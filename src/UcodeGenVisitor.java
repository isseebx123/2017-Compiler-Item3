
import java.util.HashMap;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import ASTVisitor.ASTVisitor;
import Domain.*;
import Domain.Args.*;
import Domain.Decl.*;
import Domain.Expr.*;
import Domain.Param.*;
import Domain.Stmt.*;
import Domain.Type_spec.*;

public class UcodeGenVisitor implements ASTVisitor {
	/* Value */
	private final int GLOBAL_VARIABLE_BASE = 1; // �۷ι������� ���̽�
	private final int LOCAL_VARIABLE_BASE = 2; // ���ú����� ���̽�

	private final boolean ItsArray = true;
	private final boolean ItsNotArray = false;
	private final int IS_INT_ARRAY = 1; // ������ �迭�̸� 1
	private final int IS_INT_SCALAR = 0; // ������ �迭�� �ƴϸ� 0
	private final int IS_FLOAT_OR_DOUBLE_ARRAY = 2; // ������ �迭�̸� 1
	private final int IS_FLOAT_OR_DOUBLE_SCALAR = 3; // ������ �迭�̸� 1

	/* Variable */
	private HashMap<String, int[]> LocalVariableMap = new HashMap<>();
	private HashMap<String, int[]> GlobalVariableMap = new HashMap<>();
	// <lhs, [base, offset, Type]>
	private int LocalVariableOffset = 1;
	private int GlobalVariableOffset = 1;
	private int GlobalVariableNum;

	/* Label */
	private int LabelNumber = 0;

	/* UCode */
	public String UCode = "";
	private final String ELEVEN_SPACE = "           ";

	/* Control Flow Graph(CFG) */
	private int BasicBlockCount = 0;

	/* Expr Type for Checking binary op calculation type */
	private HashMap<Expression, Integer> LhsRhsExprType = new HashMap<>();

	/* private defined Methods */
	// ���ο� �� ���ڿ��� �޾ƿ��� �޼ҵ�
	private String getNewLabel() {
		return "$$" + (LabelNumber++);
	}

	// Expr���� lhs�� rhs�� Ÿ���� ���ϴ� �޼ҵ�
	private int ExprTypeCheck(Expression lhs, Expression rhs) {
		final int lhsType = LhsRhsExprType.remove(lhs);
		final int rhsType = LhsRhsExprType.remove(rhs);

		if ((lhsType == IS_INT_SCALAR || lhsType == IS_INT_ARRAY)
				&& (rhsType == IS_INT_SCALAR || rhsType == IS_INT_ARRAY))
			return IS_INT_SCALAR;

		if ((lhsType == IS_FLOAT_OR_DOUBLE_SCALAR || lhsType == IS_FLOAT_OR_DOUBLE_ARRAY)
				&& (rhsType == IS_FLOAT_OR_DOUBLE_SCALAR || rhsType == IS_FLOAT_OR_DOUBLE_ARRAY))
			return IS_FLOAT_OR_DOUBLE_SCALAR;

		return -1;
	}

	// decl_assign���� �Ҵ��ϴ� ���� ������ Ÿ���� ���޹޾�, ������ �Ҵ繮���� Ÿ���� üũ�ϴ� �޼ҵ�
	private int doAssignTypeCheck(String literal, int Type) {
		// rhs �������� ������ �ƴ� �Ǽ����� �Ǻ�
		boolean isIntNumber = !literal.contains(".");

		if (isIntNumber && Type == IS_INT_SCALAR) {
			return IS_INT_SCALAR;
		} else if (!isIntNumber && Type == IS_FLOAT_OR_DOUBLE_SCALAR) {
			return IS_FLOAT_OR_DOUBLE_SCALAR;
		}
		return -1;
	}

	// Ÿ�� ��ȣ�� �������� �޼ҵ�
	private int getTypeNumber(TypeSpecification.Type type, boolean isArray) {
		int Type;
		if (isArray) {
			// Ÿ�� ���� (�迭)
			Type = IS_INT_ARRAY;
			if (type == (TypeSpecification.Type.FLOAT) || type == (TypeSpecification.Type.DOUBLE))
				Type = IS_FLOAT_OR_DOUBLE_ARRAY;
		} else {
			// Ÿ�� ���� (��Į��)
			Type = IS_INT_SCALAR;
			if (type == (TypeSpecification.Type.FLOAT) || type == (TypeSpecification.Type.DOUBLE))
				Type = IS_FLOAT_OR_DOUBLE_SCALAR;
		}
		return Type;
	}

