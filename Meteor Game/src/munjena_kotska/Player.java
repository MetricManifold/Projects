package munjena_kotska;

/**
 * The Player object.
 * 
 * @author Robert
 *
 */
public class Player extends Entity
{

	String rank = "Bronze";
	int kills = 0;
	int score = 0;

	float moveSpeed = 10F;

	/**
	 * Initializes the Player Entity
	 * 
	 * @param posX
	 * @param posY
	 */
	public Player(float posX, float posY)
	{
		super(posX, posY, 25, 25);
	}

	/**
	 * Updates the Player rank.
	 */
	public void updateRank()
	{

		switch ((score + kills * 100) / 100)
		{
		case 0:
			rank = "Bronze";
			break;
		case 1:
			rank = "Silver";
			break;
		case 2:
			rank = "Gold";
			break;
		case 3:
			rank = "Platinum";
			break;
		case 4:
			rank = "Diamond";
			break;
		case 5:
			rank = "Master";
			break;
		case 6:
			rank = "Grandmaster";
			break;
		}

	}

	/**
	 * Evaluates the bounds and movement of player to return new translation
	 * vector.
	 * 
	 * @param P
	 */
	public void move()
	{
		if (posX <= GameBoard.P_BOUNDS_X0 && moveX < 0)
		{
			posX = GameBoard.P_BOUNDS_X0;
			moveX = 0;
		}
		if (posX + sizeX >= GameBoard.SIZE_X && moveX > 0)
		{
			posX = GameBoard.SIZE_X - sizeY;
			moveX = 0;
		}
		if (posY <= GameBoard.P_BOUNDS_Y0 && moveY < 0)
		{
			posY = GameBoard.P_BOUNDS_Y0;
			moveY = 0;
		}
		if (posY + sizeY >= GameBoard.SIZE_Y && moveY > 0)
		{
			posY = GameBoard.SIZE_Y - sizeY;
			moveY = 0;
		}
		
		super.move();
		score++;
		updateRank();
	}

}
