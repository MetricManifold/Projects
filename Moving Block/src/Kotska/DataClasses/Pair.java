package Kotska.DataClasses;

public class Pair<T> {
	protected T a, b;
	
	public Pair(T a, T b)
	{
		this.a = a;
		this.b = b;
	}
	
	public T A()
	{
		return a;
	}
	
	public T B()
	{
		return b;
	}
}
