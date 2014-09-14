package graphtraversals;

import java.util.HashMap;
import java.util.Map;

import graphs.Graph;
import graphs.Vertex;
import graphs.Graph.AdjacentEdge;

public class OutDegree<V extends Vertex, E> {
	
	Map<V,Integer> outDegree(Graph<V,E> G) {
		Map<V,Integer> OD = new HashMap<V,Integer>(G.size());
		for (V u : G) {
			for( AdjacentEdge<V,E> v : G.adjacent(u) ) {
				OD.put(u, OD.get(u)+1);
			}
		}
		return OD;
	}


}
