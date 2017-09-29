package guo.cn.note.service;

public class NoteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoteNotFoundException() {
		super();
		
	}

	public NoteNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public NoteNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NoteNotFoundException(String message) {
		super(message);
	}

	public NoteNotFoundException(Throwable cause) {
		super(cause);
	
	}
	

}
