package Object;

/**
 * Generates square shape.
 * 
 * @param x_pos
 *            : Top left x coordinate.
 * @param y_pos
 *            : Top left y coordinate.
 * @param s_size
 *            : Side length of the shape.
 * @param mass
 *            : Mass of the shape.
 */
public class Square extends Rectangle
{

	public Square(int x_pos, int y_pos, int s_size, int mass)
	{
		super(x_pos, y_pos, s_size, s_size, mass);
	}

}
