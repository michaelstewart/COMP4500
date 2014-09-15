package graphtraversals;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println("init");
	    /* Search through all vertices of the graph and visit
	    	       any that have not yet been discovered. */
		time = 0;
		for( V u : G ) {
			if( u.colour == Colour.White ) {
//				printState(G, u);
				visit( G, u );
			}
		}
//		printState(G, null);
	}
	
	public void DepthFirstSearchT( Graph<V,E> G ) {
	    /* Initialize all the vertices of the graph to be
	    	       undiscovered and have no parent. */
		for( V u : G ) {
			u.colour = Colour.White;
			u.parent = null;
		}
		System.out.println("init");
	    /* Search through all vertices of the graph and visit
	    	       any that have not yet been discovered. */
		time = 0;
		List<V> l = new ArrayList<V>();
		while (l.size() < G.size()) {
			int max = 0;
			V v = null;
			for( V u : G ) {
				if (u.finish > max && !l.contains(u)) {
					max = u.finish;
					v = u;
				}
			}
			l.add(v);
		}
		System.out.println(l);
		
		for( V u : l ) {
			if( u.colour == Colour.White ) {
				System.out.println(u.colour);
				printStateT(G, u);
				visit( G, u );
			}
		}
//		printState(G, null);
	}
	
	
	/** Perform depth-first visit from vertex u
	 * @param G graph to be searched
	 * @param u start vertex for this visit
	 */
	private void visit( Graph<V,E> G, V u ) {
		System.out.println(u);
		u.colour = Colour.Grey;
		time++;
		u.discovery = time;
		/* Visit all vertices adjacent to u that have not been
		 * discovered (still White).
		 */
		V v = null;
		for( AdjacentEdge<V,E> e : G.adjacent(u) ) {
			v = e.target;
			if( v.colour == Colour.White ) {
				v.parent = u;
//				printState(G, v);
				visit( G, v );
			}
		}
		u.colour = Colour.Black;
		time++;
		u.finish = time;
	}
	
	
	
	private void printState(Graph<V,E> G, V v) {
		if (v != null)
			System.out.println(String.format("Prior to calling DFS(%s)\n", v.number()));
		
		System.out.println("\\begin{tabular}{| l | l | l | l | l |}\\hline");
		System.out.println("  $x$ & colour[$x$] & $\\pi$[$x$] & start time & finish time\\\\\\hline");
		
		for( V u : G ) {
			String p = u.parent != null ? Integer.toString(u.parent.number()) : "";
			String start = u.discovery != 0 ? Integer.toString(u.discovery) : "";
			String finish =  u.finish != 0 ? Integer.toString(u.finish) : "";
			System.out.println(String.format("%s & %s & %s & %s & %s \\\\\\hline", u.number(), u.colour, p, start, finish));
		}
		
		System.out.println("\\end{tabular}");
		System.out.println("");
	}
	
	private void printStateT(Graph<V,E> G, V v) {
		System.out.println(v);

	}
}