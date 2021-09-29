package springbootrestservice.excpetions;

import java.time.LocalDateTime;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ExceptionResponse {

	// appl specific error code
	private String errorCode;
	// for the user
	private String msg;
	private int resourceId;
	// https status code same as Http....
	
	private String status;
	// original exc msg from service/dao layer
	
	private String originalMsg;
	// time of the exception
	
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	private LocalDateTime excTime = LocalDateTime.now();
	// description of the error for human user
	private String moreInfo = "http://www.bende/moreinfo.html";

	public ExceptionResponse(String errorCode, String msg) {
		this.errorCode = errorCode;
		this.msg = msg;
	}

	public ExceptionResponse() {
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getExcTime() {
		return excTime;
	}

	public void setExcTime(LocalDateTime excTime) {
		this.excTime = excTime;
	}

	public String getOriginalMsg() {
		return originalMsg;
	}

	public void setOriginalMsg(String originalMsg) {
		this.originalMsg = originalMsg;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}

}
