package munjena_kotska;

import java.awt.Color;

public class Meteor extends Entity
{

	/**
	 * Initializes the Meteor Entity
	 * 
	 * @param posX
	 * @param posY
	 */
	public Meteor(float posX, float posY)
	{
		super(posX, posY, 25, 25);
		this.color = Color.gray;
	}

}