package guo.cn.note.service;

public class PasswordNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = -5091877977634972295L;

	public PasswordNotFoundException() {
		super();
		
	}

	public PasswordNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public PasswordNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordNotFoundException(String message) {
		super(message);
		
	}

	public PasswordNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
