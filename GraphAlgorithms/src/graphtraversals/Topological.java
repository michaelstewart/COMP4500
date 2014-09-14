package graphtraversals;

import graphs.DGraph;
import graphs.Vertex;
import graphs.Graph.AdjacentEdge;

import java.util.List;
import java.util.LinkedList;

public class Topological {
	
	private enum Colour{White, Grey, Black};
	
	private class V extends Vertex {
		Colour colour;
	}
	
	public List<V> ord;
	public boolean cyclic;
	
	public Topological() {
		super();
	}

	public void order( DGraph<V,Object> G ) {
		cyclic = false;
		ord = new LinkedList<V>();
		for( V u : G ) {
			u.colour = Colour.White;
		}
		for( V u : G ) {
			if( u.colour == Colour.White ) {
				visit( G, u );
			}
		}
	}
	
	private void visit( DGraph<V,Object> G, V u ) {
		/* for all w: G.V . w.colour == Colour.Grey  =>  (w,u) IN G.E+ */
		u.colour = Colour.Grey;
		/* for all w: G.V . w.colour == Colour.Grey  =>  (w,u) IN G.E* */
		for( AdjacentEdge<V,Object> e : G.adjacent(u) ) {
			V v = e.target;
			if( v.colour == Colour.White ) {
				/* for all w: G.V . w.colour == Grey  =>  (w,v) IN G.E+ */
				visit( G, v );
				/* !cyclic => respects(ord, G.E) && 
				 *            G.E*(| {v} |) subset rng(ord) */
			} else if( v.colour == Colour.Grey ) {
				/* (for all w: G.V . w.colour == Colour.Grey => (w,u) IN G.E* )
				   (v,u) IN G.E* && (u,v) IN G.E
				   (v,v) IN G.E+ */
				cyclic = true;
			}
		}
		/* !cyclic => respects(ord, G.E) && 
         *            (for all v:G.V . (u,v) IN G.E => G.E*(| {v} |) subset rng(ord))
         */
		u.colour = Colour.Black;
		/* All vertices reachable from u have been visited and added to ord
		 * so u can now be added to the front of ord.
		 */
		ord.add(0, u);
		/* !cyclic => respects(ord, E) &&
		 *            G.E*(| {u} |) subset rng(Ord)
		 */
	}
}
