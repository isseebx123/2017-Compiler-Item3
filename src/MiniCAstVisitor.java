
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import Domain.*;
import Domain.Args.*;
import Domain.Decl.*;
import Domain.Expr.*;
import Domain.Param.*;
import Domain.Stmt.*;
import Domain.Type_spec.*;

public class MiniCAstVisitor extends MiniCBaseVisitor {

	public MiniCNode visit(ParseTree tree) {
		Program program = visitProgram((MiniCParser.ProgramContext) tree);
		//System.out.println(program.toString());
		return program;
	}

	@Override
	public Program visitProgram(MiniCParser.ProgramContext ctx) {
		List<Declaration> declList = new ArrayList<>();
		Program program;

		final int CHILDRENCOUNT = ctx.getChildCount();
		for (int i = 0; i < CHILDRENCOUNT; i++) {
			Declaration decl = visitDecl((MiniCParser.DeclContext) ctx.getChild(i));
			declList.add(decl);
		}
		program = new Program(declList);

		return program;
	}

	@Override
	public Declaration visitDecl(MiniCParser.DeclContext ctx) {
		ParseTree childDecl = ctx.getChild(0);
		Declaration decl = null;

		/* Local_Declaration은 들어오지 않는다! */
		if (childDecl instanceof MiniCParser.Fun_declContext) {
			decl = visitFun_decl((MiniCParser.Fun_declContext) childDecl);
		} else if (childDecl instanceof MiniCParser.Var_declContext) {
			decl = visitVar_decl((MiniCParser.Var_declContext) childDecl);
		}

		return decl;
	}

	@Override
	public Declaration visitFun_decl(MiniCParser.Fun_declContext ctx) {
		Function_Declaration decl;

		ParseTree ttype_spec = ctx.getChild(0);
		ParseTree tterminal = ctx.getChild(1);
		ParseTree tparams = ctx.getChild(3);
		ParseTree tcompound_stmt = ctx.getChild(5);

		TypeSpecification type = visitType_spec((MiniCParser.Type_specContext) ttype_spec);
		TerminalNode t_node = (TerminalNode) tterminal;
		Parameters params = visitParams((MiniCParser.ParamsContext) tparams);
		Compound_Statement compount_stmt = (Compound_Statement) visitCompound_stmt(
				(MiniCParser.Compound_stmtContext) tcompound_stmt);

		decl = new Function_Declaration(type, t_node, params, compount_stmt);

		return (Declaration) decl;
	}

	@Override
	public Declaration visitVar_decl(MiniCParser.Var_declContext ctx) {
		Variable_Declaration decl = null;

		TypeSpecification type_spec = visitType_spec((MiniCParser.Type_specContext) ctx.getChild(0));
		TerminalNode lhs = (TerminalNode) ctx.getChild(1);
		TerminalNode rhs;

		// int a;
		if (ctx.getChildCount() == 3) {
			decl = new Variable_Declaration(type_spec, lhs);
		}
		// int a = 1;
		else if (ctx.getChildCount() == 5) {
			rhs = (TerminalNode) ctx.getChild(3);
			decl = new Variable_Declaration_Assign(type_spec, lhs, rhs);
		}
		// int a[2];
		else if (ctx.getChildCount() == 6) {
			rhs = (TerminalNode) ctx.getChild(3);
			decl = new Variable_Declaration_Array(type_spec, lhs, rhs);
		}

		return (Declaration) decl;
	}

	@Override
	public Local_Declaration visitLocal_decl(MiniCParser.Local_declContext ctx) {
		Local_Declaration decl = null;

		TypeSpecification type_spec = visitType_spec((MiniCParser.Type_specContext) ctx.getChild(0));
		TerminalNode lhs = (TerminalNode) ctx.getChild(1);
		TerminalNode rhs;

		// int a;
		if (ctx.getChildCount() == 3) {
			decl = new Local_Declaration(type_spec, lhs);
		}
		// int a = 1;
		else if (ctx.getChildCount() == 5) {
			rhs = (TerminalNode) ctx.getChild(3);
			decl = new Local_Variable_Declaration_Assign(type_spec, lhs, rhs);
		}
		// int a[2];
		else if (ctx.getChildCount() == 6) {
			rhs = (TerminalNode) ctx.getChild(3);
			decl = new Local_Variable_Declaration_Array(type_spec, lhs, rhs);
		}

		return decl;
	}

	@Override
	public TypeSpecification visitType_spec(MiniCParser.Type_specContext ctx) {
		TypeSpecification typeSpecification;

		if (ctx.getText().equals("int"))
			typeSpecification = new TypeSpecification(TypeSpecification.Type.INT);
		else
			typeSpecification = new TypeSpecification(TypeSpecification.Type.VOID);

		return typeSpecification;
	}

