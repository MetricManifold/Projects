package Object;

import Kotska.Kotska;

public class Hexagon extends Shape
{
	/**
	 * Makes a hexagon and draws it to the screen.
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
	public Hexagon(int x_pos, int y_pos, int x_size, int y_size, int mass)
	{
		super(x_pos, y_pos, x_size, y_size, mass);
	}

	/**
	 * Adds the points of the rectangle to use for collision detection.
	 */
	protected void addPoints()
	{
		addVertex(X() + x_size / 3, Y());
		addVertex(X() + 2 * x_size / 3, Y());
		addVertex(X() + x_size, Y() + y_size / 2);
		addVertex(X() + 2 * x_size / 3, Y() + y_size);
		addVertex(X() + x_size / 3, Y() + y_size);
		addVertex(X(), Y() + y_size / 2);

		normals_to_make = 3;
	}

	public void draw()
	{
		Kotska.c.fillPolygon(vertices.X(), vertices.Y(), 6);
	}

	public Hexagon getCopy()
	{
		Hexagon h = new Hexagon((int) x_pos, (int) y_pos, x_size, y_size, momentum.mass);

		/*
		 * Set data
		 */

		return h;
	}
}
