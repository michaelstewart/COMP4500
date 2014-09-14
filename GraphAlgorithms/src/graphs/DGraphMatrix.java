package graphs;

/** Implementation of a directed graph using adjacency matrix 
 * @author Ian Hayes
 */
public class DGraphMatrix<V extends Vertex, E> 
	extends GraphMatrix<V,E>
	implements DGraph<V,E> 
{

    public DGraphMatrix(int size) {
    	super(size);
    }
    
    public void addEdge( V u, V v, E e ) {
    	assert e != null;
    	edge[u.getIndex()][v.getIndex()] = e;
    }
}
