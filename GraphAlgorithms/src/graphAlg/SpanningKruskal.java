package graphAlg;

import graphs.Graph.AdjacentEdge;
import graphs.UGraph;
import graphs.Vertex;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class SpanningKruskal {
	
	public static class V extends Vertex implements Indexed {
		
		@Override
		public int getIndex() {
			return index;
		}
		@Override
		public void setIndex( int index ) {
			this.index = index;
		}
	}
			
	public class E {
		public V source;
		public V target;
		public int weight;
		
		public E( V from, V to, int weight ) {
			super( );
			this.source = from;
			this.target = to;
			this.weight = weight;
		}
		
		public int compareTo( E other ) {
			return (weight < other.weight ? -1 :
				weight > other.weight ? 1 : 0 );
		}
				
		public String toString() {
			return "Edge from " + source + " to " + target + " weight " + weight;
		}
	}
	
	private class EdgeComparator implements Comparator<E> {
		
		public int compare( E first, E second ) {
			return ( first.compareTo(second) );
		}
	}

	public List<E> minimalSpanningTree( UGraph<V,E> G ) {
		List<E> edges = new ArrayList<E>(G.size());
		for( V u : G ) {
			for( AdjacentEdge<V, E> e : G.adjacent(u) ) {
				edges.add( e.edgeInfo );
			}
		}
		Collections.sort(edges, new EdgeComparator() );
		
		/* set up partition of singleton sets */
		Partition<V> partition = new Partition<V>( G.size() );
		for( V u : G ) {
			partition.makeSet(u);
		}
		List<E> spanningTree = new ArrayList<E>(G.size()-1);
		for( E e : edges ) {
			if( !partition.equiv( e.source, e.target ) ) {
				// add edge to minimal spanning tree
				spanningTree.add(e);
				// union the components joined by the edge
				partition.union( e.source, e.target );
			}
		}
		return spanningTree;
	}
}
