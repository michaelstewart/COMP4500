package graphAlg;

public class InfiniteInteger {
	
	private int value;
	private boolean finite;
	
	public InfiniteInteger( int value ) {
		this.value = value;
		this.finite = true;
	}
	
	public InfiniteInteger() {
		this.finite = false;
	}
	
	public boolean isFinite() {
		return finite;
	}
	
	public int getValue() {
		return value;
	}
	
	public void add( int i ) {
		if( finite ) {
			this.value += i;
		}
	}
	
	public int compareTo( InfiniteInteger other ) {
		if( finite && (!other.isFinite() || value < other.getValue()) ) {
			return -1;
		} else if( other.isFinite() && (!finite || value > other.getValue()) ) {
			return 1;
		} else { 
			return 0;
		}	
	}
}
