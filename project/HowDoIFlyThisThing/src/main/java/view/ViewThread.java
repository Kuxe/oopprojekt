package view;

public class ViewThread implements Runnable{
	private View view;
	
	public ViewThread(Object lock, boolean fullscreen){
		view=new View("How do i fly this thing?", lock, fullscreen);
	}
	
	
	public void run(){
		view.start();
	}
	public void stopView(){
		view.endUpdate();
	}
	
	public View getView(){
		return view;
	}
	public boolean isReady() {
		return view.isReady();
	}
	
	public void createExplosion(javax.vecmath.Point2f position){
		if(view.isReady()){
			view.createExplosion(position);
		}
	}
	
	public void createSparkle(javax.vecmath.Point2f position){
		if(view.isReady()){
			view.createSparkle(position);
		}
	}
	
	public void createAsteroidExplosion(javax.vecmath.Point2f position){
		if(view.isReady()){
			view.createAsteroidExplosion(position);
		}
	}
}
