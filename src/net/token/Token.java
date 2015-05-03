package net.token;

import java.util.Vector;

public class Token {
	public static final int TYPE_VARIABLE = 1;		//変数
	public static final int TYPE_OPERATOR = 2;		//演算子
	public static final int TYPE_NUMBER = 3;		//数字
	public static final int TYPE_SEMICOLON = 4;	//セミコロン
	public static final int TYPE_OPEN_PARENTHESIS = 5; //「(」
	public static final int TYPE_CLOSE_PARENTHESIS = 6; //「)」

	public static final String OPERATORS = "+-*/=";	//演算子一覧

	private String token;	//数式中のトークン
	private int type;		//トークン型
	public Token(String token, int type) {
		this.token = token;
		this.type = type;
	}
	//setter and getter
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	/**
	 *
	 * 与えられた文字列が数字かどうかを判定する
	 *
	 * @param string : 判定対象の文字列
	 * @return 数字 : 1 / 数字でない : 2
	 */
	private static boolean isNumber(String string){
		try {
			@SuppressWarnings("unused")
			Integer integer = new Integer(string);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/**
	 *
	 * 与えられた数式の任意の文字のトークン型を判定する
	 *
	 * @param numericalFormula : 数式文字列
	 * @param idx : 文字の添え字
	 * @return トークン型
	 */
	private static int getTokenType(String numericalFormula, int idx) {
		//先頭の1文字を取り出す
		String first = numericalFormula.substring(idx, idx + 1);
		//数字かどうかの判定
		if (isNumber(first) || first.matches("\\."))
			return TYPE_NUMBER;
		if (OPERATORS.contains(first))
			return TYPE_OPERATOR;
		if (first.matches(";"))
			return TYPE_SEMICOLON;
		if (first.matches("\\("))
			return TYPE_OPEN_PARENTHESIS;
		if (first.matches("\\)"))
			return TYPE_CLOSE_PARENTHESIS;
		//どれにも当てはまらなかったら「変数」
		return TYPE_VARIABLE;
	}
	/**
	 *
	 * 与えられた数式の先頭のトークンのトークン型を判定する
	 *
	 * @param numericalFormula : 数式文字列
	 * @return トークン型
	 */
	private static int getTokenType(String numericalFormula) {
		return getTokenType(numericalFormula, 0);
	}
	/**
	 *
	 * 数式の先頭にある変数トークンを切り出す
	 *
	 * @param numericalFormula : 数式文字列
	 * @return 数式の先頭にある変数トークン
	 */
	private static Token getOneVariableToken(String numericalFormula) {
		for (int i = 1; i < numericalFormula.length(); i++)
			if (getTokenType(numericalFormula, i) != TYPE_VARIABLE)
				return new Token(numericalFormula.substring(0, i), TYPE_VARIABLE);
		return new Token(numericalFormula.substring(0, numericalFormula.length()), TYPE_VARIABLE);
	}
	/**
	 *
	 * 数式の先頭にある数字トークンを切り出す
	 *
	 * @param numericalFormula : 数式文字列
	 * @return 数式の先頭にある数字トークン
	 */
	private static Token getOneNumberToken(String numericalFormula) {
		boolean floatingPoint = false;
		for (int i = 1; i < numericalFormula.length(); i++) {
			//小数点が2つ含まれる場合の対策
			if (numericalFormula.substring(i, i + 1).matches("\\.")) {
				if (floatingPoint)
					return new Token(numericalFormula.substring(0, i), TYPE_NUMBER);
				floatingPoint = true;
			}
			if (getTokenType(numericalFormula, i) != TYPE_NUMBER)
				return new Token(numericalFormula.substring(0, i), TYPE_NUMBER);
		}
		return new Token(numericalFormula.substring(0, numericalFormula.length()), TYPE_NUMBER);
	}
	/**
	 *
	 * 数式の先頭にあるトークンを切り出す
	 *
	 * @param numericalFormula : 数式文字列
	 * @return 数式の先頭にあるトークン
	 */
	private static Token getOneToken(String numericalFormula) {
		if (numericalFormula == null || numericalFormula.length() == 0)
			return null;
		int tokenType = getTokenType(numericalFormula);

		switch (tokenType) {
		case TYPE_VARIABLE:
			return getOneVariableToken(numericalFormula);
		case TYPE_OPERATOR:
		case TYPE_SEMICOLON:
		case TYPE_OPEN_PARENTHESIS:
		case TYPE_CLOSE_PARENTHESIS:
			return new Token(numericalFormula.substring(0, 1), tokenType);
		case TYPE_NUMBER:
			return getOneNumberToken(numericalFormula);
		}

		return null;
	}

	public static Token[] tokenize(String numericalFormula) {
		Vector<Token> tokens = new Vector<Token>();
		Token token;
		while ((token = getOneToken(numericalFormula)) != null) {
			tokens.add(token);
			numericalFormula = numericalFormula.substring(token.getToken().length());
		}
		Token[] tokenArray = new Token[tokens.size()];
		tokens.toArray(tokenArray);
		return tokenArray;
	}
}
