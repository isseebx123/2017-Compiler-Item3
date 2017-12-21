// Generated from MiniC.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniCParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, VOID=20, INT=21, WHILE=22, IF=23, FOR=24, ELSE=25, 
		RETURN=26, OR=27, AND=28, LE=29, GE=30, EQ=31, NE=32, IDENT=33, LITERAL=34, 
		DecimalConstant=35, OctalConstant=36, HexadecimalConstant=37, WS=38;
	public static final int
		RULE_program = 0, RULE_decl = 1, RULE_var_decl = 2, RULE_type_spec = 3, 
		RULE_fun_decl = 4, RULE_params = 5, RULE_param = 6, RULE_stmt = 7, RULE_expr_stmt = 8, 
		RULE_while_stmt = 9, RULE_for_stmt = 10, RULE_compound_stmt = 11, RULE_local_decl = 12, 
		RULE_if_stmt = 13, RULE_return_stmt = 14, RULE_expr = 15, RULE_args = 16;
	public static final String[] ruleNames = {
		"program", "decl", "var_decl", "type_spec", "fun_decl", "params", "param", 
		"stmt", "expr_stmt", "while_stmt", "for_stmt", "compound_stmt", "local_decl", 
		"if_stmt", "return_stmt", "expr", "args"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'='", "'['", "']'", "'('", "')'", "','", "'{'", "'}'", "'-'", 
		"'+'", "'--'", "'++'", "'!'", "'*'", "'/'", "'%'", "'<'", "'>'", "'void'", 
		"'int'", "'while'", "'if'", "'for'", "'else'", "'return'", "'or'", "'and'", 
		"'<='", "'>='", "'=='", "'!='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, "VOID", "INT", "WHILE", 
		"IF", "FOR", "ELSE", "RETURN", "OR", "AND", "LE", "GE", "EQ", "NE", "IDENT", 
		"LITERAL", "DecimalConstant", "OctalConstant", "HexadecimalConstant", 
		"WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniC.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniCParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				decl();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VOID || _la==INT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public Var_declContext var_decl() {
			return getRuleContext(Var_declContext.class,0);
		}
		public Fun_declContext fun_decl() {
			return getRuleContext(Fun_declContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				var_decl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				fun_decl();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Var_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public Var_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterVar_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitVar_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitVar_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_declContext var_decl() throws RecognitionException {
		Var_declContext _localctx = new Var_declContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_var_decl);
		try {
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				type_spec();
				setState(44);
				match(IDENT);
				setState(45);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				type_spec();
				setState(48);
				match(IDENT);
				setState(49);
				match(T__1);
				setState(50);
				match(LITERAL);
				setState(51);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(53);
				type_spec();
				setState(54);
				match(IDENT);
				setState(55);
				match(T__2);
				setState(56);
				match(LITERAL);
				setState(57);
				match(T__3);
				setState(58);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_specContext extends ParserRuleContext {
		public TerminalNode VOID() { return getToken(MiniCParser.VOID, 0); }
		public TerminalNode INT() { return getToken(MiniCParser.INT, 0); }
		public Type_specContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_spec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterType_spec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitType_spec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitType_spec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_specContext type_spec() throws RecognitionException {
		Type_specContext _localctx = new Type_specContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_type_spec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_la = _input.LA(1);
			if ( !(_la==VOID || _la==INT) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fun_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public Fun_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterFun_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitFun_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitFun_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fun_declContext fun_decl() throws RecognitionException {
		Fun_declContext _localctx = new Fun_declContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fun_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			type_spec();
			setState(65);
			match(IDENT);
			setState(66);
			match(T__4);
			setState(67);
			params();
			setState(68);
			match(T__5);
			setState(69);
			compound_stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TerminalNode VOID() { return getToken(MiniCParser.VOID, 0); }
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params);
		int _la;
		try {
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				param();
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(72);
					match(T__6);
					setState(73);
					param();
					}
					}
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				match(VOID);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(83);
				type_spec();
				setState(84);
				match(IDENT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(86);
				type_spec();
				setState(87);
				match(IDENT);
				setState(88);
				match(T__2);
				setState(89);
				match(T__3);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public Expr_stmtContext expr_stmt() {
			return getRuleContext(Expr_stmtContext.class,0);
		}
		public Compound_stmtContext compound_stmt() {
			return getRuleContext(Compound_stmtContext.class,0);
		}
		public If_stmtContext if_stmt() {
			return getRuleContext(If_stmtContext.class,0);
		}
		public While_stmtContext while_stmt() {
			return getRuleContext(While_stmtContext.class,0);
		}
		public For_stmtContext for_stmt() {
			return getRuleContext(For_stmtContext.class,0);
		}
		public Return_stmtContext return_stmt() {
			return getRuleContext(Return_stmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stmt);
		try {
			setState(99);
			switch (_input.LA(1)) {
			case T__4:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case IDENT:
			case LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				expr_stmt();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				compound_stmt();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 3);
				{
				setState(95);
				if_stmt();
				}
				break;
			case WHILE:
				enterOuterAlt(_localctx, 4);
				{
				setState(96);
				while_stmt();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 5);
				{
				setState(97);
				for_stmt();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 6);
				{
				setState(98);
				return_stmt();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_stmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Expr_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterExpr_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitExpr_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitExpr_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expr_stmtContext expr_stmt() throws RecognitionException {
		Expr_stmtContext _localctx = new Expr_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_expr_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			expr(0);
			setState(102);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_stmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MiniCParser.WHILE, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public While_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterWhile_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitWhile_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitWhile_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_stmtContext while_stmt() throws RecognitionException {
		While_stmtContext _localctx = new While_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_while_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(WHILE);
			setState(105);
			match(T__4);
			setState(106);
			expr(0);
			setState(107);
			match(T__5);
			setState(108);
			stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_stmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MiniCParser.FOR, 0); }
		public List<Expr_stmtContext> expr_stmt() {
			return getRuleContexts(Expr_stmtContext.class);
		}
		public Expr_stmtContext expr_stmt(int i) {
			return getRuleContext(Expr_stmtContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public For_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterFor_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitFor_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitFor_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_stmtContext for_stmt() throws RecognitionException {
		For_stmtContext _localctx = new For_stmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_for_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(FOR);
			setState(111);
			match(T__4);
			setState(112);
			expr_stmt();
			setState(113);
			expr_stmt();
			setState(114);
			expr(0);
			setState(115);
			match(T__5);
			setState(116);
			stmt();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Compound_stmtContext extends ParserRuleContext {
		public List<Local_declContext> local_decl() {
			return getRuleContexts(Local_declContext.class);
		}
		public Local_declContext local_decl(int i) {
			return getRuleContext(Local_declContext.class,i);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public Compound_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterCompound_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitCompound_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitCompound_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Compound_stmtContext compound_stmt() throws RecognitionException {
		Compound_stmtContext _localctx = new Compound_stmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_compound_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__7);
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VOID || _la==INT) {
				{
				{
				setState(119);
				local_decl();
				}
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << WHILE) | (1L << IF) | (1L << FOR) | (1L << RETURN) | (1L << IDENT) | (1L << LITERAL))) != 0)) {
				{
				{
				setState(125);
				stmt();
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(131);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_declContext extends ParserRuleContext {
		public Type_specContext type_spec() {
			return getRuleContext(Type_specContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public Local_declContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterLocal_decl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitLocal_decl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitLocal_decl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Local_declContext local_decl() throws RecognitionException {
		Local_declContext _localctx = new Local_declContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_local_decl);
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133);
				type_spec();
				setState(134);
				match(IDENT);
				setState(135);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				type_spec();
				setState(138);
				match(IDENT);
				setState(139);
				match(T__1);
				setState(140);
				match(LITERAL);
				setState(141);
				match(T__0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				type_spec();
				setState(144);
				match(IDENT);
				setState(145);
				match(T__2);
				setState(146);
				match(LITERAL);
				setState(147);
				match(T__3);
				setState(148);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_stmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MiniCParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MiniCParser.ELSE, 0); }
		public If_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterIf_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitIf_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitIf_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_stmtContext if_stmt() throws RecognitionException {
		If_stmtContext _localctx = new If_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_if_stmt);
		try {
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				match(IF);
				setState(153);
				match(T__4);
				setState(154);
				expr(0);
				setState(155);
				match(T__5);
				setState(156);
				stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				match(IF);
				setState(159);
				match(T__4);
				setState(160);
				expr(0);
				setState(161);
				match(T__5);
				setState(162);
				stmt();
				setState(163);
				match(ELSE);
				setState(164);
				stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_stmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(MiniCParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Return_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterReturn_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitReturn_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitReturn_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_stmtContext return_stmt() throws RecognitionException {
		Return_stmtContext _localctx = new Return_stmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_return_stmt);
		try {
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				match(RETURN);
				setState(169);
				match(T__0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				match(RETURN);
				setState(171);
				expr(0);
				setState(172);
				match(T__0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public TerminalNode LITERAL() { return getToken(MiniCParser.LITERAL, 0); }
		public TerminalNode IDENT() { return getToken(MiniCParser.IDENT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MiniCParser.EQ, 0); }
		public TerminalNode NE() { return getToken(MiniCParser.NE, 0); }
		public TerminalNode LE() { return getToken(MiniCParser.LE, 0); }
		public TerminalNode GE() { return getToken(MiniCParser.GE, 0); }
		public TerminalNode AND() { return getToken(MiniCParser.AND, 0); }
		public TerminalNode OR() { return getToken(MiniCParser.OR, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(177);
				_la = _input.LA(1);
				if ( !(_la==IDENT || _la==LITERAL) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case 2:
				{
				setState(178);
				match(T__4);
				setState(179);
				expr(0);
				setState(180);
				match(T__5);
				}
				break;
			case 3:
				{
				setState(182);
				match(IDENT);
				setState(183);
				match(T__2);
				setState(184);
				expr(0);
				setState(185);
				match(T__3);
				}
				break;
			case 4:
				{
				setState(187);
				match(IDENT);
				setState(188);
				match(T__4);
				setState(189);
				args();
				setState(190);
				match(T__5);
				}
				break;
			case 5:
				{
				setState(192);
				((ExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
					((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(193);
				expr(5);
				}
				break;
			case 6:
				{
				setState(194);
				match(IDENT);
				setState(195);
				match(T__1);
				setState(196);
				expr(2);
				}
				break;
			case 7:
				{
				setState(197);
				match(IDENT);
				setState(198);
				match(T__2);
				setState(199);
				expr(0);
				setState(200);
				match(T__3);
				setState(201);
				match(T__1);
				setState(202);
				expr(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(214);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(212);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(206);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(207);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__14) | (1L << T__15) | (1L << T__16))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(208);
						((ExprContext)_localctx).right = expr(5);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(209);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(210);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__17) | (1L << T__18) | (1L << OR) | (1L << AND) | (1L << LE) | (1L << GE) | (1L << EQ) | (1L << NE))) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(211);
						((ExprContext)_localctx).right = expr(4);
						}
						break;
					}
					} 
				}
				setState(216);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniCListener ) ((MiniCListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniCVisitor ) return ((MiniCVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_args);
		int _la;
		try {
			setState(226);
			switch (_input.LA(1)) {
			case T__4:
			case T__9:
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case IDENT:
			case LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				expr(0);
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(218);
					match(T__6);
					setState(219);
					expr(0);
					}
					}
					setState(224);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3(\u00e7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\6\2&\n\2\r\2\16\2\'\3\3\3\3\5\3,\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4?\n\4\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7M\n\7\f\7\16\7P\13\7\3\7\3\7\5\7T\n\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b^\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\t"+
		"f\n\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\7\r{\n\r\f\r\16\r~\13\r\3\r\7\r\u0081\n\r\f\r\16\r"+
		"\u0084\13\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0099\n\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00a9\n\17\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\5\20\u00b1\n\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00cf\n\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\7\21\u00d7\n\21\f\21\16\21\u00da\13\21\3\22\3\22\3\22"+
		"\7\22\u00df\n\22\f\22\16\22\u00e2\13\22\3\22\5\22\u00e5\n\22\3\22\2\3"+
		" \23\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"\2\7\3\2\26\27\3\2#$\3\2"+
		"\f\20\4\2\f\r\21\23\4\2\24\25\35\"\u00f2\2%\3\2\2\2\4+\3\2\2\2\6>\3\2"+
		"\2\2\b@\3\2\2\2\nB\3\2\2\2\fS\3\2\2\2\16]\3\2\2\2\20e\3\2\2\2\22g\3\2"+
		"\2\2\24j\3\2\2\2\26p\3\2\2\2\30x\3\2\2\2\32\u0098\3\2\2\2\34\u00a8\3\2"+
		"\2\2\36\u00b0\3\2\2\2 \u00ce\3\2\2\2\"\u00e4\3\2\2\2$&\5\4\3\2%$\3\2\2"+
		"\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\3\3\2\2\2),\5\6\4\2*,\5\n\6\2+)\3"+
		"\2\2\2+*\3\2\2\2,\5\3\2\2\2-.\5\b\5\2./\7#\2\2/\60\7\3\2\2\60?\3\2\2\2"+
		"\61\62\5\b\5\2\62\63\7#\2\2\63\64\7\4\2\2\64\65\7$\2\2\65\66\7\3\2\2\66"+
		"?\3\2\2\2\678\5\b\5\289\7#\2\29:\7\5\2\2:;\7$\2\2;<\7\6\2\2<=\7\3\2\2"+
		"=?\3\2\2\2>-\3\2\2\2>\61\3\2\2\2>\67\3\2\2\2?\7\3\2\2\2@A\t\2\2\2A\t\3"+
		"\2\2\2BC\5\b\5\2CD\7#\2\2DE\7\7\2\2EF\5\f\7\2FG\7\b\2\2GH\5\30\r\2H\13"+
		"\3\2\2\2IN\5\16\b\2JK\7\t\2\2KM\5\16\b\2LJ\3\2\2\2MP\3\2\2\2NL\3\2\2\2"+
		"NO\3\2\2\2OT\3\2\2\2PN\3\2\2\2QT\7\26\2\2RT\3\2\2\2SI\3\2\2\2SQ\3\2\2"+
		"\2SR\3\2\2\2T\r\3\2\2\2UV\5\b\5\2VW\7#\2\2W^\3\2\2\2XY\5\b\5\2YZ\7#\2"+
		"\2Z[\7\5\2\2[\\\7\6\2\2\\^\3\2\2\2]U\3\2\2\2]X\3\2\2\2^\17\3\2\2\2_f\5"+
		"\22\n\2`f\5\30\r\2af\5\34\17\2bf\5\24\13\2cf\5\26\f\2df\5\36\20\2e_\3"+
		"\2\2\2e`\3\2\2\2ea\3\2\2\2eb\3\2\2\2ec\3\2\2\2ed\3\2\2\2f\21\3\2\2\2g"+
		"h\5 \21\2hi\7\3\2\2i\23\3\2\2\2jk\7\30\2\2kl\7\7\2\2lm\5 \21\2mn\7\b\2"+
		"\2no\5\20\t\2o\25\3\2\2\2pq\7\32\2\2qr\7\7\2\2rs\5\22\n\2st\5\22\n\2t"+
		"u\5 \21\2uv\7\b\2\2vw\5\20\t\2w\27\3\2\2\2x|\7\n\2\2y{\5\32\16\2zy\3\2"+
		"\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\u0082\3\2\2\2~|\3\2\2\2\177\u0081"+
		"\5\20\t\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0086\7\13"+
		"\2\2\u0086\31\3\2\2\2\u0087\u0088\5\b\5\2\u0088\u0089\7#\2\2\u0089\u008a"+
		"\7\3\2\2\u008a\u0099\3\2\2\2\u008b\u008c\5\b\5\2\u008c\u008d\7#\2\2\u008d"+
		"\u008e\7\4\2\2\u008e\u008f\7$\2\2\u008f\u0090\7\3\2\2\u0090\u0099\3\2"+
		"\2\2\u0091\u0092\5\b\5\2\u0092\u0093\7#\2\2\u0093\u0094\7\5\2\2\u0094"+
		"\u0095\7$\2\2\u0095\u0096\7\6\2\2\u0096\u0097\7\3\2\2\u0097\u0099\3\2"+
		"\2\2\u0098\u0087\3\2\2\2\u0098\u008b\3\2\2\2\u0098\u0091\3\2\2\2\u0099"+
		"\33\3\2\2\2\u009a\u009b\7\31\2\2\u009b\u009c\7\7\2\2\u009c\u009d\5 \21"+
		"\2\u009d\u009e\7\b\2\2\u009e\u009f\5\20\t\2\u009f\u00a9\3\2\2\2\u00a0"+
		"\u00a1\7\31\2\2\u00a1\u00a2\7\7\2\2\u00a2\u00a3\5 \21\2\u00a3\u00a4\7"+
		"\b\2\2\u00a4\u00a5\5\20\t\2\u00a5\u00a6\7\33\2\2\u00a6\u00a7\5\20\t\2"+
		"\u00a7\u00a9\3\2\2\2\u00a8\u009a\3\2\2\2\u00a8\u00a0\3\2\2\2\u00a9\35"+
		"\3\2\2\2\u00aa\u00ab\7\34\2\2\u00ab\u00b1\7\3\2\2\u00ac\u00ad\7\34\2\2"+
		"\u00ad\u00ae\5 \21\2\u00ae\u00af\7\3\2\2\u00af\u00b1\3\2\2\2\u00b0\u00aa"+
		"\3\2\2\2\u00b0\u00ac\3\2\2\2\u00b1\37\3\2\2\2\u00b2\u00b3\b\21\1\2\u00b3"+
		"\u00cf\t\3\2\2\u00b4\u00b5\7\7\2\2\u00b5\u00b6\5 \21\2\u00b6\u00b7\7\b"+
		"\2\2\u00b7\u00cf\3\2\2\2\u00b8\u00b9\7#\2\2\u00b9\u00ba\7\5\2\2\u00ba"+
		"\u00bb\5 \21\2\u00bb\u00bc\7\6\2\2\u00bc\u00cf\3\2\2\2\u00bd\u00be\7#"+
		"\2\2\u00be\u00bf\7\7\2\2\u00bf\u00c0\5\"\22\2\u00c0\u00c1\7\b\2\2\u00c1"+
		"\u00cf\3\2\2\2\u00c2\u00c3\t\4\2\2\u00c3\u00cf\5 \21\7\u00c4\u00c5\7#"+
		"\2\2\u00c5\u00c6\7\4\2\2\u00c6\u00cf\5 \21\4\u00c7\u00c8\7#\2\2\u00c8"+
		"\u00c9\7\5\2\2\u00c9\u00ca\5 \21\2\u00ca\u00cb\7\6\2\2\u00cb\u00cc\7\4"+
		"\2\2\u00cc\u00cd\5 \21\2\u00cd\u00cf\3\2\2\2\u00ce\u00b2\3\2\2\2\u00ce"+
		"\u00b4\3\2\2\2\u00ce\u00b8\3\2\2\2\u00ce\u00bd\3\2\2\2\u00ce\u00c2\3\2"+
		"\2\2\u00ce\u00c4\3\2\2\2\u00ce\u00c7\3\2\2\2\u00cf\u00d8\3\2\2\2\u00d0"+
		"\u00d1\f\6\2\2\u00d1\u00d2\t\5\2\2\u00d2\u00d7\5 \21\7\u00d3\u00d4\f\5"+
		"\2\2\u00d4\u00d5\t\6\2\2\u00d5\u00d7\5 \21\6\u00d6\u00d0\3\2\2\2\u00d6"+
		"\u00d3\3\2\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2"+
		"\2\2\u00d9!\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00e0\5 \21\2\u00dc\u00dd"+
		"\7\t\2\2\u00dd\u00df\5 \21\2\u00de\u00dc\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e5\3\2\2\2\u00e2\u00e0\3\2"+
		"\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00db\3\2\2\2\u00e4\u00e3\3\2\2\2\u00e5"+
		"#\3\2\2\2\23\'+>NS]e|\u0082\u0098\u00a8\u00b0\u00ce\u00d6\u00d8\u00e0"+
		"\u00e4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}