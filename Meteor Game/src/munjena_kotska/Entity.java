package munjena_kotska;

import java.awt.Color;

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

		ObjectMan.addEntity(this);
	}

	/**
	 * Draws the Entity to the screen.
	 */
	public void draw()
	{
		GameBoard.C.setColor(color);
		GameBoard.C.fillRect((int) posX, (int) posY, sizeX, sizeY);
		GameBoard.C.setColor(Color.black);
		GameBoard.C.drawRect((int) posX, (int) posY, sizeX, sizeY);

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
