package net.token;

public class Token {
	public final int TYPE_VARIABLE = 1;		//�ϐ�
	public final int TYPE_OPERATOR = 2;		//���Z�q
	public final int TYPE_NUMBER = 3;		//���l
	public final int TYPE_SEMICOLON = 4;	//�Z�~�R����

	private String token;	//�������̕�����(�g�[�N��)
	private int type;		//�g�[�N���^
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
