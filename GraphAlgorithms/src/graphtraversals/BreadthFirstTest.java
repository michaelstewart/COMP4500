package graphtraversals;

import graphs.DGraphAdj;
import graphs.Graph;
import graphtraversals.BreadthFirstVertex;

public class BreadthFirstTest {
	
	public static void main(String[] args) {
		BreadthFirst<BreadthFirstVertex,Object> bf = 
				new BreadthFirst<BreadthFirstVertex,Object>();
		Graph<BreadthFirstVertex,Object> G = 
				new DGraphAdj<BreadthFirstVertex,Object>();
		BreadthFirstVertex v0 = new BreadthFirstVertex(); G.addVertex( v0 );
		BreadthFirstVertex v1 = new BreadthFirstVertex(); G.addVertex( v1 );
		BreadthFirstVertex v2 = new BreadthFirstVertex(); G.addVertex( v2 );
		BreadthFirstVertex v3 = new BreadthFirstVertex(); G.addVertex( v3 );
		BreadthFirstVertex v4 = new BreadthFirstVertex(); G.addVertex( v4 );
		BreadthFirstVertex v5 = new BreadthFirstVertex(); G.addVertex( v5 );

		Object ob = new Object();
		G.addEdge( v0, v1, ob );
		G.addEdge( v0, v3, ob );
		G.addEdge( v1, v4, ob );
		G.addEdge( v3, v1, ob );
		G.addEdge( v4, v3, ob );
		G.addEdge( v2, v4, ob );
		G.addEdge( v2, v5, ob );
		G.addEdge( v5, v5, ob );

		bf.breadthFirstSearch( G, v0 );
		for( BreadthFirstVertex u : G ) {
			System.out.println( u );
		}
	}

}
