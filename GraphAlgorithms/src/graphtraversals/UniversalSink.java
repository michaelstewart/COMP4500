package graphtraversals;

import graphs.DGraph;
import graphs.DGraphMatrix;
import graphs.Vertex;

import java.util.Iterator;

final class V extends Vertex {
	int vertexNumber;
	
	V( int n ) {
		super();
		vertexNumber = n;
	}
}

public class UniversalSink {
	
	/** Determine if graph G has a universal sink
	 * @param G directed graph
	 * @return sink vertex if it exists, else return null
	 */
	public static V universalSink( 
			DGraph<V,Object> G ) {
		Iterator<V> vertices = G.iterator();
		if( ! vertices.hasNext() ) {
			return null; // empty graph has no sink
		}
		// The initial candidate for a sink is the first vertex
		V s = vertices.next();
		/* In the following predicate vertices is interpreted as
		 * the set of vertices yet to be traversed by the iterator.
		 * for all w : G - vertices . w != s => !(w sink of G) */
		while( vertices.hasNext() ) {
			// v iterates through the remaining vertices
			V v = vertices.next();
			// for all w : G - (vertices + {v}) . w != s => !(w sink of G)
			if( G.hasEdge(s, v) ) { 
				// (s, v) an edge implies s not a sink, but maybe v is, 
				// so make v the new candidate to be a sink. 
				s = v;
			} else { 
				// (s, v) not an edge implies v not a sink, but s may still 
				// be a sink, so leave s as the candidate. 
			}
			// for all w : G - vertices . w != s => !(w sink of G)
			// System.out.println( " s " + s + " v = " + v );
		}
		// for all w : G . w != s => !(w sink of G)
		// The only possible sink is s but we need to check whether it is.
		for( V w : G ) {
			/* s cannot be a sink if either
			 *   there is an edge with source s (and destination w) or
			 *   w is a vertex other than s and there is no edge from w to s
			 */
			if( G.hasEdge(s, w) || (w != s && !G.hasEdge(w, s)) ) {
				return null;
			}
		}
		// for all w : G . !G.hasEdge(s, w) && (w = s || G.hasEdge(w, s))
		// s is a sink of G
		return s;
	}
	
	public static void main(String[] args) {
		DGraph<V,Object> G = 
				new DGraphMatrix<V,Object>(6);
		V v0 = new V(0); G.addVertex( v0 );
		V v1 = new V(1); G.addVertex( v1 );
		V v2 = new V(2); G.addVertex( v2 );
		V v3 = new V(3); G.addVertex( v3 );
		V v4 = new V(4); G.addVertex( v4 );
		V v5 = new V(5); G.addVertex( v5 );
		Object ob = new Object();
		G.addEdge( v0, v2, ob );
		G.addEdge( v1, v2, ob );
		G.addEdge( v3, v2, ob );
		G.addEdge( v4, v2, ob );
		G.addEdge( v5, v2, ob );
		G.addEdge( v0, v3, ob );
		G.addEdge( v1, v3, ob );
		G.addEdge( v4, v3, ob );
		V sink = universalSink( G );
		System.out.println( (sink == null ? "No sink for graph" :
											"Sink is " + sink) );
	}
}
