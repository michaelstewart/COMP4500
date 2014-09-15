package dependencies;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import dependencies.Primitive.NullStatement;
import dependencies.Statement.Compound;
import dependencies.Statement.Repeat;
import dependencies.Statement.Select;
import graphs.DGraph;
import graphs.DGraphAdj;
import graphs.Graph.AdjacentEdge;

public class FlowGraph {
    /** Control flow graph for a function */
    DGraph<ControlFlowNode,Primitive> graph;
    /** Unique entry and exit nodes for the control flow graph */
    ControlFlowNode entry, exit;
    
    /** Construct a new control flow graph for a function */
    public FlowGraph( Statement body ) {
        super();
        graph = new DGraphAdj<ControlFlowNode,Primitive>();
        entry = newVertex();
        exit = newVertex();
        body.buildGraph(entry, exit, this);
    }
    /** Construct a new vertex and add to graph */
    private ControlFlowNode newVertex() {
        ControlFlowNode v = new ControlFlowNode();
        graph.addVertex(v);
        return v;
    }
    /** To construct the control flow graph for a primitive,
     * one only needs to add an edge labeled with the primitive statement
     * @param entry vertex already in graph
     * @param exit vertex already in graph
     * @param statement labeling the edge
     */
    public void buildPrimitive( ControlFlowNode entry, ControlFlowNode exit,
            Primitive statement ) {
        graph.addEdge( entry, exit, statement);
    }
    
    public void buildCompound(ControlFlowNode entry, ControlFlowNode exit, 
    		Compound compound) {
    	List<Statement> statements = compound.getStatements();
    	int lastStatementIdx = statements.size() - 1;
    	ControlFlowNode u = entry;    	
    	for (int i = 0; i < statements.size(); i++) {
    		Statement statement = statements.get(i);    		
    		ControlFlowNode v = i < lastStatementIdx ? newVertex() : exit;    		   		
    		statement.buildGraph(u, v, this);
    		u = v;
    	}    	
    }
    
    public void buildSelect(ControlFlowNode entry, ControlFlowNode exit, 
    		Select select) {
    	Set<Statement> statements = select.getStatements(); 	
    	Iterator<Statement> it = statements.iterator();
    	while (it.hasNext()) {
    		Statement statement = it.next();
    		statement.buildGraph(entry, exit, this);
    	}
    }
    
    public void buildRepeat(ControlFlowNode entry, ControlFlowNode exit, 
    		Repeat repeat) {
    	// enter_body vertex
    	ControlFlowNode enterBody = newVertex();
    	graph.addEdge(entry, enterBody, new NullStatement( repeat.pos ));
    	// exit_body vertex
    	ControlFlowNode exitBody = newVertex();
    	graph.addEdge(exitBody, exit, new NullStatement( repeat.pos ));
    	
    	Statement statement = repeat.getStatement();
    	statement.buildGraph(enterBody, exitBody, this);
    	
    	// Bypass repeat edge
    	graph.addEdge(entry, exit, new NullStatement( repeat.pos ));
    	// Repeat again edge
    	graph.addEdge(exitBody, enterBody, new NullStatement( repeat.pos ));
    }
    
    
    /** Calculate the dependencies for this graph */
    public Dependencies calculateDependencies( 
            Dependencies entryDependencies ) {
        Dependencies exitDependencies = null;
        //TODO complete calculation of dependencies and
        //TODO return the dependencies at the exit vertex
        
        entry.setDepends(entryDependencies);
//        Map<ControlFlowNode,Dependencies> seen = new HashMap<ControlFlowNode,Dependencies>(); 
        Map<ControlFlowNode,Integer> changedCountMap = new HashMap<ControlFlowNode,Integer>();
        Map<ControlFlowNode,Set<AdjacentEdge<ControlFlowNode, Primitive>>> inNodeMap = 
        		new HashMap<ControlFlowNode,Set<AdjacentEdge<ControlFlowNode, Primitive>>>();
        visit(entry, changedCountMap, 0, inNodeMap);
        return exit.getDepends();
    }
    
