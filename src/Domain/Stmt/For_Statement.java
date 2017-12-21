package Domain.Stmt;

import org.antlr.v4.runtime.tree.TerminalNode;

import ASTVisitor.ASTVisitor;
import Domain.Expr.Expression;

public class For_Statement extends Statement{
	public TerminalNode For_node;
	public Statement Lexpr;
	public Statement Mexpr;
	public Expression Rexpr;
	public Statement stmt;
	
	public For_Statement(TerminalNode For_node, Statement Lexpr, Statement Mexpr, Expression Rexpr, Statement stmt){
		super();
		this.For_node = For_node;
		this.Lexpr = Lexpr;
		this.Mexpr = Mexpr;
		this.Rexpr = Rexpr;
		this.stmt = stmt;
	}
	
	@Override
	public String toString(){
		return For_node.getText() + "(" + Lexpr.toString() + " "+ Mexpr.toString() + " " + Rexpr.toString() +")\n" + stmt.toString();
	}
	
	@Override
	public void accept(ASTVisitor v) {
		// TODO Auto-generated method stub
		v.visitFor_stmt(this);
	}

}
