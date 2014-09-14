package graphAlg;

import graphAlg.ShortestPathDijkstra.E;
import graphAlg.ShortestPathDijkstra.V;
import graphs.DGraph;
import graphs.DGraphAdj;

public class ShortestPathDijkstraTest {
	
	private static void addVertices( DGraph<V,E> G, 
			ShortestPathDijkstra forest, V... vertices ) {
		for( V v : vertices ) {
			v.init();
			G.addVertex( v );
		}
	}
	private static void addEdge( DGraph<V,E> G, 
			ShortestPathDijkstra forest, V i, V j, int w ) {
		G.addEdge( i, j, forest.new E(i,j,w) );
	}
	
	public static void runTest( DGraph<V,E> G, 
			ShortestPathDijkstra forest, V s ) {
		forest.shortestPath( G, s );
		for( V u : G ) {
			System.out.println( u );
		}
	}
	public static void main(String[] args) {
		ShortestPathDijkstra forest = new ShortestPathDijkstra();
		DGraph<V,E> G = new DGraphAdj<V,E>();
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
		addEdge( G, forest, v6, v5, 2 );
		addEdge( G, forest, v5, v8, 15 );
		addEdge( G, forest, v6, v7, 9 );

		runTest( G, forest, v0 );
		
		forest = new ShortestPathDijkstra();
		G = new DGraphAdj<V,E>();
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
		
		runTest( G, forest, v0 );
		
		forest = new ShortestPathDijkstra();
		G = new DGraphAdj<V,E>();
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
		addEdge( G, forest, v5, v7, 1 );
		addEdge( G, forest, v8, v7, 15 );
		
		runTest( G, forest, v0 );
		
	}
}