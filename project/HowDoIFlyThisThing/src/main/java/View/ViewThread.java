package View;

public class ViewThread extends Thread implements Runnable{
	public View v;
	
	public ViewThread(){
		v=new View("How do i fly this?");
	}
	
	
	public void run(){
		v.start();
	}
	
	public View getView(){
		return v;
	}
	
	
}
