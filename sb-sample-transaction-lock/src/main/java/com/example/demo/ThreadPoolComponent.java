package com.example.demo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class ThreadPoolComponent {
	private static final int MAX_SIZE = 10 ;
    private static final int CORE_SIZE = 10;
    private static final int SECOND = 1000;

    private ThreadPoolExecutor executor ;

    public ThreadPoolComponent(){
        executor = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, SECOND, TimeUnit.MICROSECONDS,new LinkedBlockingQueue<Runnable>()) ;
    }

    public void submit(Thread thread){
        executor.submit(thread) ;
    }
}
