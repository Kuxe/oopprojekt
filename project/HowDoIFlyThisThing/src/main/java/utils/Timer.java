package utils;

import java.util.Date;

public class Timer {
	private Date startTime;
	private int timerLength;
	
	public Timer(){
		new Timer(0);
	}
	
	public Timer(int intervalLength){
		timerLength=intervalLength;
		startTime=new Date(0);
	}
	
	public void start(){
		startTime=new Date();
	}
	
	public boolean isTimerDone(){
		if(this.timerLength>this.getTimeOfsettMillis()){
			return false;
		}else{
			return true;
		}
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
