package graphtraversals;

import graphs.DGraphAdj;
import graphs.Graph;

public class DepthFirstTest {
	
	public static void main(String[] args) {
		DepthFirst<DepthFirstVertex,Object> df = 
				new DepthFirst<DepthFirstVertex,Object>();
		Graph<DepthFirstVertex,Object> G = 
				new DGraphAdj<DepthFirstVertex,Object>();
		DepthFirstVertex v0 = new DepthFirstVertex(); G.addVertex( v0 );
		DepthFirstVertex v1 = new DepthFirstVertex(); G.addVertex( v1 );
		DepthFirstVertex v2 = new DepthFirstVertex(); G.addVertex( v2 );
		DepthFirstVertex v3 = new DepthFirstVertex(); G.addVertex( v3 );
		DepthFirstVertex v4 = new DepthFirstVertex(); G.addVertex( v4 );
		DepthFirstVertex v5 = new DepthFirstVertex(); G.addVertex( v5 );

		Object ob = new Object();
		G.addEdge( v0, v1, ob );
		G.addEdge( v0, v3, ob );
		G.addEdge( v1, v4, ob );
		G.addEdge( v3, v1, ob );
		G.addEdge( v4, v3, ob );
		G.addEdge( v2, v4, ob );
		G.addEdge( v2, v5, ob );
		G.addEdge( v5, v5, ob );

		df.DepthFirstSearch( G );
		for( DepthFirstVertex u : G ) {
			System.out.println( u );
		}
	}

}
