package graphAlg;

import graphAlg.SpanningPrim.E;
import graphAlg.SpanningPrim.V;
import graphs.UGraph;
import graphs.UGraphAdj;

public class SpanningPrimTest {
	
	static SpanningPrim forest = new SpanningPrim();
	
	private static void addVertices( UGraph<V,E> G, 
			SpanningPrim forest, V... vertices ) {
		for( V v : vertices ) {
			v.init();
			G.addVertex( v );
		}
	}
	private static void addEdge( UGraph<V,E> G, 
			SpanningPrim forest, V i, V j, int w ) {
		G.addEdge( i, j, forest.new E(w) );
	}
	
	public static void runTest( UGraph<V,E> G, SpanningPrim forest ) {
		forest.minimalSpanningForest( G );
		for( V u : G ) {
			System.out.println( "Parent of " + u + " is " + 
					(u.parent == null ? "none" : u.parent) );
		}
		System.out.println();
	}
	public static void main(String[] args) {
		UGraph<V,E> G = new UGraphAdj<V,E>();
		V v0 = forest.new V(); 
		V v1 = forest.new V(); 
		V v2 = forest.new V();
		V v3 = forest.new V(); 
		V v4 = forest.new V(); 
		V v5 = forest.new V(); 
		V v6 = forest.new V();
		V v7 = forest.new V(); 
		V v8 = forest.new V();
		addVertices( G, forest, v0, v1, v2, v3, v4, v5, v6, v7, v8 );
		addEdge( G, forest, v0, v1, 1 );
		addEdge( G, forest, v1, v2, 6 );
		addEdge( G, forest, v1, v6, 4 );
		addEdge( G, forest, v2, v3, 14 );
		addEdge( G, forest, v2, v6, 5 );
		addEdge( G, forest, v2, v4, 10 );
		addEdge( G, forest, v3, v4, 3 );
		addEdge( G, forest, v4, v5, 8 );
		addEdge( G, forest, v5, v6, 2 );
		addEdge( G, forest, v5, v8, 15 );
		addEdge( G, forest, v6, v7, 9 );

		runTest( G, forest );
		
		forest = new SpanningPrim();
		G = new UGraphAdj<V,E>();
		v0 = forest.new V(); 
		v1 = forest.new V(); 
		v2 = forest.new V();
		v3 = forest.new V(); 
		v4 = forest.new V(); 
		v5 = forest.new V(); 
		v6 = forest.new V();
		v7 = forest.new V(); 
		v8 = forest.new V();
		addVertices( G, forest, v0, v1, v2, v3, v4, v5, v6, v7, v8 );
		addEdge( G, forest, v0, v1, 1 );
		addEdge( G, forest, v1, v2, 6 );
		addEdge( G, forest, v1, v6, 4 );
		addEdge( G, forest, v2, v3, 14 );
		addEdge( G, forest, v2, v6, 5 );
		addEdge( G, forest, v2, v4, 10 );
		addEdge( G, forest, v3, v4, 3 );
		addEdge( G, forest, v4, v5, 8 );
		addEdge( G, forest, v5, v6, 2 );
		addEdge( G, forest, v5, v8, 15 );
		
		runTest( G, forest );
		
		forest = new SpanningPrim();
		G = new UGraphAdj<V,E>();
		v0 = forest.new V(); 
		v1 = forest.new V(); 
		v2 = forest.new V();
		v3 = forest.new V(); 
		v4 = forest.new V(); 
		v5 = forest.new V(); 
		v6 = forest.new V();
		v7 = forest.new V(); 
		v8 = forest.new V();
		addVertices( G, forest, v0, v1, v2, v3, v4, v5, v6, v7, v8 );
		addEdge( G, forest, v0, v1, 1 );
		addEdge( G, forest, v1, v2, 6 );
		addEdge( G, forest, v1, v6, 4 );
		addEdge( G, forest, v2, v3, 14 );
		addEdge( G, forest, v2, v6, 5 );
		addEdge( G, forest, v2, v4, 10 );
		addEdge( G, forest, v3, v4, 3 );
		addEdge( G, forest, v4, v5, 8 );
		addEdge( G, forest, v5, v6, 2 );
		addEdge( G, forest, v7, v8, 15 );
		
		runTest( G, forest );
		
	}
}