package com.nish.cronparser.exception;

public class ParseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParseException(ErrorCode errorCode) {
		super(errorCode.getErrorMessage());
	}

	public ParseException(ErrorCode errorCode, Throwable throwable) {
		super(errorCode.getErrorMessage(), throwable);
	}

	public enum ErrorCode {

		NOT_VALID_EXPRESSION("PARSE_ERROR_1", "Not a valid expression");

		private String errorCode;

		private String errorMessage;

		public String getErrorCode() {
			return errorCode;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		ErrorCode(String errorCode, String errorMessage) {
			this.errorCode = errorCode;
			this.errorMessage = errorMessage;
		}
	}

}
