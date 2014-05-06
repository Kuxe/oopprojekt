package View;

public class ViewThread extends Thread implements Runnable{
	public View v;
	
	boolean isReady = false;
	Object lock;
	
	public ViewThread(Object lock){
		this.lock = lock;
		v=new View("How do i fly this?", lock);
	}
	
	
	public void run(){
		synchronized(lock) {
			lock.notifyAll();
		}
		v.start();
	}
	
	public View getView(){
		return v;
	}
	public boolean isReady() {
		return v.isReady();
	}
}
