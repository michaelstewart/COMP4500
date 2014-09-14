package graphtraversals;

import graphs.UGraph;
import graphs.Vertex;
import graphs.Graph.AdjacentEdge;

class Components {
	
	private enum Colour{ White, Grey, Black }
	
	public class V extends Vertex {
		private Colour colour;
		public int component;
		
		public V() {
			colour = Colour.White;
		}
		
		public String toString() {
			return super.toString() + " component " + component;
		}
	}
		
	public Components() {
	}
	
	private int comp;

	/** Find the connected components of an undirected graph.
	 * The array component maps each vertex to its component 
	 * number. Two vertices are in the same component if and 
	 * only if they have the same component number. */
	
	public void components( UGraph<V,Object> G ) {
		/* Mark all vertices of G as unvisited (White). */
		for( V u : G ) {
			u.colour = Colour.White;
		}
		comp = 0;
		/* Traverse through the vertices and for each vertex u
		 * that has not been visited start a new component and
		 * mark all vertices connected to u as being in that 
		 * component. */
		for( V u : G ) {
			if( u.colour == Colour.White ) {
				comp++;
				visit(G,u);
			}
		}
	}
	
	private void visit( UGraph<V,Object> G, V u ) {
		/** Visit all vertices connected to u and mark as in 
		 * the current component.
		 */
		u.colour = Colour.Grey;
		u.component = comp;
		for( AdjacentEdge<V,Object> e : G.adjacent(u) ) {
			if( e.target.colour == Colour.White ) {
				visit( G, e.target );
			}
		}
		u.colour = Colour.Black;
	}
}