	private String getNewBasicBlock() {
		return "<bb " + (++BasicBlockCount) + ">";
	}

	private String getThisBasicBlock(int BBNum) {
		return "<bb " + BBNum + ">";
	}

	private void throwsError(String errMsg, String reason) {
		System.out.println("�����Ͽ���: " + reason);
		System.out.println("===============================");
		System.out.println(errMsg);
		System.out.println("===============================");
		System.exit(1);
	}

	// ���� ���� ���ڿ��� �޾ƿ��� �޼ҵ�
	private String getSpace(int curNum) {
		return ELEVEN_SPACE.substring(0, 11 - curNum);
	}

	// ���ú������� ��������, �۷ι������� ���� �޼ҵ�
	private int[] getVariableWithShortestScope(String s) {
		int res[] = LocalVariableMap.get(s);
		return (res != null ? res : GlobalVariableMap.get(s));
	}

	/* public defined Methods */
	@Override
	public void visitProgram(Program node) {
		List<Declaration> decls = node.decls;
		final int declSize = decls.size();

		// control flow graph basic block init
		UCode += getNewBasicBlock() + ":\n";

		for (int i = 0; i < declSize; i++) {
			visitDecl(decls.get(i));
		}

		UCode += ELEVEN_SPACE + "bgn " + GlobalVariableNum + "\n";
		UCode += ELEVEN_SPACE + "ldp\n";
		UCode += ELEVEN_SPACE + "call main\n";
		UCode += ELEVEN_SPACE + "end\n";
	}

	@Override
	public void visitDecl(Declaration node) {
		if (node instanceof Function_Declaration) {
			visitFun_decl((Function_Declaration) node);
		} else if (node instanceof Variable_Declaration) {
			visitVar_decl((Variable_Declaration) node);
			GlobalVariableNum++;
		}
	}

	@Override
	public void visitVar_decl(Variable_Declaration node) {
		final String FieldName = node.lhs.getText();
		int fieldSize = 1, Type;

		// �迭�� ��� Size �� �迭���θ� ����
		if (node instanceof Variable_Declaration_Array) {
			// �迭�� ũ�Ⱑ ���������� Ȯ��
			try {
				fieldSize = Integer.parseInt(((Variable_Declaration_Array) node).rhs.getText());
			} catch (Exception e) {
				throwsError(((Variable_Declaration_Array) node).toString(), "�迭�� ũ��� �����̾�� �մϴ�.");
			}
			// Ÿ�� ���� (�迭)
			Type = getTypeNumber(node.type.type, ItsArray);
		} else {
			// Ÿ�� ���� (��Į��)
			Type = getTypeNumber(node.type.type, ItsNotArray);
		}

		UCode += ELEVEN_SPACE + "sym " + GLOBAL_VARIABLE_BASE + " " + GlobalVariableOffset + " " + fieldSize + "\n";
		// �ʿ� �����߰�, Offset ����
		GlobalVariableMap.put(FieldName, new int[] { GLOBAL_VARIABLE_BASE, GlobalVariableOffset, Type });
		GlobalVariableOffset += fieldSize;

		// �Ҵ缱���� ��� assign�� ����
		if (node instanceof Variable_Declaration_Assign) {
			String literal = ((Variable_Declaration_Assign) node).rhs.getText();
			// rhs �������� ������ �ƴ� �Ǽ����� �Ǻ�
			Double floatNum = 0.0;
			int intNum = 0;

			// Type checking
			int typeCheckResult = doAssignTypeCheck(literal, Type);
			if (typeCheckResult == -1) {
				throwsError(node.toString(), "������ Ÿ�԰� �Ҵ��ϴ� ���� Ÿ���� ���� �ٸ��ϴ�.");
				System.exit(1);
			} else if (typeCheckResult == IS_INT_SCALAR) {
				intNum = Integer.parseInt(literal);
			} else {
				floatNum = Double.parseDouble(literal);
			}

			int Variable[] = GlobalVariableMap.get(FieldName);
			UCode += ELEVEN_SPACE + "ldc " + (typeCheckResult == IS_INT_SCALAR ? Integer.toString(intNum) : floatNum)
					+ "\n";
			UCode += ELEVEN_SPACE + "str " + Variable[0] + " " + Variable[1] + "\n";
		}
	}

