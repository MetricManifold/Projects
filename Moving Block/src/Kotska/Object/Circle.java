package Kotska.Object;

/**
 * Generates circle shape.
 * 
 */
public class Circle extends Oval {
	
	/**
	 * Makes a circle and draws it to the screen.
	 * @param x_pos : Top left x coordinate.
	 * @param y_pos : Top left y coordinate.
	 * @param x_size : Width of the shape.
	 * @param y_size : Height of the shape.
	 * @param mass : Mass of the shape.
	 */
	public Circle(int x_pos, int y_pos, int r_size, int mass)
	{
		super(x_pos, y_pos, r_size, r_size, mass);
	}
}
