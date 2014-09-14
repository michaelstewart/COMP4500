package graphtraversals;

import graphs.UGraph;
import graphs.UGraphAdj;
import graphtraversals.Components.V;

public class ComponentsTest {
	
	public static void main(String[] args) {
		Components components = new Components();
		UGraph<V,Object> G = new UGraphAdj<V,Object>();
		V v0 = components.new V(); G.addVertex( v0 );
		V v1 = components.new V(); G.addVertex( v1 );
		V v2 = components.new V(); G.addVertex( v2 );
		V v3 = components.new V(); G.addVertex( v3 );
		V v4 = components.new V(); G.addVertex( v4 );
		V v5 = components.new V(); G.addVertex( v5 );

		Object ob = new Object();
		G.addEdge( v0, v1, ob );
		G.addEdge( v0, v3, ob );
		G.addEdge( v1, v4, ob );
		G.addEdge( v4, v3, ob );
		G.addEdge( v2, v5, ob );

		components.components( G );
		for( V u : G ) {
			System.out.println( u );
		}
	}

}
