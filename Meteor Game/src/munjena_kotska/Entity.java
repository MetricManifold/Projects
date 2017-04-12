package munjena_kotska;

import java.awt.Color;
import hsa_ufa.Console;

public abstract class Entity
{

	// Entity attributes.
	int sizeX, sizeY;
	float moveX, moveY;
	Color color;

	// Position of the upper left corner.
	float posX, posY;

	/**
	 * Creates a new Entity.
	 * 
	 * @param posX
	 * @param posY
	 * @param sizeX
	 * @param sizeY
	 */
	public Entity(float posX, float posY, int sizeX, int sizeY)
	{
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	/**
	 * Draws the Entity to the screen.
	 */
	public void draw(Console C)
	{
		C.setColor(color);
		C.fillRect((int) posX, (int) posY, sizeX, sizeY);
		C.setColor(Color.black);
		C.drawRect((int) posX, (int) posY, sizeX, sizeY);
	}

	/**
	 * Moves the Entity.
	 */
	public void move()
	{
		posX += moveX;
		posY += moveY;
	}

	/**
	 * Sets Entity direction.
	 * 
	 * @param moveX
	 * @param moveY
	 */
	public void setDirection(float moveX, float moveY)
	{
		this.moveX = moveX;
		this.moveY = moveY;
	}

}
