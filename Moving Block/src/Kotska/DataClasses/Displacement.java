package Kotska.DataClasses;

import Kotska.SMath;

/**
 * A vector defining a displacement in terms of x and y float values.
 */
public class Displacement extends Pair<Float> implements DataObjectBase<Pair<Float>> {
	
	protected String ID;
	
	protected double angle;
	protected float magnitude;
	
	public Displacement(float a, float b)
	{
		super(a, b);
		update();
	}
	
	public Displacement(double a, double b)
	{
		super((float) a, (float) b);
		update();
	}
	
	/**
	 * Returns the magnitude.
	 * @return magnitude
	 */
	public float getMagnitude()
	{
		return magnitude;
	}
	
	/**
	 * Returns the angle.
	 * @return angle
	 */
	public double getAngle()
	{
		return angle;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public void setID(String ID)
	{
		this.ID = ID;
	}
	
	/**
	 * Returns a copy of this object.
	 */
	public Displacement getCopy()
	{
		return new Displacement(a, b);
	}
	
	/**
	 * Scales the displacement by multiplying the magnitude by the given scale.
	 * @param scale : Amount to be scaled by.
	 */
	public void scale(float scale)
	{
		magnitude *= scale;
		a = (float) (magnitude * Math.cos(angle));
		b = (float) (magnitude * Math.sin(angle));
	}
	
	/**
	 * Will normalise this displacement and turn it into a direction.
	 * @return direction
	 */
	public Direction getDirection()
	{
		return new Direction(a, b);
	}
	
	/**
	 * Makes this direction perpendicular.
	 */
	public void perpendicularize() 
	{
		float temp = a;
		a = -b;
		b = temp;
		update();
	}

	/**
	 * Reverses the direction.
	 */
	public void reverse()
	{
		a *= -1;
		b *= -1;
		update();
	}
	
	/**
	 * Will add to this displacement the given one.
	 * @param d : Displacement.
	 */
	public void add(Displacement d)
	{
		this.a += d.A();
		this.b += d.B();
		update();
	}
	
	protected void update()
	{
		magnitude = (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		angle = SMath.getAngle(a, b);
	}

}