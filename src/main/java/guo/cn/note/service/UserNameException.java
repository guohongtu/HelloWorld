package guo.cn.note.service;



public class UserNameException extends RuntimeException {

	private static final long serialVersionUID = -1758875551791410048L;

	public UserNameException() {
		super();
	}

	public UserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UserNameException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public UserNameException(String message) {
		super(message);
	
	}

	public UserNameException(Throwable cause) {
		super(cause);
		
	}

	

}
