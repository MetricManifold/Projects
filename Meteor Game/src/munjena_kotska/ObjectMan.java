package munjena_kotska;

import java.util.List;
import java.util.ArrayList;

/**
 * Handles and delegates all objects on the game board.
 * 
 * @author Robert
 *
 */
public class ObjectMan
{

	static List<Entity> entities = new ArrayList<Entity>();

	static int spawn_interval = 100;
	static int spawn_tracker = 0;

	/**
	 * Handles collision between all entities on the game board.
	 * 
	 * @param index
	 *            : Index along the entities list.
	 */
	public static void update(int index)
	{
		if (index == entities.size())
			return;

		entities.get(index).move();
		entities.get(index).draw();

		Entity remove = null;

		for (Entity e : entities)
		{
			if (e == entities.get(index))
				continue;

			// Collision check.
			if (Math.abs(e.posX - entities.get(index).posX) < (e.sizeX + entities.get(index).sizeX) / 2
					&& Math.abs(e.posY - entities.get(index).posY) < (e.sizeY + entities.get(index).sizeY) / 2)
				remove = e;

		}

		if (remove != null)
		{
			entities.remove(index);
			entities.remove(remove);
		}

		update(index + 1);

	}

	/**
	 * Handles map bounds collisions for meteors only.
	 * 
	 * @param index
	 *            : Index along the entities list.
	 */
	public static void mapBounds(int index)
	{
		if (index == entities.size())
			return;

		// Map bounds check.
		if (entities.get(index).posY + entities.get(index).sizeY > GameBoard.SIZE_Y && !(entities.get(index) instanceof Player))
		{
			entities.remove(index);
		}

		mapBounds(index + 1);
	}

	/**
	 * Handles the spawning of meteors on the board.
	 */
	public static void spawn()
	{
		spawn_tracker = (spawn_tracker + 1) % spawn_interval;

		if (spawn_tracker != 0)
			return;

		int randomX = 0;
		Meteor M = new Meteor(randomX, 0);
		M.setDirection(0, 5);

	}

	/**
	 * Adds an Entity to the entity list.
	 * 
	 * @param e
	 */
	public static void addEntity(Entity e)
	{
		entities.add(e);
	}

	public static void playerBounds(Player P)
	{
		float moveX = P.moveX, moveY = P.moveY;

		if (P.posX <= GameBoard.P_BOUNDS_X0 && moveX < 0)
		{
			P.posX = GameBoard.P_BOUNDS_X0;
			moveX = 0;
		}
		if (P.posX + P.sizeX >= GameBoard.SIZE_X && moveX > 0)
		{
			P.posX = GameBoard.SIZE_X - P.sizeY;
			moveX = 0;
		}
		if (P.posY <= GameBoard.P_BOUNDS_Y0 && moveY < 0)
		{
			P.posY = GameBoard.P_BOUNDS_Y0;
			moveY = 0;
		}
		if (P.posY + P.sizeY >= GameBoard.SIZE_Y && moveY > 0)
		{
			P.posY = GameBoard.SIZE_Y - P.sizeY;
			moveY = 0;
		}

		P.setDirection(moveX, moveY);
	}

}
