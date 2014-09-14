package graphAlg;

public interface HeapElement<X> extends Comparable<X>{
	
	public void setKey( int key );
	
	public int getHeapIndex();
	
	public void setHeapIndex( int i );
	
}
