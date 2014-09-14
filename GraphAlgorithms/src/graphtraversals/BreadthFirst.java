package graphtraversals;

import graphs.Graph;
import graphs.Graph.AdjacentEdge;

import java.util.Queue;
import java.util.LinkedList;

public class BreadthFirst<V extends BreadthFirstVertex,E> {

	public enum Colour{ White, Grey, Black }
			
	public BreadthFirst() {
		super();
	}
		
	public void breadthFirstSearch( Graph<V,E> G, V source ) {
		/* Traverse all the vertices of the graph initializing them
		 * to be unvisited (White), distance infinity 
		 * (approximately) from the source and to have no parent.
		 */
		 for( V u : G ) {
			 u.colour = Colour.White;
			 u.distance = Integer.MAX_VALUE;
			 u.parent = null;
		 }
		 /* Start the breadth-first search from the source.
		  * Colour it Grey to indicate it has been noticed, 
		  * but not yet searched. The source is distance 0 from 
		  * itself, and has no parent. */
		 source.colour = Colour.Grey;
		 source.distance = 0;
		 source.parent = null;
		 /* To perform a breadth-first search a first-in first-out 
		  * queue is used to store the vertices that have been 
		  * discovered, but not yet processed.
		  * The queue is initialized to contain the source vertex. 
		  */
		 Queue<V> Q = new LinkedList<V>();
		 Q.add( source );
		 while( ! Q.isEmpty() ) {
			 /* Process the first vertex, u, in the queue. */
			 V u = Q.remove();
			 /* Traverse all adjacent vertices of u searching for
			         undiscovered (White) vertices. */
			 for( AdjacentEdge<V, E> e : G.adjacent(u) ) {
				 V v = e.target;
				 if( v.colour == Colour.White ) {
					 /* For each undiscovered vertex, v, 
					  * process it by:
					  * marking v as discovered (Grey),
					  * calculating its distance from the source,
					  * noting that v's parent is u, and
					  * enqueue v so that its adjacent 
					  * vertices are searched. 
					  */
					 v.colour = Colour.Grey;
					 v.distance = u.distance + 1;
					 v.parent = u;
					 Q.add(v);
				 }
			 }
			 /* Having examined the vertices adjacent to u,
			  * we mark u as being completely processed (Black).
			  */
			 u.colour = Colour.Black;
		 }
	}
}
