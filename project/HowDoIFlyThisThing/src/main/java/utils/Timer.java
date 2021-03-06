package utils;

import java.util.Date;

public class Timer implements ITimer{
	private Date startTime;
	private int timerLength;
	
	public Timer(){
		this(0);
	}
	
	public Timer(int intervalLength){
		timerLength=intervalLength;
		startTime=new Date(0);
	}
	
	public void start(){
		startTime=new Date();
	}
	
	public boolean isTimerDone(){
        return !(timerLength > getTimeOfsettMillis());
	}
	
	private long getTimeOfsettMillis(){
		Date refTime=new Date();
		return (refTime.getTime()-startTime.getTime());
	}
	
	public void setTimerLength(int length){
		timerLength=length;
	}
	
	public int getTimerLength(){
		return timerLength;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
}
