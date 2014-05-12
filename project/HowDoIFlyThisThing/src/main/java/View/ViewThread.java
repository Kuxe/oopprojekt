package View;

public class ViewThread extends Thread implements Runnable{
	private View view;
	
	public ViewThread(Object lock, boolean fullscreen){
		view=new View("How do i fly this?", lock, fullscreen);
	}
	
	
	public void run(){
		view.start();
	}
	public void exit(){
		view.stop();
	}
	
	public View getView(){
		return view;
	}
	public boolean isReady() {
		return view.isReady();
	}
}
