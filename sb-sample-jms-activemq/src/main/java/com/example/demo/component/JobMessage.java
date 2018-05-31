package com.example.demo.component;

import java.io.Serializable;

public class JobMessage implements Serializable {

	/**
	 * 任务类型一
	 */
	public static final int JOBTYPE_1 = 1;

	private static final long serialVersionUID = 1919952496132667529L;

	private int jobType;
	
	private String param;

	public int getJobType() {
		return jobType;
	}

	public void setJobType(int jobType) {
		this.jobType = jobType;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "JobType:" + jobType + " Param:" + param;
	}
}