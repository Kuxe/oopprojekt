package View;

public class ViewThread extends Thread implements Runnable{
	public View v;
	
	public ViewThread(Object lock){
		v=new View("How do i fly this?", lock);
	}
	
	
	public void run(){
		v.start();
	}
	
	public View getView(){
		return v;
	}
	public boolean isReady() {
		return v.isReady();
	}
}
