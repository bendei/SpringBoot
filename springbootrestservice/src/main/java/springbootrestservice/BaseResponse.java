package springbootrestservice;

public class BaseResponse {
	
	 private String status;
	    private int code;

	    public String getstatus() {
	    return status;
	    }

	    public void setstatus(String status) {
	    this.status = status;
	    }

	    public int getcode() {
	    return code;
	    }

	    public void setcode(int code) {
	    this.code = code;
	    }


}
