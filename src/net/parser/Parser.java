package net.parser;

import net.lexer.Lexer;
import net.token.Token;

public class Parser {

	/**
	 *
	 * @param numericalFormula : 数式の文字列．"a=b+c"など．
	 */
	public Parser(String numericalFormula) {
		System.out.println(numericalFormula);
		Lexer lexer = new Lexer(numericalFormula);
		Token[] tokens = lexer.getTokens();
		System.out.println("token num : " + tokens.length);
		for (Token token : tokens) {
			System.out.println(token.getToken() + " : " + token.getType());
		}
	}

	public static void main(String[] args) {
		new Parser("k=b+(c*4+0.5)/4.0;");
	}

}
