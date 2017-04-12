package DataClasses;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Random;

import Kotska.Physics;
import Kotska.Player;
import Object.Shape;

public class ObjectMan
{

	private static LinkedList<Shape> OBJECTS = new LinkedList<Shape>();
	private static Queue<Shape> ACTIVE_OBJECTS = new LinkedList<Shape>();

	/**
	 * Will add a new shape to the OBJECTS array.
	 * 
	 * @param shape
	 *            : a list of the shapes.
	 */
	public static void addObjects(Shape... shape)
	{
		// The elements array will automatically be expanded if the maximum
		// number of elements have been reached
		for (Shape s : shape)
			OBJECTS.add(s);
	}

	/**
	 * Adds an object to the array of objects which require state updates.
	 * 
	 * @param shape
	 *            : The object to be added.
	 */
	public static void addActiveObject(Shape shape)
	{
		ACTIVE_OBJECTS.add(shape);
	}

	/**
	 * Draws all shapes in the game.
	 */
	public static void drawShapes()
	{
		ListIterator<Shape> ol = OBJECTS.listIterator();

		while (ol.hasNext())
			ol.next().drawShape();
	}

	/**
	 * Updates the state of all active shapes.
	 */
	public static void update()
	{
		Queue<Shape> temp = new LinkedList<Shape>();

		while (!ACTIVE_OBJECTS.isEmpty())
		{
			temp.add(ACTIVE_OBJECTS.remove());
		}

		while (!temp.isEmpty())
		{
			temp.remove();
		}
	}

	/**
	 * Assigns a random speed and angle to all shapes.
	 */
	public static void randomTrajectories()
	{
		Random rand = new Random();
		double RNG_s = 0;
		double RNG_a = 0;
		Shape itr_shape;

		ListIterator<Shape> ol = OBJECTS.listIterator();

		while (ol.hasNext())
		{
			itr_shape = ol.next();
			if (!(itr_shape instanceof Player))
			{
				RNG_s = rand.nextDouble() * 5;
				RNG_a = rand.nextDouble() * Math.PI;
				Displacement velocity = new Displacement((float) (RNG_s * Math.cos(RNG_a)), (float) (RNG_s * Math.sin(RNG_a)));
				itr_shape.momentum.addVelocity(velocity);
				Physics.addActiveObject(itr_shape);
			}
		}

	}

	/**
	 * Will return the array of all objects in the game.
	 * 
	 * @return OBJECTS
	 */
	public static Shape[] getObjects()
	{
		return OBJECTS.toArray(new Shape[OBJECTS.size()]);
	}

	/**
	 * Returns the number of objects in the game.
	 * 
	 * @return OBJECTS size
	 */
	public static int getObjectCount()
	{
		return OBJECTS.size();
	}

}
