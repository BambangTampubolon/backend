package com.test.viewmodel;

public class InfoViewModel {
	public int status;
	public String message;
	public Object detailmessage;
	public int executiontime;
	public Object detailInfo;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDetailmessage() {
		return detailmessage;
	}

	public void setDetailmessage(Object detailmessage) {
		this.detailmessage = detailmessage;
	}

	public int getExecutiontime() {
		return executiontime;
	}

	public void setExecutiontime(int executiontime) {
		this.executiontime = executiontime;
	}

	public Object getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(Object detailInfo) {
		this.detailInfo = detailInfo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorDetail [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", detailmessage=");
		builder.append(detailmessage);
		builder.append(", executiontime=");
		builder.append(executiontime);
		builder.append(", detailInfo=");
		builder.append(detailInfo);
		builder.append("]");
		return builder.toString();
	}
}
