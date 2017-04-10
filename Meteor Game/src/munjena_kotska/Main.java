package munjena_kotska;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main
{
	final static Dimension SDIM = Toolkit.getDefaultToolkit().getScreenSize();
	final static double WIDTH = SDIM.getWidth();
	final static double HEIGHT = SDIM.getHeight();
	final static int DELAY = 10;

	public static void main(String args[]) throws InterruptedException
	{
		final int locx = (int) (WIDTH / 2) - 400;
		final int locy = (int) (HEIGHT / 2) - 400;
		final Color bg = Color.BLACK;
		
		GameBoard GB = new GameBoard(locx, locy, bg);
		ObjectMan OM = new ObjectMan(GB);
		
		OM.spawn();

		do
		{
			synchronized (GB.C)
			{
				// Register key presses.
				KeyDirective.checkPlayerMoveKeys(GB);

				// Spawn Entities.
				OM.spawn();

				// Update the entities.
				OM.playerBounds(GB.P);
				OM.update();
				OM.mapBounds();

				// Draw the UI.
				UIDirective.draw(GB.C);
			}

			Thread.sleep(DELAY);
		} while (true);

	}
}