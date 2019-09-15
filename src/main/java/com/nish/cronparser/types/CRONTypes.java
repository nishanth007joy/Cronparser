package com.nish.cronparser.types;

import java.util.function.Predicate;

import com.google.common.base.CharMatcher;

public enum CRONTypes {

	HYPHEN("Hyphen", "-", expression -> CharMatcher.anyOf("-").matchesAllOf(expression)),
	ASTERISK("Asterisk", "*", expression -> CharMatcher.anyOf("*").matchesAllOf(expression)),
	COMMA("Comma", ",", expression -> CharMatcher.anyOf(",").matchesAllOf(expression)),
	FORWARD_SLASH("Slash", "/", expression -> CharMatcher.anyOf("/").matchesAllOf(expression));

	private String type;
	private String typeSymbol;

	private Predicate<String> checkType;

	public String getType() {
		return type;
	}

	public String getTypeSymbol() {
		return typeSymbol;
	}

	public Predicate<String> getCheckType() {
		return checkType;
	}

	CRONTypes(String type, String typeSymbol, Predicate<String> checkType) {
		this.type = type;
		this.typeSymbol = typeSymbol;
		this.checkType = checkType;
	}
}
