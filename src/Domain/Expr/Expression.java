package Domain.Expr;

import ASTVisitor.ASTVisitor;
import Domain.MiniCNode;

public class Expression extends MiniCNode {
	@Override
	public void accept(ASTVisitor v) {
		v.visitExpr(this);
	}
}
