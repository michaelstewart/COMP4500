package graphAlg;

import java.util.List;
import java.util.ArrayList;

/** Priority queue implementation as a binary heap in an array */
public class PriorityQueue<X extends HeapElement<X>> {

	/* Data type invariant:
	 * for all i: 2..heap.size() . heap[i DIV 2] <= heap[i] 
	 */
	private List<X> heap;

	public PriorityQueue( int size ) {
		super();
		heap = new ArrayList<X>(size+1);
		/* Add dummy element at position 0 as heap indices start at 1 */
		heap.add(null);
	}
	/** The heap is empty if it only contains the dummy element at 0 */
	public boolean isEmpty() {
		return heap.size() == 1;
	}
	/** Update the heap at position i to contain element u and
	 * update u's heap index to be i */
	private void heapUpdate( int i, X u ) {
		heap.set(i,	u);
		u.setHeapIndex(i);
	}
	/** The element u has had it value decreased and needs to be sifted
	 * down the heap to re-establish the heap data type invariant.
	 */
	private void siftDown( X u ) {
		int i = u.getHeapIndex();
		while( i != 1 && u.compareTo(heap.get(i/2) ) < 0 ) {
			// System.out.println( "heap(" + i/2 + ") = " + heap.get(i/2) );
			heapUpdate(i, heap.get(i/2));
			i = i/2;
		}
		heapUpdate(i, u);
	}
	/** Add a new element to the end of the heap and sift it down to
	 * re-establish the data type invariant.
	 */
	public void add( X u ) {
		u.setHeapIndex(heap.size());
		heap.add( u );
		siftDown( u ); 
	}
	/** The minimum value in the heap is always at position 1 */
	public X findMin() {
		return heap.get(1);
	}
	/** Decrease the key of element u to newKey and sift it down to 
	 * re-establish the data type invariant.
	 * @requires newKey <= u.getKey()
	 */
	public void decreaseKey( X u, int newKey ) {
		System.out.println( "DecreaseKey " + u + " to " + newKey );
		u.setKey(newKey);
		siftDown( u );
		// System.out.println( "decreaseKey Min " + heap.get(1) );
	}
	
	private void siftUp( int i ) {
		while( 2*i < heap.size() ) {
			int j = 2*i;
			if( j+1 < heap.size() && 
					heap.get(j+1).compareTo(heap.get(j)) < 0 ) {
				j = j+1;
			}
			if( heap.get(i).compareTo(heap.get(j)) > 0 ) {
				X temp = heap.get(i);
				heapUpdate(i, heap.get(j) );
				heapUpdate(j,  temp);
				i = j;
			} else {
				break;
			}
		}
	}

	public void deleteMin() {
		X last = heap.remove(heap.size()-1);
		if( isEmpty() ) {
			return;
		}
		heapUpdate(1, last);
		siftUp( 1 );
		// System.out.println( "deleteMin Min " + heap.get(1) );
	}


	public boolean checkInvariant() {
		boolean isHeap = true;
		for( int i = 2; i < heap.size(); i++ ) {
			if( heap.get(i/2).compareTo(heap.get(i)) > 0 ) {
				isHeap = false;
				System.out.println( "Not heap at " + 
						i/2 + " value " + heap.get(i/2) +
						i + " value " + heap.get(i) );
			}
		}
		return isHeap;
	}
}