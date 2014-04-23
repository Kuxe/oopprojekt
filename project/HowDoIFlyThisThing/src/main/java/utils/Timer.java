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
	
}
