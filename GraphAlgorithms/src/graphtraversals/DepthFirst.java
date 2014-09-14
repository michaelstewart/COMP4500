package graphtraversals;

import graphs.Graph;
import graphs.Graph.AdjacentEdge;

public class DepthFirst<V extends DepthFirstVertex,E> {
	
	public enum Colour{ White, Grey, Black }
			
	public DepthFirst( ) {
		super();
	}
	
	private int time;

	public void DepthFirstSearch( Graph<V,E> G ) {
	    /* Initialize all the vertices of the graph to be
	    	       undiscovered and have no parent. */
		for( V u : G ) {
			u.colour = Colour.White;
			u.parent = null;
		}
	    /* Search through all vertices of the graph and visit
	    	       any that have not yet been discovered. */
		time = 0;
		for( V u : G ) {
			if( u.colour == Colour.White ) {
				visit( G, u );
			}
		}
	}
	/** Perform depth-first visit from vertex u
	 * @param G graph to be searched
	 * @param u start vertex for this visit
	 */
	private void visit( Graph<V,E> G, V u ) {
		u.colour = Colour.Grey;
		time++;
		u.discovery = time;
		/* Visit all vertices adjacent to u that have not been
		 * discovered (still White).
		 */
		for( AdjacentEdge<V,E> e : G.adjacent(u) ) {
			V v = e.target;
			if( v.colour == Colour.White ) {
				v.parent = u;
				visit( G, v );
			}
		}
		u.colour = Colour.Black;
		time++;
		u.finish = time;
	}
}