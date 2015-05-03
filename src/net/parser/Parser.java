package net.parser;

import java.util.Stack;
import java.util.Vector;

import net.lexer.Lexer;
import net.token.Token;

public class Parser {
	Token[] tokens;

	/**
	 *
	 * @param numericalFormula : 数式の文字列．"a=b+c"など．
	 */
	public Parser(String numericalFormula) {
		System.out.println(numericalFormula);
		Lexer lexer = new Lexer(numericalFormula);
		tokens = lexer.getTokens();
	}

	/**
	 *
	 * 演算子の優先順位を評価する
	 *
	 * @param operator : 演算子のトークン
	 * @return	優先度(小さいほど優先)
	 */
	private int evaluateOperator(Token operator) {
		String operatorString = operator.getToken();
		if (operatorString.matches("\\*|/"))
			return 3;
		if (operatorString.matches("\\+|-"))
			return 4;
		if (operatorString.matches("="))
			return 13;
		if (operatorString.matches("\\(|\\)"))
			return 100;
		return -1;
	}

	/**
	 *
	 * 逆ポーランド記法のトークン配列を取得する
	 *
	 * @return 逆ポーランド記法のトークン配列
	 */
	public Token[] getRPN() {
		Vector<Token> rpn = new Vector<Token>();
		Stack<Token> stack = new Stack<Token>();
		for (Token token : tokens) {
			//RPN表記とスタックの途中経過を表示する
			/*
			System.out.println("Token : " + token.getToken());
			System.out.print("Stack : ");
			for (Token token2 : stack)
				System.out.print("[" + token2.getToken() + "]");
			System.out.println();
			System.out.print("RPN : ");
			for (Token token2 : rpn)
				System.out.print("[" + token2.getToken() + "]");
			System.out.println();
			*/
			switch (token.getType()) {
			case Token.TYPE_NUMBER:
			case Token.TYPE_VARIABLE:
				rpn.add(token);
				break;
			case Token.TYPE_OPERATOR:
				while (!stack.empty()) {
					Token token2 = stack.peek();
					if (evaluateOperator(token) < evaluateOperator(token2)) {
						break;
					}
					rpn.add(stack.pop());
				}
				stack.push(token);
				break;
			case Token.TYPE_OPEN_PARENTHESIS:
				stack.push(token);
				break;
			case Token.TYPE_CLOSE_PARENTHESIS:
				while (true) {
					if (stack.empty())
						return null;
					Token token2 = stack.peek();
					if (token2.getType() == Token.TYPE_OPEN_PARENTHESIS)
						break;
					rpn.add(stack.pop());
				}
				stack.pop();
				break;
			}
		}
		while (!stack.empty()) {
			Token token = stack.pop();
			int tokenType = token.getType();
			//かっこがあったらエラー
			if (tokenType == Token.TYPE_OPEN_PARENTHESIS || tokenType == Token.TYPE_CLOSE_PARENTHESIS)
				return null;
			rpn.add(token);
		}
		if (tokens[tokens.length - 1].getType() == Token.TYPE_SEMICOLON)
			rpn.add(tokens[tokens.length - 1]);
		Token[] rpnTokens = new Token[rpn.size()];
		rpn.toArray(rpnTokens);
		return rpnTokens;
	}

	public static void main(String[] args) {
		Parser parser = new Parser("a=b+(c*4+0.5)/4.0;");
		Token[] rpnTokens = parser.getRPN();
		if (rpnTokens == null) {
			System.out.println("Error");
			return;
		}
		for (Token token : rpnTokens) {
			System.out.print("[" + token.getToken() + "]");
		}
		System.out.println();
	}

}
