package net.parser;

public class Parser {

	/**
	 *
	 * @param numericalFormula : ”®‚Ì•¶š—ñD"a=b+c"‚È‚ÇD
	 */
	public Parser(String numericalFormula) {
		System.out.println(numericalFormula);
	}

	public static void main(String[] args) {
		System.out.println("hello, world!");
		new Parser("a=b+c");
	}

}
