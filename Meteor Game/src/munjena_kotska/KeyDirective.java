package munjena_kotska;

import hsa_ufa.Console;

/**
 * Handles key actions.
 * 
 * @author Robert
 *
 */
public class KeyDirective
{

	/**
	 * Checks the player movement keys (
	 */
	public static void checkPlayerMoveKeys(GameBoard GB)
	{
		Console C = GB.C;
		Player P = GB.P;

		int moveX = 0, moveY = 0;
		
		if (C.isKeyDown('A'))
		{
			moveX--;
		}

		if (C.isKeyDown('D'))
		{
			moveX++;
		}

		if (C.isKeyDown('W'))
		{
			moveY--;
		}

		if (C.isKeyDown('S'))
		{
			moveY++;
		}

		moveX *= P.moveSpeed;
		moveY *= P.moveSpeed;

		P.setDirection(moveX, moveY);
	}
}
