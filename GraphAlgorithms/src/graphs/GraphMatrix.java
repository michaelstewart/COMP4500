package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/** This class implements the common parts of directed and undirected 
 * graphs using an adjacency matrix representation.
 * Only adding edges is different between the two.
 * @author Ian Hayes
 *
 * @param <VertexInfo> type of information stored with each vertex
 * @param <EdgeInfo> type of information stored with each edge
 */
abstract class GraphMatrix<V extends Vertex, E> implements Graph<V,E> {

	protected E[][] edge;
	protected List<V> graph;
	int maxSize;
	
    public GraphMatrix(int maxSize) {
    	super();
    	this.maxSize = maxSize;
    	graph = new ArrayList<V>(maxSize);
    	edge = (E[][]) new Object[maxSize][maxSize];
//    	for ( int i = 0; i < maxSize; i++ ) {
//    		edge[i] = (Edge<Vertex<VI>,EI>[]) new Object[maxSize];
//    	}
    }
    
	private class Vertices implements Iterator<V> {
		int currentVertex;
		
		public Vertices() {
			super();
			currentVertex = -1;
		}
		
		public boolean hasNext() {
			return currentVertex < graph.size()-1;
		}
		
		public V next() {
			currentVertex++;
			return graph.get(currentVertex);
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private class Edges 
		implements Iterator<AdjacentEdge<V,E>>, 
					Iterable<AdjacentEdge<V,E>> {
		int currentVertex;
		int currentEdge;
		
		public Edges( V u ) {
			super();
			currentVertex = u.getIndex();
			currentEdge = -1;
		}
		
		public Iterator<AdjacentEdge<V,E>> iterator() {
			return this;
		}
		
		private void findNext() {
			do {
				currentEdge++;
			} while( currentEdge < graph.size() && 
					edge[currentVertex][currentEdge] == null);
			currentEdge--;
		}
		
		public boolean hasNext() {
			findNext();
			return currentEdge < graph.size()-1;
		}
		
		public AdjacentEdge<V,E> next() {
			assert currentEdge < graph.size()-1;
			findNext();
			currentEdge++;
			return new AdjacentEdge<V,E>( 
					graph.get(currentVertex), 
					edge[currentVertex][currentEdge]);
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public int size() {
		return graph.size();
	}

    public void addVertex( V v ) {
    	int size = graph.size();
    	v.setIndex(size);
    	graph.add( v );
    	for(int i = 0; i <= size; i++) {
    		edge[size][i] = null;
    		edge[i][size] = null;
    	}
    }
    
//    private Vertex<VI> getVertex( int i ) {
//    	return graph.get(i);
//    }
    
    private boolean hasVertex( int i ) {
    	return 0 <= i && i < graph.size();
    }
    
    public abstract void addEdge( V u, V v, E e );
    
    public boolean hasEdge( V u, V v ) {
    	assert hasVertex(u.getIndex()) && hasVertex(v.getIndex());
    	return edge[u.getIndex()][v.getIndex()] != null;
    }
    
    public Iterator<V> iterator() {
    	return new Vertices();
    }

    public Iterable<AdjacentEdge<V,E>> adjacent( V u ) {
    	return new Edges( u );
    }
}
