package net.parser;

import net.token.Token;

public class Parser {

	/**
	 *
	 * @param numericalFormula : 数式の文字列．"a=b+c"など．
	 */
	public Parser(String numericalFormula) {
		System.out.println(numericalFormula);
		Token.tokenize("k=b+c");
	}

	public static void main(String[] args) {
		new Parser("a=b+c");
	}

}
