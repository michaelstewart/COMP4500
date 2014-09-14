package graphAlg;

import graphs.DGraph;
import graphs.Graph.AdjacentEdge;
import graphs.Vertex;

public class ShortestPathBellmanFord {
	
	public class V extends Vertex {
		public V parent;
		public int minDistance;
		
		public V() {
			super();
			init();
		}
		
		public void init() {
			parent = null;
			minDistance = Integer.MAX_VALUE;
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
			super();
			this.source = source;
			this.target = target;
			this.weight = weight;
		}
		
		public void relax() {
			if( source.minDistance + weight < target.minDistance) {
				target.parent = source;
				target.minDistance = source.minDistance + weight;
				//System.out.println( "  relaxed " + this );
			}
		}
	}
		
	public boolean shortestPath( DGraph<V,E> G, V s ) {
	    for( V u : G ) {
	    	u.minDistance = Integer.MAX_VALUE;
	    	u.parent = null;
	    }
	    s.minDistance = 0;
	    for( int i = 1; i < G.size(); i++ ) {
	    	for( V u : G ) {
	    		for( AdjacentEdge<V, E> e : G.adjacent( u ) ) {
	    			e.edgeInfo.relax();
	    		}
	    	}
	    }
	    for( V u : G ) {
    		for( AdjacentEdge<V,E> e : G.adjacent( u ) ) {
    			V es = e.edgeInfo.source;
    			V et = e.edgeInfo.target;
    			if( et.minDistance > es.minDistance + e.edgeInfo.weight ) {
    				return false;
    			}
    		}
	    }
    	return true;	
	}
}