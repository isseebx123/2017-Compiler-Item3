package Domain.Stmt;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import ASTVisitor.ASTVisitor;

public class Default_Statement {

	TerminalNode defaultnode;
	public List<Statement> stmts;
	
	public Default_Statement(TerminalNode defaultnode, List<Statement> stmts){
		this.defaultnode = defaultnode;
		this.stmts = stmts;
	}
	
	public String toString(){
		return defaultnode.toString() + ":\n"
				+ stmts.stream().map(t -> t.toString() + "\n").reduce("",(acc, decl) -> acc + decl);
	}

	public void accept(ASTVisitor v) {
		
	}
}
