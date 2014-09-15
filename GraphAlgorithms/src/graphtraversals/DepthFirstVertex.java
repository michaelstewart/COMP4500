package graphtraversals;

import graphs.Vertex;
import graphtraversals.DepthFirst.Colour;

public class DepthFirstVertex extends Vertex {
	public Colour colour;
	public int discovery;
	public int finish;
	public DepthFirstVertex parent;
	
	public DepthFirstVertex( ) {
		super();
		initVertex();
	}
	
	public void initVertex() {
		colour = Colour.White;
		parent = null;
	}
	
	public int number() {
		return super.getIndex();
	}
		
	public String toString() {
		return super.toString() + " discovered " + discovery + 
		" finished " + finish + 
		" parent " + (parent == null ? "null" : parent);
	}
}