	@Override
	public void visitType_spec(TypeSpecification node) {
		// ������ ������ ����
	}

	@Override
	public void visitFun_decl(Function_Declaration node) {
		String funcName = node.t_node.toString(); // �Լ��̸�
		List<Local_Declaration> decls = node.compount_stmt.local_decls;
		List<Parameter> params = node.params.params;
		final int paramsSize = (params != null ? params.size() : 0);

		// ������ ũ�⸦ ���
		int fieldSize = 0;
		for (Local_Declaration decl : decls) {
			if (decl instanceof Local_Variable_Declaration_Array) { // �迭�� ���
				String arraySize = ((Local_Variable_Declaration_Array) decl).rhs.getText();
				try {
					fieldSize += Integer.parseInt(arraySize);
				} catch (Exception e) {
					throwsError(((Local_Variable_Declaration_Array) decl).toString(), "�迭�� ũ��� �����̾�� �մϴ�.");
				}
			} else { // �迭�� �ƴ� int���� ���
				fieldSize++;
			}
		}
		// �Ķ������ ũ�⸦ ���, �迭�� ��쵵 1�� ������
		fieldSize += paramsSize;

		// main proc 5 2 2, sym 2 1 1, ...
		UCode += funcName + getSpace(funcName.length()) + "proc " + fieldSize + " 2 2\n";

		// ���ú��� ������ �Ķ���Ϳ� ���� ����
		visitParams(node.params);

		// ���ú��� ���� �� stmt����
		visitCompound_stmt(node.compount_stmt);

		// �ʿ��� �Ķ���� ������ ��� ����
		LocalVariableMap.clear();
		LocalVariableOffset = 1;

		// �Լ� ���� (void�� ó��, int�� return������ ó��)
		if (node.type.type.toString().equals("VOID")) {
			UCode += ELEVEN_SPACE + "ret\n";
			UCode += ELEVEN_SPACE + "end\n";
		}

	}

	@Override
	public void visitParams(Parameters node) {
		List<Parameter> params = node.params;

		// �����̰ų� VOID�� ���� ����
		if (params != null) {
			for (Parameter p : params) {
				visitParam(p);
			}
		}
	}

	@Override
	public void visitParam(Parameter node) {
		TerminalNode t_node = node.t_node;

		int fieldSize = 1, Type;
		if (node instanceof ArrayParameter) {
			// Ÿ�� ���� (�迭)
			Type = getTypeNumber(node.type.type, ItsArray);
		} else {
			// Ÿ�� ���� (��Į��)
			Type = getTypeNumber(node.type.type, ItsArray);
		}
		UCode += ELEVEN_SPACE + "sym " + LOCAL_VARIABLE_BASE + " " + LocalVariableOffset + " " + fieldSize + "\n";

		// �ʿ� ���� �߰�, Offset ����
		LocalVariableMap.put(t_node.getText(), new int[] { LOCAL_VARIABLE_BASE, LocalVariableOffset, Type });
		LocalVariableOffset += fieldSize;
	}

	@Override
	public void visitStmt(Statement node) {
		if (node instanceof Compound_Statement) {
			visitCompound_stmt((Compound_Statement) node);
		} else if (node instanceof Expression_Statement) {
			visitExpr_stmt((Expression_Statement) node);
		} else if (node instanceof If_Statement) {
			visitIf_stmt((If_Statement) node);
		} else if (node instanceof Return_Statement) {
			visitReturn_stmt((Return_Statement) node);
		} else if (node instanceof While_Statement) {
			visitWhile_stmt((While_Statement) node);
		} else if (node instanceof For_Statement) {
			visitFor_stmt((For_Statement) node);
		}
	}

