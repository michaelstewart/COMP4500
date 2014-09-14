package graphs;

/** Implementation of a directed graph using adjacency matrix 
 * @author Ian Hayes
 */
class UGraphMatrix<V extends Vertex, E> 
extends GraphMatrix<V,E>
implements UGraph<V,E> 
{

    public UGraphMatrix(int size) {
    	super(size);
    }
    
    public void addEdge( V u, V v, E e ) {
    	assert e != null;
    	edge[u.getIndex()][v.getIndex()] = e;
    	edge[v.getIndex()][u.getIndex()] = e;
    }
}
