package cn.itcast.core.exception;

public abstract class SysException extends Exception {

	private String errorMsg;

	public SysException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysException(String message, Throwable cause) {
		super(message, cause);
		errorMsg=message;
		// TODO Auto-generated constructor stub
	}

	public SysException(String message) {
		super(message);
		errorMsg=message;
		// TODO Auto-generated constructor stub
	}

	public SysException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
