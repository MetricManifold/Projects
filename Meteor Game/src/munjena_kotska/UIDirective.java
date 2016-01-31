package munjena_kotska;

import hsa_ufa.Console;

import java.awt.Color;

/**
 * Handles UI drawing and events
 * @author Robert
 *
 */
public class UIDirective {
	
	/**
	 * Draws the game UI to the screen.
	 */
	public static void draw()
	{
		Console C = GameBoard.C;
		
		C.setColor(Color.DARK_GRAY);
		C.fillRect(0, 0, 150, 800);
		C.setColor(Color.WHITE);
		C.drawRect(0, 0, 149, 799);
		C.drawRect(0, 0, 150, 800);

		C.setColor(Color.GRAY);
		C.fillRect(0, 0, 150, 200);
		C.setColor(Color.WHITE);
		C.drawRect(0, 0, 149, 199);
		C.drawRect(0, 0, 150, 200);
		
	}
	
}