	@Override
	public void visitExpr_stmt(Expression_Statement node) {
		visitExpr(node.expr);
		LhsRhsExprType.clear(); // expr���� ���� �ؽ����� �ʱ�ȭ
	}

	@Override
	public void visitWhile_stmt(While_Statement node) {
		Expression expr = node.expr;
		Statement stmt = node.stmt;
		String startLabel = getNewLabel();
		String endLabel = getNewLabel();

		UCode += getNewBasicBlock() + ":\n"; // BBLeader: While_start
		int BBStartNumber = BasicBlockCount;
		UCode += startLabel + getSpace(startLabel.length()) + "nop\n";

		visitExpr(expr);

		UCode += ELEVEN_SPACE + "fjp " + endLabel + "\n";
		UCode += ELEVEN_SPACE + "goto " + getNewBasicBlock() + "\n";
		int BBEndNumber = BasicBlockCount;

		UCode += getNewBasicBlock() + ":\n"; // BBLeader: �귣ġ ����
		visitStmt(stmt);
		UCode += ELEVEN_SPACE + "ujp " + startLabel + "\n";
		UCode += ELEVEN_SPACE + "goto " + getThisBasicBlock(BBStartNumber) + "\n";
		UCode += getThisBasicBlock(BBEndNumber) + ":\n"; // <BB End>: target
		UCode += endLabel + getSpace(endLabel.length()) + "nop\n";
	}

	@Override
	public void visitFor_stmt(For_Statement node) {
		Statement Lexpr = node.Lexpr;
		Statement Mexpr = node.Mexpr;
		Expression Rexpr = node.Rexpr;
		Statement stmt = node.stmt;
		String startLabel = getNewLabel();
		String endLabel = getNewLabel();

		visitStmt(Lexpr);

		UCode += getNewBasicBlock() + ":\n"; // BBLeader: For_start
		int BBStartNumber = BasicBlockCount;
		UCode += startLabel + getSpace(startLabel.length()) + "nop\n";

		visitStmt(Mexpr);

		UCode += ELEVEN_SPACE + "fjp " + endLabel + "\n";
		UCode += ELEVEN_SPACE + "goto " + getNewBasicBlock() + "\n";
		int BBEndNumber = BasicBlockCount;
		UCode += getNewBasicBlock() + ":\n"; // BBLeader: �귣ġ ����

		visitStmt(stmt);
		visitExpr(Rexpr);
		UCode += ELEVEN_SPACE + "ujp " + startLabel + "\n";
		UCode += ELEVEN_SPACE + "goto " + getThisBasicBlock(BBStartNumber) + "\n";
		UCode += getThisBasicBlock(BBEndNumber) + ":\n"; // <BB End>: target
		UCode += endLabel + getSpace(endLabel.length()) + "nop\n";
	}

	@Override
	public void visitCompound_stmt(Compound_Statement node) {
		List<Local_Declaration> decls = node.local_decls;
		List<Statement> stmts = node.stmts;

		// local���� ó��
		for (Local_Declaration decl : decls) {
			visitLocal_decl(decl);
		}

		// stmt ó��
		for (Statement stmt : stmts) {
			visitStmt(stmt);
		}

		// compound_stmt ���� ���� �ʿ��� local ������ ����, offset ����
		for (Local_Declaration decl : decls) {
			int Variable[] = LocalVariableMap.remove(decl.lhs.getText());
			LocalVariableOffset = Math.min(LocalVariableOffset, Variable[1]);
		}
	}