    private void visit(ControlFlowNode u, Map<ControlFlowNode,Integer> changedCountMap, int changedCount,
    		Map<ControlFlowNode,Set<AdjacentEdge<ControlFlowNode, Primitive>>> inNodeMap) {
//    	System.out.println("Discovered: " + u + " at a count of: " + changedCount);
    	Integer oldChangedCount = changedCountMap.get(u);
    	if (!(oldChangedCount != null && oldChangedCount > changedCount)) {
    		// Update the changedCount but don't decrease it
    		changedCountMap.put(u, changedCount);
    	}
    	    	
        for (AdjacentEdge<ControlFlowNode, Primitive> e : graph.adjacent(u)) {
        	ControlFlowNode v = e.target;
        	Primitive p = e.edgeInfo;
        	Dependencies oldDependencies = v.getDepends();
   			Dependencies newDependencies = p.calculateDependencies(u.getDepends());
   			if (highInDegree(inNodeMap, e, v)) {
   				newDependencies.merge(oldDependencies);
   			}
   			
   			v.setDepends(newDependencies);
   			int newChangedCount = changedCount;
   			if (!newDependencies.equals(oldDependencies)) {
   				// Dependencies have changed
   				newChangedCount++;
   			}
   			
   			// Check if nothing has changed since the last time we saw this node
   			if (!(changedCountMap.containsKey(v) && changedCountMap.get(v).equals(newChangedCount))) {
   				// Either we haven't seen the node or something has changed since last time we saw it
   				visit(v, changedCountMap, newChangedCount, inNodeMap);
   			}
        }
//        System.out.println("Finished: " + u);
    }
    
    private boolean highInDegree(Map<ControlFlowNode,Set<AdjacentEdge<ControlFlowNode, Primitive>>> inNodeMap, AdjacentEdge<ControlFlowNode, Primitive> e, ControlFlowNode v) {
    	Set<AdjacentEdge<ControlFlowNode, Primitive>> inEdgeSet = inNodeMap.get(v);
    	if (inEdgeSet == null) {
    		inEdgeSet = new HashSet<AdjacentEdge<ControlFlowNode, Primitive>>();
    	}
    	inEdgeSet.add(e);
    	inNodeMap.put(v, inEdgeSet);
    	if (inEdgeSet.size() > 1) {
    		return true;
    	}
    	return false;    	
    }

    /*
    public Dependencies calculateDependencies( 
            Dependencies entryDependencies ) {
        Dependencies exitDependencies = null;
        
        entry.setDepends(entryDependencies);
//        Map<ControlFlowNode,Integer> changedCountMap = new HashMap<ControlFlowNode,Integer>();
        
        bfs(entry);
        return exit.getDepends();
    }
    
    public void bfs(ControlFlowNode u) {
    	Queue<ControlFlowNode> Q = new LinkedList<ControlFlowNode>();
    	Set<ControlFlowNode> V = new HashSet<ControlFlowNode>();
    	V.add(u);
    	Q.add(u);
    	while (!Q.isEmpty()) {
    		ControlFlowNode t = Q.remove();
    		for (AdjacentEdge<ControlFlowNode, Primitive> e : graph.adjacent(t)) {
    			ControlFlowNode v = e.target;
    			Primitive p = e.edgeInfo;
       			Dependencies newDependencies = p.calculateDependencies(u.getDepends());
       			v.setDepends(newDependencies);
    		}
    	}
    	
    }
    */
    
    public String toString() {
        String result = "Entry = " + entry + " Exit = " + exit + "\n";
        // As the graph is connected we just print the edges
        for( ControlFlowNode n : graph ) {
            for( AdjacentEdge<ControlFlowNode,Primitive> 
                    e : graph.adjacent(n) ) {
                result += "  " + n + " -> " + e.target + " " +
                        e.edgeInfo + " " + "\n";
            }
        }
        return result;
    }
}
