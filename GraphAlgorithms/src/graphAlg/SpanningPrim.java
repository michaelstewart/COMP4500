package graphAlg;

import graphs.Graph.AdjacentEdge;
import graphs.UGraph;
import graphs.Vertex;

public class SpanningPrim {
	
	public class V extends Vertex implements HeapElement<V> {
		public V parent;
		public int minWeight;
		public boolean inTree;
		int heapIndex;
		
		public V() {
			super();
			init();
		}
		public void init() {
			parent = null;
			minWeight = Integer.MAX_VALUE;
			inTree = false;
		}
				
		public int getHeapIndex() {
			return heapIndex;
		}
		
		public void setHeapIndex( int i ) {
			heapIndex = i;
		}
		
		public void setKey( int key ) {
			minWeight = key;
		}
		
		public int compareTo( V w ) {
			if( minWeight < w.minWeight ) {
				return -1;
			} else if( minWeight == w.minWeight ) {
				return 0;
			} else
				return 1;
		}
		
		public String toString() {
			return super.toString() + " weight " + minWeight;
		}

	}
	
	public class E {
		public int weight;
		
		public E( int weight ) {
			this.weight = weight;
		}
	}
	
	private PriorityQueue<V> PQ;

	
	public void minimalSpanningForest( UGraph<V,E> G ) {
	    PQ = new PriorityQueue<V>( G.size() );
	    for( V u : G ) {
	    	u.minWeight = Integer.MAX_VALUE;
	    	u.inTree = false;
	    	PQ.add( u );
	    }
	    for( V u : G ) {
	    	if( !u.inTree ) {
	    		minimalSpanningTree( G, u );
	    	}
	    }
	}
	
	private void minimalSpanningTree( UGraph<V,E> G, V u ) {
		/* Treat u as the root of a spanning tree */
		u.parent = null;
		PQ.decreaseKey(u, 0);
        do {
        	u = PQ.findMin();
        	PQ.deleteMin();
        	u.inTree = true;
        	for( AdjacentEdge<V,E> e : G.adjacent(u) ) {
        		V v = e.target;
        		if( !v.inTree && (e.edgeInfo.weight < v.minWeight) ) {
        			v.parent = u;
        			PQ.decreaseKey( v, e.edgeInfo.weight );
        		}
        	}
        } while( !PQ.isEmpty() );
	}
}