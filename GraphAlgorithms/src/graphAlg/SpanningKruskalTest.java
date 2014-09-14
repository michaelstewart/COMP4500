package graphAlg;

import graphAlg.SpanningKruskal.E;
import graphAlg.SpanningKruskal.V;
import graphs.UGraph;
import graphs.UGraphAdj;

import java.util.List;

public class SpanningKruskalTest {
	
	private static void addVertices( UGraph<V,E> G, 
			SpanningKruskal mst, V... vertices ) {
		for( V v : vertices ) {
			G.addVertex( v );
		}
	}
	private static void addEdge( UGraph<V,E> G, 
			SpanningKruskal mst, V i, V j, int w ) {
		G.addEdge( i, j, mst.new E(i,j,w) );
	}
	
	public static void runTest( UGraph<V,E> G, SpanningKruskal mst ) {
		List<E> edges = mst.minimalSpanningTree( G );
		System.out.println( "Minimal spanning tree with " + 
				edges.size() + " edges" );
		int weight = 0;
		for( E e : edges ) {
			System.out.println( e );
			weight += e.weight;
		}
		System.out.println( "Weight " + weight );
	}
	public static void main(String[] args) {
		SpanningKruskal mst = new SpanningKruskal();
		UGraph<V,E> G = new UGraphAdj<V,E>();
		V v0 = new V();
		V v1 = new V();
		V v2 = new V();
		V v3 = new V();
		V v4 = new V();
		V v5 = new V();
		V v6 = new V();
		V v7 = new V();
		V v8 = new V();
		addVertices( G, mst, v0, v1, v2, v3, v4, v5, v6, v7, v8 );
		addEdge( G, mst, v0, v1, 1 );
		addEdge( G, mst, v1, v2, 6 );
		addEdge( G, mst, v1, v6, 4 );
		addEdge( G, mst, v2, v3, 14 );
		addEdge( G, mst, v2, v6, 5 );
		addEdge( G, mst, v2, v4, 10 );
		addEdge( G, mst, v3, v4, 3 );
		addEdge( G, mst, v4, v5, 8 );
		addEdge( G, mst, v5, v6, 2 );
		addEdge( G, mst, v5, v8, 15 );
		addEdge( G, mst, v6, v7, 9 );

		runTest( G, mst );
		
		mst = new SpanningKruskal();
		G = new UGraphAdj<V,E>();
		v0 = new V();
		v1 = new V();
		v2 = new V();
		v3 = new V();
		v4 = new V();
		v5 = new V();
		v6 = new V();
		v7 = new V();
		v8 = new V();
		addVertices( G, mst, v0, v1, v2, v3, v4, v5, v6, v7, v8 );
		addEdge( G, mst, v0, v1, 1 );
		addEdge( G, mst, v1, v2, 6 );
		addEdge( G, mst, v1, v6, 4 );
		addEdge( G, mst, v2, v3, 14 );
		addEdge( G, mst, v2, v6, 5 );
		addEdge( G, mst, v2, v4, 10 );
		addEdge( G, mst, v3, v4, 3 );
		addEdge( G, mst, v4, v5, 8 );
		addEdge( G, mst, v5, v6, 2 );
		addEdge( G, mst, v5, v8, 15 );
		
		runTest( G, mst );
		
		mst = new SpanningKruskal();
		G = new UGraphAdj<V,E>();
		v0 = new V();
		v1 = new V();
		v2 = new V();
		v3 = new V();
		v4 = new V();
		v5 = new V();
		v6 = new V();
		v7 = new V();
		v8 = new V();
		addVertices( G, mst, v0, v1, v2, v3, v4, v5, v6, v7, v8 );
		addEdge( G, mst, v0, v1, 1 );
		addEdge( G, mst, v1, v2, 6 );
		addEdge( G, mst, v1, v6, 4 );
		addEdge( G, mst, v2, v3, 14 );
		addEdge( G, mst, v2, v6, 5 );
		addEdge( G, mst, v2, v4, 10 );
		addEdge( G, mst, v3, v4, 3 );
		addEdge( G, mst, v4, v5, 8 );
		addEdge( G, mst, v5, v6, 2 );
		addEdge( G, mst, v7, v8, 15 );
		
		runTest( G, mst );
		
	}
}