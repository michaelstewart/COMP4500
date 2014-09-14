package dependencies;

//import java.io.PrintStream;
import java.util.TreeMap;
import java.util.SortedMap;

public class Dependencies {
    
    /** Maps each variable name to the names of variables it depends on.
     * A SortedMap is used so that dependencies print in order.
     */
    private SortedMap<String,DependSet> dependencies;
        
    /** Construct empty dependencies */
    public Dependencies() {
        super();
        dependencies = new TreeMap<String,DependSet>();
    }
    /** Add a dependency between a variable and a set of variables */
    public Dependencies put( String var, DependSet vars ) {
        dependencies.put( var,  vars );
        return this;
    }
    /** Get a dependency between a variable and a set of variables */
    public DependSet get(String var) {
    	DependSet dependSet = dependencies.get(var);
    	if (dependSet == null) {
    		dependSet = new DependSet();
    	}
    	return dependSet;
    }
    /** Dependency equality requires that the dependencies are 
     * identical for all variables */
    public boolean equals( Dependencies other ) {
        return dependencies.equals( other.dependencies );
    }
    /** Construct dependencies that are a copy of this */
    public Dependencies copy() {
        Dependencies newCopy = new Dependencies();
        for( SortedMap.Entry<String, DependSet> entry : dependencies.entrySet() ) {
            newCopy.dependencies.put( entry.getKey(), entry.getValue().copy() );
        }
        return newCopy;
    }
    public void replace(String var, DependSet vars) {
    	DependSet depend = null;
    	for (String v : vars.getDependencies()) {    		
    		DependSet d = get(v);
    		if (depend == null) {
    			depend = d;
    		} else {
    			depend.merge(d);
    		}
    	}
    	if (depend == null) {
    		depend = new DependSet();
    	}
    	put(var, depend); 
    }
    public String toString() {
        String result = "{";
        String sep = " ";
        for ( SortedMap.Entry<String, DependSet> entry : 
                dependencies.entrySet() ) {
            // Omit any empty dependencies when printing
            if( !entry.getValue().isEmpty() ) {
                result += sep + "(" + entry.getKey() + "," + 
                    entry.getValue().getDependencies() + ")";
                sep = ", ";
            }
        }
        return result + " }";
    }
}
