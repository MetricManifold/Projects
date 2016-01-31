package munjena_kotska;

/**
 * The Player object.
 * @author Robert
 *
 */
public class Player extends Entity {

	String rank = "Bronze";
	int kills = 0;
	int score = 0;
	
	float moveSpeed = 2f;

	/**
	 * Initializes the Player Entity
	 * @param posX
	 * @param posY
	 */
	public Player(float posX, float posY)
	{
		super(posX, posY, 25, 25);
	}

}
