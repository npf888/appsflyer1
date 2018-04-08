package com.ami.common.schedule;

import org.apache.log4j.Logger;

import com.ami.weixin.course.schedule.Task;

/**
 * 所有 定时器
 * @author JavaServer
 *
 */
public  class Schedule {
	private final static Logger logger = Logger.getLogger(Schedule.class);
	private Task task;
	private long timeInterval1;
	public Schedule(Task taskIn,long timeInterval1In){
		this.task=taskIn;
		this.timeInterval1=timeInterval1In;
	}
	public void start(){
		final long time = timeInterval1;  
		Runnable runnable = new Runnable() {  
			@Override
			public void run() {  
				while (true) {  
					try {  
						Thread.sleep(time); 
						task.execute();
					} catch (InterruptedException e) {  
						e.printStackTrace();  
					}  
				}  
			}  
		};  
		logger.info("Schedule thread init  start");
		Thread thread = new Thread(runnable);  
		thread.start();  
		logger.info("Schedule thread init  success");
	}
}
