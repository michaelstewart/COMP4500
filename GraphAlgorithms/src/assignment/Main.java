package assignment;

import graphs.DGraph;
import graphs.DGraphAdj;
import graphs.Graph;
import graphs.Graph.AdjacentEdge;
import graphtraversals.DepthFirst;
import graphtraversals.DepthFirstVertex;

import java.util.HashMap;
import java.util.Map;

public class Main {

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
		DepthFirstVertex v6 = new DepthFirstVertex(); G.addVertex( v6 );
		DepthFirstVertex v7 = new DepthFirstVertex(); G.addVertex( v7 );
		DepthFirstVertex v8 = new DepthFirstVertex(); G.addVertex( v8 );
		DepthFirstVertex v9 = new DepthFirstVertex(); G.addVertex( v9 );
		

		Object ob = new Object();
		G.addEdge( v9, v8, ob );
		G.addEdge( v8, v4, ob );
		G.addEdge( v4, v2, ob );
		G.addEdge( v2, v6, ob );
		G.addEdge( v6, v7, ob );
		G.addEdge( v7, v8, ob );
		G.addEdge( v8, v9, ob );
		G.addEdge( v9, v2, ob );
		G.addEdge( v2, v6, ob );
		G.addEdge( v6, v5, ob );
		G.addEdge( v5, v2, ob );

		df.DepthFirstSearch( G );
		for( DepthFirstVertex u : G ) {
			System.out.println( u );
		}
	}
	
	public static void computeSni() {
		int[] arr = {9,8,4,2,6,7,0,9,2,6,5,2};
		Map<Integer,Integer> d = new HashMap<Integer,Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			d.put(i+1, arr[i]);
		}
		
		printOut(d);
		
		for (int i = 2; i <= 12; i++) {
			if (d.get(i).equals(d.get(i-1))) {
				int val = (d.get(i) + 3) % 10;
				d.put(i, val);
			}
		}
		
		printOut(d);
	}
	
	private static void printOut(Map<Integer,Integer> d) {
		String s = "";
		for (int i = 1; i <=12; i++) {
			s += d.get(i);
		}
		
		System.out.println(s);
	}
	
	
}
