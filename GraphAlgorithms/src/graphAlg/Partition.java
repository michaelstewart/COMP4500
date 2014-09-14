package graphAlg;

import graphAlg.Indexed;

import java.util.List;
import java.util.ArrayList;

public class Partition<X extends Indexed> {

	/* Abstractly a partition is a set of disjoint sets
	 *   P : { PS : SET OF SET OF X | 
     *        for all s,t : PS @ s <> t => s intersect t = {} }
     * The partition is represented by a forest of trees 
     * which are represented using parent links.
     *   P = { extractSet(entry.element) | entry : entries && entry != null }
     * where
     *   extractSet(x) = { e.element | e : entries && e != null && 
     *   				   findSet(e.element) == findSet(x) } 
     */
	private List<Entry> entries;

	private class Entry {
		X element;
		Entry parent;
		int rank;
		
		private Entry(X element) {
			this.element = element;
			// Parent is self loop to indicate it is the root
			this.parent = this;
			// Maximum height of the tree rooted at this node
			this.rank = 0;
		}
	}
	
	
	/* P = {} */
	public Partition( int maxSize ) {
		entries = new ArrayList<Entry>( maxSize );
		for( int i = 0; i < maxSize; i++ ) {
			entries.add( null );
		}
	}
	
	/** @requires !(x in Union(P))
	 * @ensures P' = P U {{x}}
	 * @param x to make into a single element set in partition
	 */
	public void makeSet( X x ) {
		entries.set( x.getIndex(), new Entry(x) );
	}

    /** @requires x IN union(P)
     * @ensures (exists S: P . x IN S && FindSet(x) IN S &&
     *              (forall y: S . FindSet(y) = FindSet(x) ) ) */
	private Entry findSet( X x ) {
		Entry ex = entries.get( x.getIndex() );
		assert ex != null;
		/* Find root of tree containing x */
		Entry s = ex.parent;
		while( s != s.parent ) {
			s = s.parent;
		}
		/* Compress path from x to the root s.
		 * This does not change the partition, just its representation. */
		while( ex != s ) {
			Entry y = ex.parent;
			ex.parent = s;
			ex = y;
		}
		return s;
	}
	
	/* @requires (exists S,T: P . (x IN S) && (y IN T)
	 * @returns true if and only if
	 * 		(exists S: P . (x IN S) && (y IN S))
	 */
	public boolean equiv( X x, X y ) {
		return findSet(x) == findSet(y);
	}

    /* @requires (exists S,T: P . (x IN S) && (y IN T) && (S != T)
     * @ensures (exists S,T: P . (x IN S) && (y IN T) && 
     *                           P' = P - {S, T} U {S U T})
     */
	public void union( X x, X y) {
		Entry s = findSet( x );
		Entry t = findSet( y );
		if( s.rank > t.rank ) {
			t.parent = s;
		} else { /* s.rank <= t.rank */
			s.parent = t;
			if( s.rank == t.rank ) {
				t.rank++;
			}
		}
	}
}