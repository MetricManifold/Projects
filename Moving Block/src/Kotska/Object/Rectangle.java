package Kotska.Object;

import Kotska.Kotska;

/**
 * Generates rectangle shape.
 * 
 */
public class Rectangle extends Shape {

	/**
	 * Makes a rectangle and draws it to the screen.
	 * @param x_pos : Top left x coordinate.
	 * @param y_pos : Top left y coordinate.
	 * @param x_size : Width of the shape.
	 * @param y_size : Height of the shape.
	 * @param mass : Mass of the shape.
	 */
	public Rectangle(int x, int y, int width, int height, int mass)
	{
		super(x, y, width, height, mass);
	}
	
	/**
	 * Makes a rounded rectangle and draws it to the screen.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param radius
	 */
	Rectangle(int x, int y, int width, int height, int radius, int mass)
	{
		super(x, y, width, height, mass);
		draw(radius);
	}
	
	/**
	 * Adds the points of the rectangle to use for collision detection.
	 */
	protected void addPoints()
	{
		addVertex(X(), Y());
		addVertex(X() + x_size, Y());
		addVertex(X() + x_size, Y() + y_size);
		addVertex(X(), Y() + y_size);
		
		normals_to_make = 2;
	}
	
	/**
	 * Returns a copy of the shape.
	 */
	public Rectangle getCopy()
	{
		Rectangle s = new Rectangle((int) x_pos, (int) y_pos, x_size, y_size, momentum.mass);
		copyData(s);		
		return s;
	}
	
	public void draw()
	{
		Kotska.c.fillRect(X(), Y(), x_size, y_size);
	}
	
	public void draw(int radius)
	{
		Kotska.c.fillRoundRect(X(), Y(), x_size, y_size, radius, radius);
	}
}
