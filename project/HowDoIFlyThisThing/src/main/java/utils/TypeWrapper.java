package utils;

/**
 *
 * @author Martin Nilsson
 */
public class TypeWrapper {
	private float value;
	
	public TypeWrapper(float value){
		this.value=value;
	}
	public void setValue(float newValue){
		this.value = newValue;
	}
	public float getValue(){
		return this.value;
	}
}
