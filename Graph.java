import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

//////////////////////////////////////////////////
// some useful things you may end up importing:
//   import java.util.HashMap;
//   import java.util.HashSet;
//   import java.util.Stack;
//   import java.util.LinkedList;
//   import java.util.ArrayList;
//////////////////////////////////////////////////

public class Graph
{
    //////////////////////////////////////////////////
    // Add a field to store the vertices.
    //////////////////////////////////////////////////
    
    public Graph()
    {
	//////////////////////////////////////////////////
	// Don't forget to initialize the vertex storage field.
	//////////////////////////////////////////////////
    }
    
    public Vertex findVertex(String name)
    {
	//////////////////////////////////////////////////
	// Implement string-based lookup.
	//////////////////////////////////////////////////
	return null;
    }
    
    public Vertex addVertex(String name)
    {
	//////////////////////////////////////////////////
	// Create a new vertex, place it in the storage field, and
	// return it. When you add fields to the Vertex class, you may
	// need to add parameters to this method so that you can
	// properly initialize the new vertex instance here.
	//////////////////////////////////////////////////
	return null;
    }
    
    public Vertex getOrMakeVertex(String name)
    {
	//////////////////////////////////////////////////
	// If there already is a vertex with the given name in the
	// storage, return that. Otherwise, create a new vertex, add
	// it to the storage, and return the new vertex.
	//////////////////////////////////////////////////
	return null;
    }
    
    public Edge addEdge(Vertex source, Vertex destination)
    {
	//////////////////////////////////////////////////
	// Create a new Edge which goes from the source to the
	// destination. Add the new edge to the neighbors of the
	// source vertex. Return the new edge. When you later add
	// fields (such as cost) to the Edge class, you have to add
	// parameters to this method so that it can properly
	// initialize the new edge instance.
	//////////////////////////////////////////////////
	return null;
    }
    
    public Edge addEdge(String source, String destination)
    {
	//////////////////////////////////////////////////
	// When you later add fields (such as cost) to the Edge class,
	// you have to add parameters to this method so that it can
	// pass them on.
	//////////////////////////////////////////////////
	System.out.println("new edge: " + source + " --> " + destination);
	return addEdge(getOrMakeVertex(source), getOrMakeVertex(destination));
    }
    
    public boolean load(String filename, boolean undirected)
    {
	try {
	    BufferedReader in = new BufferedReader(new FileReader(filename));
	    String line;
	    while (null != (line = in.readLine())) {
		StringTokenizer st = new StringTokenizer(line);
		String source = st.nextToken();
		String destination = st.nextToken();
		//////////////////////////////////////////////////
		// Here is the place to add parsing of the third
		// element in the line, which contains the edge cost.
		//////////////////////////////////////////////////
		addEdge(source, destination);
		if (undirected) {
		    addEdge(destination, source);
		}
	    }
	}
	catch (NumberFormatException ee) {
	    System.err.println("format error in file " + filename
			       + "\n  (costs must be real numbers)");
	    return false;
	}
	catch (NoSuchElementException ee) {
	    System.err.println("syntax error in file " + filename
			       + "\n  (each line must have 3 entries: two strings and a double)");
	    return false;
	}
	catch (FileNotFoundException ee) {
	    System.err.println("no file called " + filename);
	    return false;
	}
	catch (IOException ee) {
	    System.err.println("input/output error in file " + filename);
	    return false;
	}
	return true;
    }
    
    public static void main(String[] args)
    {
	if (0 == args.length) {
	    System.err.println("filename expected");
	    System.exit(42);
	}
	Graph gg = new Graph();
	if ( ! gg.load(args[0], false)) {
	    System.err.println("failed to load graph from " + args[0]);
	}
    }
    
}
