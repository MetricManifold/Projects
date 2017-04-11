package Object;

import Kotska.Kotska;

public class Triangle extends Shape
{

	/**
	 * Makes a triangle and draws it to the screen.
	 * 
	 * @param x_pos
	 *            : Top left x coordinate.
	 * @param y_pos
	 *            : Top left y coordinate.
	 * @param x_size
	 *            : Width of the shape.
	 * @param y_size
	 *            : Height of the shape.
	 * @param mass
	 *            : Mass of the shape.
	 */
	public Triangle(int x_pos, int y_pos, int x_size, int y_size, int mass)
	{
		super(x_pos, y_pos, x_size, y_size, mass);
	}

	/**
	 * Adds the points of the rectangle to use for collision detection.
	 */
	protected void addPoints()
	{
		addVertex(X(), Y() + y_size);
		addVertex(X() + x_size / 2, Y());
		addVertex(X() + x_size, Y() + y_size);

		normals_to_make = 3;
	}

	/**
	 * Returns a copy of the shape.
	 */
	public Triangle getCopy()
	{
		Triangle s = new Triangle((int) x_pos, (int) y_pos, x_size, y_size, momentum.mass);
		copyData(s);
		return s;
	}

	public void draw()
	{
		Kotska.c.fillPolygon(vertices.X(), vertices.Y(), 3);
	}
}
