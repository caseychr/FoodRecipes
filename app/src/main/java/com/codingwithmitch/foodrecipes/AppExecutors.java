package com.codingwithmitch.foodrecipes;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * We need to create this Executors class to run Retrofit calls on background threads.
 */
public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }

    /**
     * Using ScheduledExecutorService to add extra functionality to the executors themselves. We're creating a pool
     * of 3 threads to do all the work in the application.
     *
     * What is an Executor? A thing used to execute runnable tasks to run on a thread (main thread or background thread)
     * EXAMPLE:
     * private Executor mBackgroundExecutor = Executors.newSingleThreadExecutor();
     * public AppExecutors(){mBackgroundExecutor.execute(Runnable command)}
     * The above creates a separate thread and then runs a Runnable command specified in execute();
     *
     * What is a ScheduledExecutorService? It's a service that can schedule commands to run on a given delay
     * */
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO(){
        return mNetworkIO;
    }
}