	@Override
	public void visitLocal_decl(Local_Declaration node) {
		final String FieldName = node.lhs.getText();
		int fieldSize = 1, Type;

		// �迭���� ������ ���
		if (node instanceof Local_Variable_Declaration_Array) {
			// �迭�� ũ�Ⱑ ���������� Ȯ��
			try {
				fieldSize = Integer.parseInt(((Local_Variable_Declaration_Array) node).rhs.getText());
			} catch (Exception e) {
				throwsError(((Local_Variable_Declaration_Array) node).toString(), "�迭�� ũ��� �����̾�� �մϴ�.");
			}
			// Ÿ�� ���� (�迭)
			Type = getTypeNumber(node.type.type, ItsArray);
		} else {
			// Ÿ�� ���� (��Į��)
			Type = getTypeNumber(node.type.type, ItsNotArray);
		}

		UCode += ELEVEN_SPACE + "sym " + LOCAL_VARIABLE_BASE + " " + LocalVariableOffset + " " + fieldSize + "\n";
		// �ʿ� ���� �߰�, Offset ����
		LocalVariableMap.put(FieldName, new int[] { LOCAL_VARIABLE_BASE, LocalVariableOffset, Type });
		LocalVariableOffset += fieldSize;

		// �Ҵ缱���� ��� �Ҵ繮 �ʿ�
		if (node instanceof Local_Variable_Declaration_Assign) {
			String literal = ((Local_Variable_Declaration_Assign) node).rhs.getText();
			// rhs �������� ������ �ƴ� �Ǽ����� �Ǻ�
			Double floatNum = 0.0;
			int intNum = 0;

			// Type checking
			int typeCheckResult = doAssignTypeCheck(literal, Type);
			if (typeCheckResult == -1) {
				throwsError(node.toString(), "������ Ÿ�԰� �Ҵ��ϴ� ���� Ÿ���� ���� �ٸ��ϴ�.");
				System.exit(1);
			} else if (typeCheckResult == IS_INT_SCALAR) {
				intNum = Integer.parseInt(literal);
			} else {
				floatNum = Double.parseDouble(literal);
			}

			int Variable[] = LocalVariableMap.get(FieldName);
			UCode += ELEVEN_SPACE + "ldc " + (typeCheckResult == IS_INT_SCALAR ? Integer.toString(intNum) : floatNum)
					+ "\n";
			UCode += ELEVEN_SPACE + "str " + Variable[0] + " " + Variable[1] + "\n";
		}
	}

	@Override
	public void visitIf_stmt(If_Statement node) {
		Expression expr = node.expr;
		Statement stmt1 = node.if_stmt;

		String endLabel = getNewLabel();

		if (node.else_stmt == null) {
			// if
			visitExpr(expr);
			UCode += ELEVEN_SPACE + "fjp " + endLabel + "\n";
			UCode += ELEVEN_SPACE + "goto " + getNewBasicBlock() + "\n";
			int BBEndNumber = BasicBlockCount;
			UCode += getNewBasicBlock() + ":\n"; // BBLeader: �귣ġ ����

			visitStmt(stmt1);

			UCode += getThisBasicBlock(BBEndNumber) + ":\n"; // <BB End>: target

			// end-if label
			UCode += endLabel + getSpace(endLabel.length()) + "nop\n";
		} else {
			// if-else
			String elseLabel = getNewLabel();

			visitExpr(expr);
			UCode += ELEVEN_SPACE + "fjp " + elseLabel + "\n";
			UCode += ELEVEN_SPACE + "goto " + getNewBasicBlock() + "\n";
			int BBElseNumber = BasicBlockCount;
			UCode += getNewBasicBlock() + ":\n"; // BBLeader: �귣ġ ����

			visitStmt(stmt1);
			UCode += ELEVEN_SPACE + "ujp " + endLabel + "\n";
			UCode += ELEVEN_SPACE + "goto " + getNewBasicBlock() + "\n";
			int BBEndNumber = BasicBlockCount;

			// else label
			// <BB Else>: target
			// �귣ġ ������ instruction�� ��ħ. ��������� �ϳ��� �ۼ�
			UCode += getThisBasicBlock(BBElseNumber) + ":\n";
			UCode += elseLabel + getSpace(elseLabel.length()) + "nop\n";

			Statement stmt2 = node.else_stmt;
			visitStmt(stmt2);

			// end-if label
			UCode += getThisBasicBlock(BBEndNumber) + ":\n"; // <BB End>: target
			UCode += endLabel + getSpace(endLabel.length()) + "nop\n";
		}
	}

	@Override
	public void visitReturn_stmt(Return_Statement node) {
		Expression expr = node.expr;
		visitExpr(expr); // ���ÿ� push�ϴ� �ͱ��� �����Ѵٰ� ����.

		// �Լ� ���� (int�� ó��, void�� func_decl���� ó��)
		UCode += ELEVEN_SPACE + "retv\n";
		UCode += ELEVEN_SPACE + "end\n";
	}

