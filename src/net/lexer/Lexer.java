package net.lexer;

import net.token.Token;

public class Lexer {
	Token[] tokens;
	public Lexer(String numericalFormula) {
		tokens = Token.tokenize(numericalFormula);
	}
	public Token[] getTokens() {
		return tokens;
	}
}
