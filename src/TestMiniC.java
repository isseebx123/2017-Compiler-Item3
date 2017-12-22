//import java.io.BufferedWriter;
//import java.io.FileWriter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class TestMiniC {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MiniCLexer lexer = new MiniCLexer(new ANTLRFileStream("test.c"));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		MiniCParser parser = new MiniCParser(tokens);
		ParseTree tree = parser.program();

		// building AST + translate miniC2Ucode
		MiniCAstVisitor visitor = new MiniCAstVisitor();
		UcodeGenVisitor uvisitor = new UcodeGenVisitor();
		visitor.visit(tree).accept(uvisitor);
		System.out.println(uvisitor.UCode);
		// 파일 출력
//		try {
//			BufferedWriter buf = new BufferedWriter(new FileWriter("ucode.uco"));
//			buf.write(uvisitor.UCode);
//			System.out.println(uvisitor.UCode);
//			System.out.println("ucode.uco파일로 코드를 출력하였습니다.");
//			buf.close();
//		} catch (Exception e) {
//			System.out.println("File write Err!");
//			System.exit(1);
//		}
	}

}
