package com.example.demo.biz.impl;

import com.example.demo.biz.ExecutorBiz;
import com.example.demo.executor.JobExecutor;
import com.example.demo.handler.IJobHandler;
import com.example.demo.model.ResultR;
import com.example.demo.thread.JobThread;

public class ExecutorBizImpl implements ExecutorBiz{

	@Override
	public ResultR<String> run(String param) {
		// load old：jobHandler + jobThread
        JobThread jobThread = JobExecutor.loadJobThread(param);
        IJobHandler jobHandler = jobThread!=null?jobThread.getJobHandler():null;

        // new jobhandler
        IJobHandler newJobHandler = JobExecutor.loadJobHandler(param);

        // valid old jobThread
        if (jobThread!=null && jobHandler!=newJobHandler) {
            // change handler, need kill old thread
            jobThread = null;
            jobHandler = null;
        }

        // valid handler
        if (jobHandler == null) {
            jobHandler = newJobHandler;
            if (jobHandler == null) {
                return new ResultR<String>(ResultR.FAIL_CODE, "job handler [" + param + "] not found.");
            }
        }
		return null;
	}

	@Override
	public ResultR<String> kill(String jobId) {
		// TODO Auto-generated method stub
		return null;
	}

}
