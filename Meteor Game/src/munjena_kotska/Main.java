package munjena_kotska;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main
{
	final static Dimension SDIM = Toolkit.getDefaultToolkit().getScreenSize();
	final static double WIDTH = SDIM.getWidth();
	final static double HEIGHT = SDIM.getHeight();
	final static int DELAY = 20;

	public static void main(String args[]) throws InterruptedException
	{
		final int locX = (int) (WIDTH - GameBoard.SIZE_X) / 2;
		final int locY = (int) (HEIGHT - GameBoard.SIZE_Y) / 2 - 20;
		final Color bg = Color.BLACK;

		GameBoard GB = new GameBoard(locX, locY, bg);
		ObjectMan OM = new ObjectMan(GB);

		do
		{
			long s = System.nanoTime();
			synchronized (GB.C)
			{

				// Register key presses.
				KeyDirective.checkPlayerMoveKeys(GB);

				// Spawn Entities.
				OM.spawn();

				// Update the entities.
				GB.update();
				OM.update();

			}

			long e = System.nanoTime();
			Thread.sleep(DELAY - (e - s) / 1000000);
		} while (true);

	}
}