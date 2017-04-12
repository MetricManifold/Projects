package Object;

import Kotska.Kotska;

/**
 * Generates ellipse shape.
 * 
 */
public class Oval extends Shape
{

	/**
	 * Makes an oval and draws it to the screen
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
	public Oval(int x_pos, int y_pos, int x_size, int y_size, int mass)
	{
		super(x_pos, y_pos, x_size, y_size, mass);
	}

	/**
	 * Adds the center of the shape to the point array.
	 */
	protected void addPoints()
	{
		addVertex(Y() + x_size / 2, Y() + y_size / 2);
	}

	/**
	 * Returns a copy of the shape.
	 */
	public Oval getCopy()
	{
		Oval s = new Oval((int) x_pos, (int) y_pos, x_size, y_size, momentum.mass);
		copyData(s);
		return s;
	}

	/**
	 * Draws the oval to the screen.
	 */
	public void draw()
	{
		Kotska.c.fillOval(X(), Y(), x_size, y_size);
	}

	public boolean isRound()
	{
		return true;
	}

}
