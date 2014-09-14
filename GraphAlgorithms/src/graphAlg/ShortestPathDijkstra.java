package graphAlg;

import graphs.DGraph;
import graphs.Graph.AdjacentEdge;
import graphs.Vertex;

public class ShortestPathDijkstra {
	
	public class V extends Vertex implements HeapElement<V> {
		public V parent;
		public int minDistance;
		public int heapIndex;
		public boolean isFinal;
		
		public V() {
			super();
			init();
		}
		
		public void init() {
			parent = null;
			minDistance = Integer.MAX_VALUE;
			isFinal = false;			
		}
		
		public int getHeapIndex(){
			return heapIndex;
		}
		
		public void setHeapIndex( int index ) {
			heapIndex = index;
		}
		
		public void setKey( int distance ) {
			minDistance = distance;
		}
		
		public int compareTo( V w ) {
			if( minDistance < w.minDistance ) {
				return -1;
			} else if( minDistance == w.minDistance ) {
				return 0;
			} else
				return 1;
		}
		
		public String toString() {
			return super.toString() + 
					" distance " + minDistance + " parent " + 
					(parent == null ? " null " : parent );
		}
	}
	
	public class E {
		public V source;
		public V target;
		public int weight;
		
		public E( V source, V target, int weight ) {
			this.source = source;
			this.target = target;
			this.weight = weight;
		}
		
		public void relax() {
			if( source.minDistance + weight < target.minDistance) {
				target.parent = source;
				PQ.decreaseKey(target, source.minDistance + weight );
				//System.out.println( "  relaxed " + this );
			}
		}
	}
	
	public PriorityQueue<V> PQ;

	public void shortestPath( DGraph<V,E> G, V s ) {
		PQ = new PriorityQueue<V>( G.size() );
	    for( V u : G ) {
	    	u.minDistance = Integer.MAX_VALUE;
	    	u.parent = null;
	    	PQ.add( u );
	    }
	    PQ.decreaseKey(s,0);
	    while( !PQ.isEmpty() ) {
	    	V u = PQ.findMin();
	    	u.isFinal = true;
	    	PQ.deleteMin();
	    	for( AdjacentEdge<V,E> e : G.adjacent(u) ) {
	    		if( !e.edgeInfo.target.isFinal ) {
	    			e.edgeInfo.relax();
	    		}
       		}
       	}
	}
}