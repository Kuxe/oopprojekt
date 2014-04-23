package utils;

import java.util.Date;

public class Timer {
	Date startTime;
	int timerLength;
	
	public Timer(){
		new Timer(0);
	}
	
	public Timer(int intervalLength){
		timerLength=intervalLength;
	}
	
	public void start(){
		startTime=new Date();
	}
	
	private int getTimeOfsettMillis(){
		Date refTime=new Date();
		return (int)(startTime.getTime()-refTime.getTime());
	}
	
}
