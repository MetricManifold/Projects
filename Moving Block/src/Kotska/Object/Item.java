package Object;

import java.awt.Image;

import Kotska.Kotska;

public class Item extends Rectangle
{

	protected Image img;

	public Item(int x, int y, int width, int height, int mass, Image img)
	{
		super(x, y, width, height, mass);
		this.img = img;
	}

	public void draw()
	{
		Kotska.c.drawImage(img, X(), Y(), x_size, y_size);
	}

	public Item getCopy()
	{
		Item s = new Item((int) x_pos, (int) y_pos, x_size, y_size, momentum.mass, img);
		copyData(s);
		return s;
	}

}
