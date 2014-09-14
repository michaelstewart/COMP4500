package assignment;

import graphs.DGraph;
import graphs.DGraphAdj;
import graphs.Graph.AdjacentEdge;
import graphtraversals.DepthFirstVertex;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		DGraph<DepthFirstVertex, AdjacentEdge<DepthFirstVertex,DepthFirstVertex>> G = 
				new DGraphAdj<DepthFirstVertex, AdjacentEdge<DepthFirstVertex,DepthFirstVertex>>();
		for (int i= 0; i < 10; i++) {
			DepthFirstVertex v = new DepthFirstVertex();			
			G.addVertex(v);
			System.out.println(v);
		}
		
		G.
		
		
		
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
