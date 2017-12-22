package Domain.Stmt;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import ASTVisitor.ASTVisitor;

public class Switch_Statement extends Statement{

	public TerminalNode node;
	public TerminalNode ident;
	public List<Case_Statement> stmts;
	public Default_Statement defaultnode;
	
	public Switch_Statement(TerminalNode node, TerminalNode ident, List<Case_Statement> stmt) {
		this.node = node;
		this.ident = ident;
		this.stmts = stmt;
	}
	public Switch_Statement(TerminalNode node, TerminalNode ident, List<Case_Statement> stmt, Default_Statement defaultNode) {
		this.node = node;
		this.ident = ident;
		this.stmts = stmt;
		this.defaultnode = defaultNode;
	}

	@Override
	public String toString(){		
		return node.toString() + "(" + ident.toString() +")" +"{" + 
				stmts.stream().map(t ->"\n"+ t.toString()).reduce("",(acc, decl) -> acc + decl) +
				(defaultnode != null ? defaultnode.toString() : "") + "}";
	}

	@Override
	public void accept(ASTVisitor v) {
		
	}
}
