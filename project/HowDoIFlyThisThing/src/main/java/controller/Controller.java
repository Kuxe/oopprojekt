package controller;

import java.util.Collections;
import java.util.List;

import com.whathappensingandalf.howdoiflythisthing.Gameworld;
import com.whathappensingandalf.howdoiflythisthing.IDrawable;

import View.View;

public class Controller {
	
	private Gameworld model;
	View view;
	
	public Controller(View v){
		view=v;
	}
	
	public synchronized void setRenderObjects(List<IDrawable> list){
		view.setRenderObjects(list);
	}
	
}
