package View;

public class ViewThred extends Thread implements Runnable{
	public View v;
	
	public ViewThred(){
		v=new View("How do i fly this?");
	}
	
//	public void start(){
//		super.start();
//		
//	
//	}
	
	public void run(){
		v.start();
	}
	
	public View getView(){
		return v;
	}
	
	
}
