package Domain.Stmt;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import ASTVisitor.ASTVisitor;

public class Case_Statement {

	TerminalNode casenode;
	public TerminalNode caseVal;
	public List<Statement> stmts;
	public TerminalNode breaknode;

	public Case_Statement(TerminalNode casenode, TerminalNode caseVal, List<Statement> stmts) {
		this.casenode = casenode;
		this.caseVal = caseVal;
		this.stmts = stmts;
	}

	public Case_Statement(TerminalNode casenode, TerminalNode caseVal, List<Statement> stmts, TerminalNode breaknode) {
		this.casenode = casenode;
		this.caseVal = caseVal;
		this.stmts = stmts;
		this.breaknode = breaknode;
	}
	
	@Override
	public String toString(){
		return casenode.toString() + " " + caseVal.toString() + ":\n"
				+ stmts.stream().map(t -> t.toString() + "\n").reduce("",(acc, decl) -> acc + decl)
				+ (breaknode != null ? breaknode.toString()+";" : "");
	}

	public void accept(ASTVisitor v) {
		
	}

}
