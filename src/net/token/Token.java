package net.token;

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
	 * 与えられた数式の先頭のトークンのトークン型を判定する
	 *
	 * @param numericalFormula : 数式文字列
	 * @return トークン型
	 */
	private static int getTokenType(String numericalFormula) {
		//先頭の1文字を取り出す
		String first = numericalFormula.substring(0, 1);
		//数字かどうかの判定
		if (isNumber(first))
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
	 * 数式の先頭にあるトークンを切り出す
	 *
	 * @param numericalFormula : 数式文字列
	 * @return 数式の先頭にあるトークン
	 */
	private static Token getOneToken(String numericalFormula) {
		int tokenType = getTokenType(numericalFormula);
		System.out.println("tokenType : " + tokenType);
/*
		switch (tokenType) {
		case TYPE_VARIABLE:
		}
*/
		return null;
	}

	public static Token[] tokenize(String numericalFormula) {
		getOneToken(numericalFormula);
		return null;
	}
}
