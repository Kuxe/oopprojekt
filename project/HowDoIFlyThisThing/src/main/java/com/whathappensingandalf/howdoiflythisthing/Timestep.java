package com.whathappensingandalf.howdoiflythisthing;

public class Timestep {
	private double deltaTime;
	private long start;
	private long end;
	
	public void start() {
		start = System.nanoTime();
	}
	
	public void end() {
		end = System.nanoTime();
	}
	
	public void calculateDeltatime() {
		deltaTime = (end - start) / 1000000000.0;
	}
	
	public double getDelta() {
		return deltaTime;
	}
}
