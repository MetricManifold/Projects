package Kotska;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import Kotska.DataClasses.ObjectMan;
import hsa_ufa.Console;
import Kotska.Object.Hexagon;
import Kotska.Object.Rectangle;
import Kotska.Object.Triangle;

public class Kotska {

	public static final int 
	MAX_HEIGHT = 800,
	MAX_WIDTH = 800,
	DIM_BUFF = 55;

	public static Console c = new Console(MAX_WIDTH + 55, MAX_WIDTH + 55, "Kotska");

	public static boolean 
	W_down = false,
	A_down = false,
	S_down = false,
	D_down = false;
	
	public static int test = 0;
	
	static boolean onLSD = false;

	public static void main(String args[]) throws InterruptedException 
	{
		/*
		 * Enable mouse methods
		 */
		c.enableMouseMethods();

		final int fX = 170;
		final int fY = 240;

		Image faces[] = new Image[5];
		try 
		{
			Image temp[] = {
					ImageIO.read(new File("normal_s.jpg")),  
					ImageIO.read(new File("hurt_up_s.jpg")),
					ImageIO.read(new File("hurt_right.jpg")), 
					ImageIO.read(new File("hurt_down_s.jpg")), 
					ImageIO.read(new File("hurt_left_s.jpg"))
			};
			faces = temp;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		Rectangle box_1 = new Rectangle(0, 0, 100, 150, 20);
		Rectangle box_2 = new Rectangle(500, 600, 100, 120, 31);
		Rectangle box_3 = new Rectangle(450, 20, 135, 25, 12);
		Triangle tri_1 = new Triangle(300, 300, 100, 100, 8);
		Hexagon hex_1 = new Hexagon(500, 300, 200, 150, 52);

		//Square character = new Square(c.getDrawWidth() / 2 - 100, c.getDrawHeight() / 2 + 45, 50);
		Player p = new Player(c.getDrawWidth() / 2 - 100, c.getDrawHeight() / 2 + 45, (int)(fX * 0.4), (int)(fY * 0.4), 10, faces);
		//Oval obstacle = new Oval(c.getDrawWidth() / 2, c.getDrawHeight () / 2, 100, 200);

		ObjectMan.addObjects(box_1, box_2, box_3, tri_1, hex_1 /*, obstacle */);
		ObjectMan.addObjects(p);

		int R = 30;
		int G = 30;
		int B = 30;

		int random_tracker = 0;

		
		random_tracker %= 100;
		if (random_tracker == 0)	
			ObjectMan.randomTrajectories()
		;

		
		
		c.setFont(new Font("Helvetica", Font.PLAIN, 40));
		box_1.setColor(Color.RED);
		box_2.setColor(Color.cyan);
		box_3.setColor(Color.magenta);
		hex_1.setColor(Color.yellow);
		
		double thistime, lasttime = System.currentTimeMillis();
		
		double peak = 20;
		Physics.update();
		do {

			synchronized(c) {

				c.setBackgroundColor(new Color(R, G, B));
				c.clear();
				
				//Text
				random_tracker++;
				thistime = System.currentTimeMillis();
				c.drawString((thistime - lasttime) + " Peak: " + peak, 100, 100);
				random_tracker %= 1000;
				if (random_tracker == 0) peak = 20;
				if (thistime - lasttime > peak) peak = thistime - lasttime;
				
				lasttime = thistime;
				
				/*
				 * Perform all update operations
				 */
				
				ObjectMan.update();
				p.updatePos();
				Physics.update();
				Render.draw();
				
				/*
				 * Update the character
				 */
				p.updateImage();
			}
			
			if (onLSD)
			{
				R += 1;
				G += 3;
				B += 2;

				if (R > 255) R = 64;
				if (G > 255) G = 8;
				if (B > 255) B = 0;
			}

			Thread.sleep(20);

			if (c.isKeyDown('A'))
				A_down = true;
			else
				A_down = false;

			if (c.isKeyDown('D'))
				D_down = true;
			else
				D_down = false;

			if (c.isKeyDown('W'))
				W_down = true;
			else
				W_down = false;

			if (c.isKeyDown('S'))
				S_down = true;
			else
				S_down = false;



		} while (true);

		//c.clear();
		//wc.print("Game Over");

	}
}