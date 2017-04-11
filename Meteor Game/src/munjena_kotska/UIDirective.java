package munjena_kotska;

import hsa_ufa.Console;

import java.awt.Color;

/**
 * Handles UI drawing and events
 * 
 * @author Robert
 *
 */
public class UIDirective
{
	public static final int SCORE_X0 = 10, SCORE_Y0 = 10, SCORE_W = 100, SCORE_H = 100;
	public static final int INFO_X0 = 0, INFO_Y0 = SCORE_W, INFO_W = SCORE_W, INFO_H = GameBoard.SIZE_Y - SCORE_H;
	private Console C;

	public UIDirective(Console C)
	{
		this.C = C;
		draw();
	}

	/**
	 * Draws the game UI to the screen.
	 */
	public void draw()
	{
		drawScore();
	}

	/**
	 * Draws the score screen to the board
	 */
	public void drawScore()
	{
		C.setColor(Color.DARK_GRAY);
		C.fillRect(SCORE_X0, SCORE_Y0, SCORE_W, SCORE_H);
		C.setColor(Color.WHITE);
		C.drawRect(SCORE_X0, SCORE_Y0, SCORE_W, SCORE_H);
	}

	/**
	 * Draws the info board to the screen
	 */
	public void drawInfo()
	{
		C.setColor(Color.GRAY);
		C.fillRect(INFO_X0, INFO_Y0, INFO_W, INFO_H);
		C.setColor(Color.WHITE);
		C.drawRect(INFO_X0, INFO_Y0, INFO_W, INFO_H);
	}

}
