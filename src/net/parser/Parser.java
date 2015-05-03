package net.parser;

public class Parser {

	/**
	 *
	 * @param numericalFormula : 数式の文字列．"a=b+c"など．
	 */
	public Parser(String numericalFormula) {
		System.out.println(numericalFormula);
	}

	public static void main(String[] args) {
		System.out.println("hello, world!");
		new Parser("a=b+c");
	}

}
