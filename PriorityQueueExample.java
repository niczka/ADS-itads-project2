import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Random;

class SomeData
{
    public double foo;
    public String bar;
    
    public SomeData(double _foo, String _bar)
    {
	foo = _foo;
	bar = _bar;
    }
    
    public String toString()
    {
	return bar + " / " + foo;
    }
}


class ExampleOne
    implements Comparable<ExampleOne>
{
    int value;
    SomeData data;
    
    public ExampleOne(int _value, SomeData _data)
    {
	value = _value;
	data = _data;
    }
    
    public int compareTo(ExampleOne other)
    {
	if (value < other.value) {
	    return -1;
	}
	if (value > other.value) {
	    return 1;
	}
	return 0;
    }
    
    public String toString()
    {
	return data + "\t(value " + value + ")";
    }
}


class ExampleTwo
    implements Comparator<SomeData>
{
    public int compare(SomeData left, SomeData right)
    {
	if (left.foo < right.foo) {
	    return -1;
	}
	if (left.foo > right.foo) {
	    return 1;
	}
	return 0;
    }
}


public class PriorityQueueExample
{
    static private Random rnd = new Random();
    static private final String charbag = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static void main(String[] args)
    {
	//////////////////////////////////////////////////
	// initialize some data
	
	SomeData[] data = new SomeData[20];
	for (int ii = 0; ii < data.length; ++ii) {
	    StringBuilder sb = new StringBuilder();
	    for (int jj = 0; jj < 3; ++jj) {
		sb.append(charbag.charAt(rnd.nextInt(charbag.length())));
	    }
	    data[ii] = new SomeData(rnd.nextDouble() * 200.0 - 100.0, sb.toString());
	}
	
	//////////////////////////////////////////////////
	// demo of priority queue based on subclassing the java.lang.Comparable interface
	
	System.out.println("Example 1: using a Comparable with an extra 'value' field");
	
	// NOTE: the queue stores ExampleOne objects, which contain
	//       the SomeData object inside of them
	PriorityQueue<ExampleOne> pqOne = new PriorityQueue<ExampleOne>();
	for (int ii = 0; ii < data.length; ++ii) {
	    int value = rnd.nextInt(5);
	    // NOTE: each queue entry gets its own new wrapper object;
	    //       we could also make SomeData a subclass of
	    //       Comparable of course, but then we would not be
	    //       able to use different comparison approaches
	    //       depending on the needs of the application
	    if (pqOne.add(new ExampleOne(value, data[ii]))) {
		System.out.println("  inserted " + data[ii] + " (value " + value
				   + ")\ttop: " + pqOne.peek());
	    }
	    else {
		System.out.println("  FAILED to insert " + data[ii] + " (value " + value + ")");
	    }
	}
	System.out.println("Emptying the queue:");
	while (true) {
	    ExampleOne ee = pqOne.poll();
	    if (null == ee) {
		break;
	    }
	    System.out.println("  " + ee);
	}
	System.out.println("NOTE: the above list goes by decreasing (value X)");
	
	//////////////////////////////////////////////////
	// demo of priority queue based on a separate java.util.Comparator functor
	
	System.out.println("\nExample 2: using a Comparator");
	
	// NOTE: the queue stores SomeData objects directly, the
	//       comparator is a separate functor object; the
	//       PriorityQueue constructor which takes the extra
	//       comparator argument also needs to receive an initial
	//       capacity
	PriorityQueue<SomeData> pqTwo = new PriorityQueue<SomeData>(11, new ExampleTwo());
	for (int ii = 0; ii < data.length; ++ii) {
	    // NOTE: we add data object directly, no need for wrapper objects
	    if (pqTwo.add(data[ii])) {
		System.out.println("  inserted " + data[ii] + "\ttop: " + pqTwo.peek());
	    }
	    else {
		System.out.println("  FAILED to insert " + data[ii]);
	    }
	}
	System.out.println("Emptying the queue:");
	while (true) {
	    SomeData sd = pqTwo.poll();
	    if (null == sd) {
		break;
	    }
	    System.out.println("  " + sd);
	}
	System.out.println("NOTE: the above list goes by decreasing number after the /");
    }
}
