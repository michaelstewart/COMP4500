package graphs;

/** Implementation of an undirected graph using adjacency lists 
 * @author Ian Hayes
 */
public class UGraphAdj<V extends Vertex, E> 
	extends GraphAdj<V,E>
	implements UGraph<V,E> {

    public UGraphAdj() {
    	super();
    }
    
    public void addEdge( V u, V v, E e ) {
    	graph.get(u.getIndex()).edges.add( 
    			new AdjacentEdge<V,E>(v,e) );
    	graph.get(v.getIndex()).edges.add( 
    			new AdjacentEdge<V,E>(u,e) );
    }
}
