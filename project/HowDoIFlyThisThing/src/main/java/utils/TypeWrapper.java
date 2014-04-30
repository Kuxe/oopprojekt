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
	public boolean equals(Object rhs){
		if(rhs==this){
			return true;
		}else if(!(rhs instanceof TypeWrapper)){
			return false;
		}else{
			TypeWrapper other = (TypeWrapper)rhs;
			return this.value==other.value;
		}
	}
}
