package Kotska.Object;

import java.awt.Image;

import Kotska.Kotska;

public abstract class ItemInteracting extends Item {

	protected Image img_array[];
	
	public ItemInteracting(int x_pos, int y_pos, int x_size, int y_size, int mass, Image img_array[]) {
		super(x_pos, y_pos, x_size, y_size, mass, img_array[0]);
		this.img_array = img_array;
	}

	/**
	 * Draws the shape with the default index
	 */
	public void draw()
	{
		Kotska.c.drawImage(img, X(), Y(), x_size, y_size);
	}
	
	/**
	 * Changes the main image to one within the index
	 * @param image index
	 */
	public void setImage(int i)
	{
		img = img_array[i];
	}
	
	/**
	 * Will update the image of the object.
	 */
	public abstract void updateImage();
	
}
