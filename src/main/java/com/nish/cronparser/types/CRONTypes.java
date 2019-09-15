package com.nish.cronparser.types;

import java.util.function.Predicate;

import com.google.common.base.CharMatcher;

public enum CRONTypes {

	HYPHEN("Hyphen", "-", expression -> CharMatcher.is('-').matchesAnyOf(expression)),
	ASTERISK("Asterisk", "*", expression -> CharMatcher.is('*').matchesAnyOf(expression)),
	COMMA("Comma", ",", expression -> CharMatcher.is(',').matchesAnyOf(expression)),
	FORWARD_SLASH("Slash", "/", expression -> CharMatcher.is('/').matchesAnyOf(expression));

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
