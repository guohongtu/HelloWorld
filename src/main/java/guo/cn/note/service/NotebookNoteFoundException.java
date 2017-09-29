package guo.cn.note.service;

public class NotebookNoteFoundException extends RuntimeException {

	
	private static final long serialVersionUID = -306627373767688061L;

	public NotebookNoteFoundException() {
		super();
		
	}

	public NotebookNoteFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public NotebookNoteFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NotebookNoteFoundException(String message) {
		super(message);
		
	}

	public NotebookNoteFoundException(Throwable cause) {
		super(cause);
		
	}
	
}