	@Override
	public void visitExpr(Expression node) {
		if (node instanceof ArefAssignNode) {
			// t_node[lhs] = rhs;
			ArefAssignNode n = (ArefAssignNode) node;
			TerminalNode t_node = n.t_node;
			Expression lhs = n.lhs;
			Expression rhs = n.rhs;
			int arrayVariable[] = getVariableWithShortestScope(t_node.getText());

			// ����� ���ÿ� ����ִٰ� ����. LITERAL|IDENT���� ó��.
			visitExpr(lhs);
			UCode += ELEVEN_SPACE + "lda " + arrayVariable[0] + " " + arrayVariable[1] + "\n";
			UCode += ELEVEN_SPACE + "add\n";

			visitExpr(rhs);
			UCode += ELEVEN_SPACE + "sti\n";
		} else if (node instanceof ArefNode) {
			// t_node[expr];
			ArefNode n = (ArefNode) node;
			TerminalNode t_node = n.t_node;
			Expression expr = n.expr;
			int arrayVariable[] = getVariableWithShortestScope(t_node.getText());

			visitExpr(expr);

			// �迭ũ�� expr�� ���������� Ȯ��
			int exprType = LhsRhsExprType.remove(expr);
			if (exprType == IS_FLOAT_OR_DOUBLE_SCALAR || exprType == IS_FLOAT_OR_DOUBLE_ARRAY) {
				throwsError(node.toString(), "�迭�� ũ��� �������� �մϴ�.");
				System.exit(1);
			}

			UCode += ELEVEN_SPACE + "lda " + arrayVariable[0] + " " + arrayVariable[1] + "\n";
			UCode += ELEVEN_SPACE + "add\n";
		} else if (node instanceof AssignNode) {
			// t_node = expr;
			AssignNode n = (AssignNode) node;
			TerminalNode t_node = n.t_node;
			Expression expr = n.expr;
			int arrayVariable[] = getVariableWithShortestScope(t_node.getText());
			visitExpr(expr);
			UCode += ELEVEN_SPACE + "str " + arrayVariable[0] + " " + arrayVariable[1] + "\n";
		} else if (node instanceof BinaryOpNode) {
			// lhs op rhs
			BinaryOpNode n = (BinaryOpNode) node;
			Expression lhs = n.lhs, rhs = n.rhs;
			String op = n.op;

			visitExpr(lhs);
			visitExpr(rhs);

			int Type = ExprTypeCheck(lhs, rhs);
			if ((op.equals("+") || op.equals("-") || op.equals("/") || op.equals("*") || op.equals("%"))
					&& Type == -1) {
				// ���� �������꿡�� Ÿ��üũ��� lhs, rhs�� Ÿ���� �ٸ� ��� 
				throwsError(node.toString(), "�����ϴ� Expr���� Ÿ���� ���� �ٸ��ϴ�.");
			}
			// lhs, rhs Ÿ���� ���� ��� ������ ��� Ÿ���� ���� ����
			LhsRhsExprType.put(node, Type);

			if (op.equals("*")) {
				UCode += ELEVEN_SPACE + "mul\n";
			} else if (op.equals("/")) {
				UCode += ELEVEN_SPACE + "div\n";
			} else if (op.equals("%")) {
				UCode += ELEVEN_SPACE + "mod\n";
			} else if (op.equals("+")) {
				UCode += ELEVEN_SPACE + "add\n";
			} else if (op.equals("-")) {
				UCode += ELEVEN_SPACE + "sub\n";
			} else if (op.equals("==")) {
				UCode += ELEVEN_SPACE + "eq\n";
			} else if (op.equals("!=")) {
				UCode += ELEVEN_SPACE + "ne\n";
			} else if (op.equals("<=")) {
				UCode += ELEVEN_SPACE + "le\n";
			} else if (op.equals("<")) {
				UCode += ELEVEN_SPACE + "lt\n";
			} else if (op.equals(">=")) {
				UCode += ELEVEN_SPACE + "ge\n";
			} else if (op.equals(">")) {
				UCode += ELEVEN_SPACE + "gt\n";
			} else if (op.equals("and")) {
				UCode += ELEVEN_SPACE + "and\n";
			} else if (op.equals("or")) {
				UCode += ELEVEN_SPACE + "or\n";
			}
		} else if (node instanceof FuncallNode) {
			// t_node(args);
			FuncallNode n = (FuncallNode) node;
			TerminalNode t_node = n.t_node;
			Arguments args = n.args;

			UCode += ELEVEN_SPACE + "ldp\n";
			visitArgs(args);
			UCode += ELEVEN_SPACE + "call " + t_node.getText() + "\n";
		} else if (node instanceof ParenExpression) {
			// (expr)
			ParenExpression n = (ParenExpression) node;
			Expression expr = n.expr;

			visitExpr(expr);
			// ���� �������� Ÿ���� �������־����� �������� �ڽ��� ���� �ٽ� ����
			if (LhsRhsExprType.containsKey(expr)) {
				LhsRhsExprType.put(node, LhsRhsExprType.remove(expr));
			}
		} else if (node instanceof TerminalExpression) {
			// 1 �Ǵ� x
			TerminalExpression n = (TerminalExpression) node;
			String terminal = n.t_node.getText();

			int Variable[] = getVariableWithShortestScope(terminal);

			if (Variable != null) {
				// IDENT
				LhsRhsExprType.put(node, Variable[2]); // Ÿ��üũ�� ����, Expr�� Ÿ���� ����

				if (Variable[2] == IS_INT_SCALAR)
					UCode += ELEVEN_SPACE + "lod " + Variable[0] + " " + Variable[1] + "\n";
				else if (Variable[2] == IS_INT_ARRAY)
					UCode += ELEVEN_SPACE + "lda " + Variable[0] + " " + Variable[1] + "\n";
			} else {
				// LITERAL
				UCode += ELEVEN_SPACE + "ldc " + terminal + "\n";

				// Ÿ��üũ�� ����, Expr�� Ÿ���� ����
				int Type = terminal.contains(".") ? IS_FLOAT_OR_DOUBLE_SCALAR : IS_INT_SCALAR;
				LhsRhsExprType.put(node, Type);
			}
		} else if (node instanceof UnaryOpNode) {
			// op expr
			UnaryOpNode n = (UnaryOpNode) node;
			String op = n.op;
			Expression expr = n.expr;

			visitExpr(expr);
			// ���� �������� Ÿ���� �������־����� �������� �ڽ��� ���� �ٽ� ����
			if (LhsRhsExprType.containsKey(expr)) {
				LhsRhsExprType.put(node, LhsRhsExprType.remove(expr));
			}

			if (op.equals("-")) {
				UCode += ELEVEN_SPACE + "neg\n";
			} else if (op.equals("+")) {
				// dup?
			} else if (op.equals("--")) {
				UCode += ELEVEN_SPACE + "dec\n";
			} else if (op.equals("++")) {
				UCode += ELEVEN_SPACE + "inc\n";
			} else if (op.equals("!")) {
				UCode += ELEVEN_SPACE + "notop\n";
			}

			TerminalExpression nn = (TerminalExpression) expr;
			String terminal = nn.t_node.getText();
			int Variable[] = getVariableWithShortestScope(terminal);

			// Variable == null���� -1 �Ǵ� +1�� ���� ���(op="+" or "-")�� ����
			if (Variable != null)
				UCode += ELEVEN_SPACE + "str " + Variable[0] + " " + Variable[1] + "\n";
		}
	}

