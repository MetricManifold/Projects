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

	float moveSpeed = 2f;

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
	 * Updates the Player statistics.
	 */
	public static void update(Player P)
	{

		do
		{
			P.score = (P.kills / 5);

			int rankcase = 0;

			switch (rankcase)
			{
			case 0:
				P.rank = "Bronze";
				break;
			case 1:
				P.rank = "Silver";
				break;
			case 2:
				P.rank = "Gold";
				break;
			case 3:
				P.rank = "Platinum";
				break;
			case 4:
				P.rank = "Diamond";
				break;
			case 5:
				P.rank = "Master";
				break;
			case 6:
				P.rank = "Grandmaster";
				break;
			}

		} while (true);

	}

}
