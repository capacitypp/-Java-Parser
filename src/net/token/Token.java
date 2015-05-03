package net.token;

public class Token {
	public final int TYPE_VARIABLE = 1;		//変数
	public final int TYPE_OPERATOR = 2;		//演算子
	public final int TYPE_NUMBER = 3;		//数字
	public final int TYPE_SEMICOLON = 4;	//セミコロン

	private String token;	//数式中のトークン
	private int type;		//型
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
}
