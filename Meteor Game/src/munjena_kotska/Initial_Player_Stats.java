package munjena_kotska;

public class Initial_Player_Stats
{

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