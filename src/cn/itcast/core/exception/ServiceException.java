package cn.itcast.core.exception;

public class ServiceException extends SysException {

	public ServiceException() {
		super("业务操作失败");
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
