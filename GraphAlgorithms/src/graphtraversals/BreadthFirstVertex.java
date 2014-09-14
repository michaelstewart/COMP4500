package graphtraversals;

import graphs.Vertex;
import graphtraversals.BreadthFirst.Colour;

public class BreadthFirstVertex extends Vertex {
	public Colour colour;
	public int distance;
	public BreadthFirstVertex parent;
	
	public BreadthFirstVertex( ) {
		super();
		this.colour = Colour.White;
		this.distance = Integer.MAX_VALUE;
		this.parent = null;
	}
	
	public String toString() {
		return super.toString() + " distance " + 
				(distance == Integer.MAX_VALUE ? "infinity" : distance) + 
			" parent " + (parent == null ? "null" : parent );
	}
}
