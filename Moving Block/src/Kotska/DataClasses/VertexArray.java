
package DataClasses;

import Object.ShapeTooComplexException;

public class VertexArray implements DataObjectBase<VertexArray>
{

	private String ID;

	/**
	 * The maximum number of vertices that a polygon may have
	 */
	public final static int MAX_VERTICES = 16;

	private int x[] = new int[MAX_VERTICES];
	private int y[] = new int[MAX_VERTICES];
	short vertex_index = 0;

	public void addVertex(int x_new, int y_new) throws ShapeTooComplexException
	{
		if (vertex_index >= MAX_VERTICES)
			throw new ShapeTooComplexException();

		x[vertex_index] = x_new;
		y[vertex_index] = y_new;

		vertex_index++;
	}

	/**
	 * Moves all vertices to match with the given point array.
	 * 
	 * @param p
	 *            : Point array to translate this to.
	 */
	public void moveTo(PointArray p)
	{
		if (vertex_index != p.point_index)
			throw new IllegalArgumentException("Cannot move VertexArray to PointArray.");

		for (int i = 0; i < vertex_index; i++)
		{
			x[i] = p.pointInt(i).A();
			y[i] = p.pointInt(i).B();
		}
	}

	/**
	 * Returns the number of vertices in the array.
	 * 
	 * @return Number of vertices
	 */
	public int size()
	{
		return vertex_index;
	}

	/**
	 * Returns a copy of the position array.
	 */
	public VertexArray getCopy()
	{
		VertexArray p = new VertexArray();

		for (int i = 0; i < vertex_index; i++)
		{
			try
			{
				p.addVertex(x[i], y[i]);
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
	 * @return All x coordinates
	 */
	public int[] X()
	{
		return x;
	}

	/**
	 * @return All y coordinates
	 */
	public int[] Y()
	{
		return y;
	}

	/**
	 * @param i
	 *            index
	 * @return vertex as a Pair Object
	 */
	public Pair<Integer> point(int i)
	{
		return new Pair<Integer>(x[i], y[i]);
	}
}
