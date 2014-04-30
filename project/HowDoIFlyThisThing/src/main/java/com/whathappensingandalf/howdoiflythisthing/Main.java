package com.whathappensingandalf.howdoiflythisthing;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import com.esotericsoftware.kryonet.Server;

import controller.Controller;
import View.View;
/**
 * 
 * @author Kuxe
 *
 *	Main-class with main-method
 */
public class Main {
	public static void main(String argc[]){
		
		Controller controller;
		
		if(argc.length == 1) {
			controller = new Controller(argc[0]);
		} else {
			controller = new Controller();
		}
		controller.start();
	}
}