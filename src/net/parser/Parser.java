package net.parser;

import net.token.Token;

public class Parser {

	/**
	 *
	 * @param numericalFormula : 数式の文字列．"a=b+c"など．
	 */
	public Parser(String numericalFormula) {
		System.out.println(numericalFormula);
		Token[] tokens = Token.tokenize("k=b+(c*4+0.5)/4.0;");
		System.out.println("token num : " + tokens.length);
		for (Token token : tokens) {
			System.out.println(token.getToken() + " : " + token.getType());
		}
	}

	public static void main(String[] args) {
		new Parser("a=b+c");
	}

}
