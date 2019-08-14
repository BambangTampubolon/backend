package com.test.manager.impl;

import java.util.Date;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

import com.test.viewmodel.InfoViewModel;


public class ManagerImpl {
	private static final Logger logger = Logger.getLogger(ManagerImpl.class);

	private InfoViewModel info;
	private int totalRow;
	private String errorMessage;
	protected int statusCode;
	protected String statusMessage;
	protected int generatedID;
	protected long hashing;

	public long getHashing() {
		if (hashing < 1)
			this.hashing = new Date().getTime();
		return hashing;
	}

	public void setHashing(long hashing) {
		this.hashing = hashing;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getGeneratedID() {
		return generatedID;
	}

	public void setGeneratedID(int generatedID) {
		this.generatedID = generatedID;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public InfoViewModel getInfo() {
		return info;
	}

	public void setInfo(InfoViewModel info) {
		this.info = info;
	}

	protected void setResponseSp(String resp) {
		InfoViewModel errorDetail = new InfoViewModel();
		if (resp.startsWith("00")) {
			errorDetail.setMessage(HttpStatus.OK.name());
			errorDetail.setStatus(HttpStatus.OK.value());
			errorDetail.setDetailInfo(HttpStatus.OK);
			// Get content message from SP
			errorDetail.setDetailmessage(resp.substring(resp.indexOf("||") + 2));
		} else {
			String errMsg = resp.substring(resp.indexOf("||") + 2);
			logger.error("(" + getHashing() + ") " + "Err Msg: " + errMsg);

			errorDetail.setMessage(HttpStatus.CONFLICT.name());
			errorDetail.setStatus(HttpStatus.CONFLICT.value());
			errorDetail.setDetailInfo(HttpStatus.CONFLICT);
			// Get content message from SP
			errorDetail.setDetailmessage(errMsg);
		}
		this.setInfo(errorDetail);
		this.set(resp);
	}

	public void set(String resultSP) {
		String[] parts = resultSP.split(Pattern.quote("||"));
		// [0] = status (Code)
		this.setStatusCode(Integer.parseInt(parts[0].toString()));
		// [1] = status (Value)
		this.setStatusMessage(parts[1].toString());
		// [2] = generated ID
		this.setGeneratedID(Integer.parseInt(parts[2].toString()));
	}

	public void setInfoConflict(String errMsg) {
		logger.error("(" + getHashing() + ") " + "Err Msg: " + errMsg);

		InfoViewModel info = new InfoViewModel();

		info.setMessage(HttpStatus.CONFLICT.name());
		info.setStatus(HttpStatus.CONFLICT.value());
		info.setDetailInfo(HttpStatus.CONFLICT);
		info.setDetailmessage(errMsg);

		setInfo(info);
	}

	public void setInfoOk(String msg) {
		InfoViewModel info = new InfoViewModel();

		info.setMessage(HttpStatus.OK.name());
		info.setStatus(HttpStatus.OK.value());
		info.setDetailInfo(HttpStatus.OK);
		info.setDetailmessage(msg);

		setInfo(info);
	}
}