	@Override
	public Parameters visitParams(MiniCParser.ParamsContext ctx) {
		Parameters parameters;
		TypeSpecification typeSpecification;
		final int CHILDCOUNT = ctx.getChildCount();

		// 공백
		if (CHILDCOUNT == 0) {
			parameters = new Parameters();
		}
		// VOID
		else if (CHILDCOUNT == 1 && ctx.getChild(0) instanceof TerminalNode) {
			typeSpecification = new TypeSpecification(TypeSpecification.Type.VOID);
			parameters = new Parameters(typeSpecification);
		}
		// (int a, int b ...)
		else {
			List<Parameter> params = new ArrayList();
			for (int i = 0; i < CHILDCOUNT; i += 2) {
				Parameter param = visitParam((MiniCParser.ParamContext) ctx.getChild(i));
				params.add(param);
			}
			parameters = new Parameters(params);
		}

		return parameters;
	}

	@Override
	public Parameter visitParam(MiniCParser.ParamContext ctx) {
		Parameter parameter = null;
		TypeSpecification typeSpecification = visitType_spec((MiniCParser.Type_specContext) ctx.getChild(0));
		TerminalNode t_node = (TerminalNode) ctx.getChild(1);

		final int CHILDCOUNT = ctx.getChildCount();

		// int a
		if (CHILDCOUNT == 2) {
			parameter = new Parameter(typeSpecification, t_node);
		}
		// int a[]
		else if (CHILDCOUNT == 4) {
			ArrayParameter aParam = new ArrayParameter(typeSpecification, t_node);
			parameter = (Parameter) aParam;
		}

		return parameter;
	}

	@Override
	public Statement visitStmt(MiniCParser.StmtContext ctx) {
		Statement statement = null;
		ParseTree childStmt = ctx.getChild(0);

		if (childStmt instanceof MiniCParser.Compound_stmtContext) {
			statement = visitCompound_stmt((MiniCParser.Compound_stmtContext) childStmt);
		} else if (childStmt instanceof MiniCParser.Expr_stmtContext) {
			statement = visitExpr_stmt((MiniCParser.Expr_stmtContext) childStmt);
		} else if (childStmt instanceof MiniCParser.If_stmtContext) {
			statement = visitIf_stmt((MiniCParser.If_stmtContext) childStmt);
		} else if (childStmt instanceof MiniCParser.Return_stmtContext) {
			statement = visitReturn_stmt((MiniCParser.Return_stmtContext) childStmt);
		} else if (childStmt instanceof MiniCParser.While_stmtContext) {
			statement = visitWhile_stmt((MiniCParser.While_stmtContext) childStmt);
		} else if (childStmt instanceof MiniCParser.For_stmtContext) {
			statement = visitFor_stmt((MiniCParser.For_stmtContext) childStmt);
		}

		return statement;
	}

	@Override
	public Statement visitCompound_stmt(MiniCParser.Compound_stmtContext ctx) {
		Compound_Statement stmt = null;
		List<Local_Declaration> local_decls = new ArrayList();
		List<Statement> stmts = new ArrayList();

		final int CHILDCOUNT = ctx.getChildCount();
		for (int i = 0; i < CHILDCOUNT; i++) {
			ParseTree child = ctx.getChild(i);
			
			if (child instanceof MiniCParser.Local_declContext) {
				Local_Declaration lDecl = visitLocal_decl((MiniCParser.Local_declContext) child);
				local_decls.add(lDecl);
			} else if (child instanceof MiniCParser.StmtContext) {
				Statement statement = visitStmt((MiniCParser.StmtContext) child);
				stmts.add(statement);
			}
		}
		stmt = new Compound_Statement(local_decls, stmts);

		return (Statement) stmt;
	}

	@Override
	public Statement visitExpr_stmt(MiniCParser.Expr_stmtContext ctx) {
		Expression_Statement stmt = null;
		Expression expr = visitExpr((MiniCParser.ExprContext) ctx.getChild(0));

		stmt = new Expression_Statement(expr);

		return (Statement) stmt;
	}

	@Override
	public Statement visitIf_stmt(MiniCParser.If_stmtContext ctx) {
		If_Statement stmt = null;

		TerminalNode ifnode = (TerminalNode) ctx.getChild(0);
		TerminalNode elsenode;
		Expression expr = visitExpr((MiniCParser.ExprContext) ctx.getChild(2));
		Statement if_stmt = visitStmt((MiniCParser.StmtContext) ctx.getChild(4));
		Statement else_stmt;

		final int CHILDCOUNT = ctx.getChildCount();
		if (CHILDCOUNT == 5) {
			stmt = new If_Statement(ifnode, expr, if_stmt);
		} else if (CHILDCOUNT == 7) {
			elsenode = (TerminalNode) ctx.getChild(5);
			else_stmt = visitStmt((MiniCParser.StmtContext) ctx.getChild(6));
			stmt = new If_Statement(ifnode, expr, if_stmt, elsenode, else_stmt);
		}

		return (Statement) stmt;
	}

