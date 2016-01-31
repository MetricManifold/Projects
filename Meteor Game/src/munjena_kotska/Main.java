package munjena_kotska;

import java.awt.Color;

public class Main 
{
	public static void main(String args[]) throws InterruptedException {
		
		// Delay between ticks.
		int delay = 10;

		GameBoard.C.setLocation((1920 / 2) - 400, (1080 / 2) - 400);
		GameBoard.C.setBackgroundColor(Color.BLACK);
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