import java.util.LinkedList;

public class Vertex
{
    public LinkedList<Edge> neighbors;
    public final String name;           //
    public final int id;

    
    public Vertex(String _name, int _id)
    {
	neighbors = new LinkedList<Edge>();
    name = name_;                       //
    id = _id;                           //
    }
}