	@Override
	public void visitArgs(Arguments node) {
		List<Expression> exprs = node.exprs;

		for (Expression expr : exprs) {
			visitExpr(expr);
		}
	}

	static String switchEndLabel;	//����ġ�� ������ ��  
	static String nextCase;			//����ġ������ ���� Case�� ��ġ
	static boolean lastCase;		//���纸���ִ���ġ�� ������ Case����
	static boolean defaultCase;		//default Case�� �ִ���
	static int BBnextCaseNumber;	//����ġ������ ���� Case�� BB��ȣ
	static int BBSwitch;			//����ġ�� �����°� BB��ȣ
	@Override
	public void visitSwitch_stmt(Switch_Statement node) {

		TerminalNode ident = node.ident;
		List<Case_Statement> stmts = node.stmts;
		Default_Statement defaultnode = node.defaultnode;
		
		if (stmts.size() != 0)	//switch���ȿ� case�� ������ �����°� ����
			switchEndLabel = getNewLabel();
		
		if(defaultnode != null)	//default Case�� ������ true
			defaultCase = true;
		
		getNewBasicBlock();	//switch������ ���
		BBSwitch = BasicBlockCount;	//������ ��� ����
		
		for(int i=0; i<stmts.size(); i++){
			int arrayVariable[] = getVariableWithShortestScope(ident.getText());
			UCode += ELEVEN_SPACE + "lod " + arrayVariable[0] + " " + arrayVariable[1] + "\n";		
			
			if(i == stmts.size()-1)	//������ Case���̸� true
				lastCase = true;
			
			visitCase_stmt(stmts.get(i));
			
			if(i != stmts.size()-1){	//������ case�� �ƴ϶�� 
				UCode += "<bb " + BBnextCaseNumber + ">:\n"; // BBLeader: �귣ġ ����
				UCode += nextCase + getSpace(nextCase.length()) + "nop\n";	
			}	
			else if( i == stmts.size()-1 && defaultCase == true){	//�������ε� default�� �ִ� ���
				UCode += "<bb " + BBnextCaseNumber + ">:\n"; // BBLeader: �귣ġ ����
				UCode += nextCase + getSpace(nextCase.length()) + "nop\n";	
			}		
			//�������ε� default���ִ°�쿡 ���� U-code�� visitCase_stmt���� ������־� ���⼭ �ȸ�����Ѵ�. (��������ȭ)
		}
		
		if(defaultCase == true){
			visitDefault_stmt(defaultnode);
		}
		UCode += "<bb " + BBSwitch + ">:\n";	//������ ��
		UCode += switchEndLabel + getSpace(switchEndLabel.length()) + "nop\n";
	}

