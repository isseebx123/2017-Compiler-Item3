package Domain.Type_spec;

import ASTVisitor.ASTVisitor;
import Domain.MiniCNode;

public class TypeSpecification extends MiniCNode {
	public final Type type;

	public enum Type {
		VOID, INT, DOUBLE, FLOAT
	}

	public TypeSpecification(Type type) {
		this.type = type;
	}
	
	@Override
	public String toString(){
		return type.toString().toLowerCase();
	}

	@Override
	public void accept(ASTVisitor v) {
		v.visitType_spec(this);
	}
}
