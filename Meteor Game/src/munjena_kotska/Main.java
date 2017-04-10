package munjena_kotska;

import java.awt.Color;

public class Main 
{
	final static int width = 1920;
	final static int height = 1080;
	final static int delay = 10;
	
	public static void main(String args[]) throws InterruptedException {
		
		final int locx = (width / 2) - 400;
		final int locy = (height / 2) - 400;
		final Color bg = Color.BLACK;
		

		GameBoard.C.setLocation(locx, locy);
		GameBoard.C.setBackgroundColor(bg);
		GameBoard.C.clear();		
		
		ObjectMan.spawn();

		do 
		{
			synchronized (GameBoard.C) {
				
				// Draw the background.
				GameBoard.drawBoard();
				GameBoard.drawPlayableArea();		
				
				// Register key presses.
				KeyDirective.checkPlayerMoveKeys();	
				
				// Spawn Entities.
				ObjectMan.spawn();				
				
				// Update the entities.
				ObjectMan.playerBounds(GameBoard.P);
				ObjectMan.update(0);
				ObjectMan.mapBounds(0);					
								
				// Draw the UI.
				UIDirective.draw();
				
			}

			Thread.sleep(delay);
		}
		while (true);

	}
}