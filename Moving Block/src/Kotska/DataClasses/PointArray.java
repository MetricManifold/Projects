package Kotska.DataClasses;

import Kotska.Object.ShapeTooComplexException;

public class PointArray implements DataObjectBase<PointArray> {
	
	private String ID;
	
	/**
	 * The maximum number of vertices that a polygon may have
	 */
	public final static int MAX_VERTICES = 16;
	
	private float x[] = new float[MAX_VERTICES];
	private float y[] = new float[MAX_VERTICES];
	short point_index = 0;
	
	public void addPoint(float x_new, float y_new) throws ShapeTooComplexException
	{
		if (point_index >= MAX_VERTICES)
			throw new ShapeTooComplexException();
		
		x[point_index] = x_new;
		y[point_index] = y_new;
		
		point_index++;
	}
	
	/**
	 * Shifts all vertices in the array
	 * @param x_shift
	 * @param y_shift
	 */
	public void shift(float x_shift, float y_shift)
	{
		for (int i = 0; i < point_index; i++)
		{
			x[i] += x_shift;
			y[i] += y_shift;
		}
	}
	
	/**
	 * Moves all points to match with the given vertex array.
	 * @param v : Vertex array to translate this to.
	 */
	public void moveTo(VertexArray v)
	{
		if (point_index != v.vertex_index)
			throw new IllegalArgumentException("Cannot move PointArray to VertexArray.");
		
		for (int i = 0; i < point_index; i++)
		{
			x[i] = v.point(i).A();
			y[i] = v.point(i).A();
		}
	}
	
	/**
	 * Returns a copy of the position array.
	 */
	public PointArray getCopy()
	{
		PointArray p = new PointArray();
		
		for (int i = 0; i < point_index; i++)
		{
			try 
			{
				p.addPoint(x[i], y[i]);
			} 
			catch (ShapeTooComplexException e) 
			{
				e.printStackTrace();
			}
		}
		
		return p;
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
	 * Returns the number of vertices in the array
	 * @return
	 */
	public int size()
	{
		return point_index;
	}
	
	/**
	 * @return all x coordinate
	 */
	public float[] X()
	{
		return x;
	}
	
	/**
	 * @return all y coordinate
	 */
	public float[] Y()
	{
		return y;
	}
	
	/**
	 * Will return a point as a float at the specified index.
	 * @param i : index
	 * @return A point
	 */
	public Pair<Float> pointFloat(int i)
	{
		return new Pair<Float>(x[i], y[i]);
	}
	
	/**
	 * Will return a point as an integer at the specified index.
	 * @param i : index
	 * @return A point
	 */
	public Pair<Integer> pointInt(int i)
	{
		return new Pair<Integer>((int) x[i], (int) y[i]);
	}
}