	@Override
	public Statement visitReturn_stmt(MiniCParser.Return_stmtContext ctx) {
		Return_Statement stmt = null;
		TerminalNode return_node = (TerminalNode) ctx.getChild(0);
		Expression expr;

		final int CHILDCOUNT = ctx.getChildCount();
		if (CHILDCOUNT == 2) {
			stmt = new Return_Statement(return_node);
		} else if (CHILDCOUNT == 3) {
			expr = visitExpr((MiniCParser.ExprContext) ctx.getChild(1));
			stmt = new Return_Statement(return_node, expr);
		}

		return (Statement) stmt;
	}

	@Override
	public Statement visitWhile_stmt(MiniCParser.While_stmtContext ctx) {
		While_Statement stmt = null;
		TerminalNode while_node = (TerminalNode) ctx.getChild(0);
		Expression expr = visitExpr((MiniCParser.ExprContext) ctx.getChild(2));
		Statement _stmt = visitStmt((MiniCParser.StmtContext) ctx.getChild(4));

		stmt = new While_Statement(while_node, expr, _stmt);

		return (Statement) stmt;
	}
	
	@Override
	public Statement visitFor_stmt(MiniCParser.For_stmtContext ctx) {
		For_Statement stmt = null;
		TerminalNode for_node = (TerminalNode) ctx.getChild(0);
		Statement Lexpr = visitExpr_stmt((MiniCParser.Expr_stmtContext) ctx.getChild(2));
		Statement Mexpr = visitExpr_stmt((MiniCParser.Expr_stmtContext) ctx.getChild(3));
		Expression Rexpr = visitExpr((MiniCParser.ExprContext) ctx.getChild(4));
		Statement _stmt = visitStmt((MiniCParser.StmtContext) ctx.getChild(6));

		stmt = new For_Statement(for_node, Lexpr, Mexpr, Rexpr, _stmt);

		return (Statement) stmt;
	}
	
	

	@Override
	public Expression visitExpr(MiniCParser.ExprContext ctx) {
		Expression expr = null;
		final int CHILDCOUNT = ctx.getChildCount();

		// 1
		if (CHILDCOUNT == 1) {
			TerminalNode t_node = (TerminalNode) ctx.getChild(0);
			expr = new TerminalExpression(t_node);
		}
		// 3
		else if (CHILDCOUNT == 3 && ctx.getChild(0).getText().equals("(")) {
			Expression _expr = visitExpr((MiniCParser.ExprContext) ctx.getChild(1));
			expr = new ParenExpression(_expr);
		}
		// 4
		// 4
		else if (CHILDCOUNT == 4) {
			TerminalNode t_node = (TerminalNode) ctx.getChild(0);
			if (ctx.getChild(1).getText().equals("[")) {
				Expression _expr = visitExpr((MiniCParser.ExprContext) ctx.getChild(2));
				expr = new ArefNode(t_node, _expr);
			} else if (ctx.getChild(1).getText().equals("(")) {
				Arguments _args = visitArgs((MiniCParser.ArgsContext) ctx.getChild(2));
				expr = new FuncallNode(t_node, _args);
			}
		}
		// 2
		else if (CHILDCOUNT == 2) {
			String op = ctx.getChild(0).getText();
			Expression _expr = visitExpr((MiniCParser.ExprContext) ctx.getChild(1));
			expr = new UnaryOpNode(op, _expr);
		}
		// 3
		// 3
		// 3
		else if (CHILDCOUNT == 3) {
			if (ctx.getChild(1).getText().equals("=")) {
				TerminalNode t_node = (TerminalNode) ctx.getChild(0);
				Expression _expr = visitExpr((MiniCParser.ExprContext) ctx.getChild(2));
				expr = new AssignNode(t_node, _expr);
			} else {
				Expression lhs = visitExpr((MiniCParser.ExprContext) ctx.getChild(0));
				Expression rhs = visitExpr((MiniCParser.ExprContext) ctx.getChild(2));
				String op = ctx.getChild(1).getText();
				expr = new BinaryOpNode(lhs, op, rhs);
			}
		}
		// 6
		else if (CHILDCOUNT == 6) {
			TerminalNode t_node = (TerminalNode) ctx.getChild(0);
			Expression lhs = visitExpr((MiniCParser.ExprContext) ctx.getChild(2));
			Expression rhs = visitExpr((MiniCParser.ExprContext) ctx.getChild(5));
			expr = new ArefAssignNode(t_node, lhs, rhs);
		}

		return expr;
	}

	@Override
	public Arguments visitArgs(MiniCParser.ArgsContext ctx) {
		Arguments args = null;
		List<Expression> exprs = new ArrayList();
		final int CHILDCOUNT = ctx.getChildCount();

		// 공백
		if (CHILDCOUNT == 0) {
			args = new Arguments();
		}
		// expr (',' expr)*
		else {
			for (int i = 0; i < CHILDCOUNT; i += 2) {
				Expression expr = visitExpr((MiniCParser.ExprContext) ctx.getChild(i));
				exprs.add(expr);
			}
			args = new Arguments(exprs);
		}

		return args;
	}

}
