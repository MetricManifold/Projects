package Kotska;

import java.awt.Image;

import DataClasses.Displacement;
import DataClasses.Thrust;
import Object.ItemInteracting;

public class Player extends ItemInteracting
{

	Thrust thrust;
	public boolean is_moving;

	/**
	 * Creates a player object with the specified sprites.
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
	 * @param img_array
	 *            : The sprites used in moving.
	 */
	public Player(int x_pos, int y_pos, int x_size, int y_size, int mass, Image[] img_array)
	{
		super(x_pos, y_pos, x_size, y_size, mass, img_array);

		thrust = new Thrust(this, 1000, 5);
	}

	public void updateImage()
	{
		if (Kotska.D_down)
			setImage(2);
		else if (Kotska.A_down)
			setImage(4);
		else if (Kotska.W_down)
			setImage(1);
		else if (Kotska.S_down)
			setImage(3);
		else
			setImage(0);
	}

	/**
	 * Returns the shift of the shape as a Pair according to its momentum.
	 * 
	 * @return Shift
	 */
	protected Displacement getShift()
	{
		float x_shift = 0;
		float y_shift = 0;

		/*
		 * Change speed based on button press
		 */
		if (Kotska.D_down && !Kotska.A_down)
			x_shift += 1;
		if (Kotska.A_down && !Kotska.D_down)
			x_shift -= 1;
		if (Kotska.W_down && !Kotska.S_down)
			y_shift -= 1;
		if (Kotska.S_down && !Kotska.W_down)
			y_shift += 1;

		if (x_shift != 0 || y_shift != 0)
		{
			double travel_angle = SMath.getAngle(x_shift, y_shift);
			thrust.setAngle(travel_angle);
			thrust.toggle(true);
		}
		else
			thrust.toggle(false);

		thrust.update();
		momentum.dissipate(0.35f);

		return momentum.getVelocity();
	}
}
