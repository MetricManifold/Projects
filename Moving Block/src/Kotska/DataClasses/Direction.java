package Kotska.DataClasses;

/**
 * A vector defining a direction.
 */
public class Direction extends Displacement {
	
	public Direction(float a, float b)
	{
		super(a, b);
			
		this.a /= magnitude;
		this.b /= magnitude;
		
		magnitude = 1;
	}
	
	/**
	 * Returns a copy of this object.
	 */
	public Direction getCopy()
	{
		return new Direction(a, b);
	}
	
}












