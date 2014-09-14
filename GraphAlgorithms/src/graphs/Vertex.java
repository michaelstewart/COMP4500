package graphs;

public class Vertex {
    /** Used as a unique identifier for vertex to allow
     * array-based implementation.
     * Set and get index are only allowed within subclasses
     */
    protected int index;
    
    public Vertex( ) {
        super();
        index = -1;
    }
    
    protected int getIndex() {
        return index;
    }
    
    protected void setIndex( int i ) {
        this.index = i;
    }
    
    public String toString() {
        return "vertex(" + index + ")";
    }
    
}
