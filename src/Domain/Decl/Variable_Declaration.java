package Domain.Decl;

import java.io.IOException;

import org.antlr.v4.runtime.tree.TerminalNode;

import ASTVisitor.ASTVisitor;
import Domain.Type_spec.TypeSpecification;

public class Variable_Declaration extends Declaration{
	public TypeSpecification type;
	public TerminalNode lhs;
	
	public Variable_Declaration(TypeSpecification type, TerminalNode lhs) {
		super();
		this.type = type;
		this.lhs = lhs;
	}
	
	@Override
	public String toString(){
		return type.toString() + " " + lhs.getText() + ";";
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitDecl(this); 
	}
}
