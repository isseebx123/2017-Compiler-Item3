package ASTVisitor;

import Domain.*;
import Domain.Args.*;
import Domain.Decl.*;
import Domain.Expr.*;
import Domain.Param.*;
import Domain.Stmt.*;
import Domain.Type_spec.*;

public interface ASTVisitor {
	public void visitProgram(Program node);

	public void visitDecl(Declaration node);

	public void visitVar_decl(Variable_Declaration node);

	public void visitType_spec(TypeSpecification node);

	public void visitFun_decl(Function_Declaration node);

	public void visitParams(Parameters node);;

	public void visitParam(Parameter node);

	public void visitStmt(Statement node);

	public void visitExpr_stmt(Expression_Statement node);

	public void visitWhile_stmt(While_Statement node);

	public void visitCompound_stmt(Compound_Statement node);

	public void visitLocal_decl(Local_Declaration node);

	public void visitIf_stmt(If_Statement node);

	public void visitReturn_stmt(Return_Statement node);

	public void visitExpr(Expression node);

	public void visitArgs(Arguments node);

	public void visitFor_stmt(For_Statement node);
	
	public void visitSwitch_stmt(Switch_Statement node);
	
	public void visitCase_stmt(Case_Statement node);
	
	public void visitDefault_stmt(Case_Statement node);
}
