package springbootrestservice.excpetions;

public class ResourceNotFoundException extends RuntimeException {;

	private String message;
	
	public ResourceNotFoundException() {};
	
	public ResourceNotFoundException(String msg) {
		super(msg);
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