	@Override
	public void visitCase_stmt(Case_Statement node) {
		TerminalNode caseVal = node.caseVal;
		List<Statement> stmts = node.stmts;
		TerminalNode breaknode = node.breaknode;
		
		int Variable[] = getVariableWithShortestScope(caseVal.getText());
		if (Variable != null) {	//case������ ���� or ���� �ڿ� �ȵȴ�.
			UCode += ELEVEN_SPACE + "lod " + Variable[0] + " " + Variable[1] + "\n";
		} else{
			UCode += ELEVEN_SPACE + "ldc " + caseVal.getText() + "\n";
		}
		UCode += ELEVEN_SPACE + "eq\n";
		if(lastCase == false || defaultCase == true){	//������ Case�ƴϰų� �������ε� default�ִ°�� ������ġ�� ���������
			nextCase = getNewLabel();
			UCode += ELEVEN_SPACE + "fjp " + nextCase + "\n";
			UCode += ELEVEN_SPACE + "goto " + getNewBasicBlock() + "\n";
			BBnextCaseNumber = BasicBlockCount;
			UCode += getNewBasicBlock() + ":\n"; // BBLeader: �귣ġ ����
		} else{	//������ Case�ε� default�� ���°��  ���⼭ u-code����
			UCode += ELEVEN_SPACE + "fjp " + switchEndLabel + "\n";
			UCode += ELEVEN_SPACE + "goto <bb " + BBSwitch + ">\n";
			UCode += getNewBasicBlock() + ":\n"; // BBLeader: �귣ġ ����
		}
		
		for(int i=0; i<stmts.size(); i++){
			visitStmt(stmts.get(i));
		}
		
		if(breaknode != null){	//break���� �ִ� ��� switch�� ���������� �Ѵ�.
			UCode += ELEVEN_SPACE + "ujp " + switchEndLabel + "\n";
			UCode += ELEVEN_SPACE + "goto <bb " + BBSwitch + ">\n";
		}
	}	

	@Override
	public void visitDefault_stmt(Default_Statement node) {
		List<Statement> stmts = node.stmts;
		for(int i=0; i<stmts.size(); i++)
			visitStmt(stmts.get(i));
	}

}
