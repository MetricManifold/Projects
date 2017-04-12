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
	public static final int SCORE_RANK_X0 = SCORE_X0 + 10, SCORE_RANK_Y0 = SCORE_Y0 + 20;
	public static final int SCORE_SCORE_X0 = SCORE_X0 + 10, SCORE_SCORE_Y0 = SCORE_Y0 + 40;

	private Console C;
	private Player P;

	public UIDirective(GameBoard GB)
	{
		this.C = GB.C;
		this.P = GB.P;
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

		// rank and score
		C.drawString(P.rank, SCORE_RANK_X0, SCORE_RANK_Y0);
		C.drawString(String.valueOf(P.score), SCORE_SCORE_X0, SCORE_SCORE_Y0);
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
