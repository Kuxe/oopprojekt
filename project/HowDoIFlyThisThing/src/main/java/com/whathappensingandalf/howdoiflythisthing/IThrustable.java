package com.whathappensingandalf.howdoiflythisthing;

/**
 *
 * @author Martin Nilsson
 * 
 * A interface for all classes that are supposed to use thrusters to change 
 * acceleration and velocity.
 */
public interface IThrustable {
	
	/**
	 * Updates this objects thrust by recalculating it.
	 */
	public void calculateThrust(Timestep timestep);
}
