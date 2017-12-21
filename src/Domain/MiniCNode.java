package Domain;

import ASTVisitor.ASTVisitor;

public abstract class MiniCNode{
	public abstract void accept(ASTVisitor v);
}
