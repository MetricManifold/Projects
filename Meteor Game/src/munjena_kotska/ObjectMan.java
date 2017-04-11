package munjena_kotska;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

/**
 * Handles and delegates all objects on the game board.
 * 
 * @author Robert
 *
 */
public class ObjectMan
{
	int spawn_interval = 20;
	int spawn_tracker = 0;
	int numEntities = 0;

	List<Entity> entities = new ArrayList<Entity>();
	GameBoard GB;

	public ObjectMan(GameBoard GB)
	{
		this.GB = GB;
	}

	/**
	 * Returns the aabb collision check between two objects
	 * 
	 * @param a
	 *            first object
	 * @param b
	 *            second object
	 * @return
	 */
	private boolean aabbCheck(Entity a, Entity b)
	{
		float spaceX = a.posX - b.posX;
		float spaceY = a.posY - b.posY;

		return (a != b &&
				(spaceX > 0.0 && spaceX < b.sizeX || spaceX < 0.0 && spaceX > -a.sizeX) &&
				(spaceY > 0.0 && spaceY < b.sizeY || spaceY < 0.0 && spaceY > -a.sizeY));
	}

	/**
	 * Handles collision between all entities
	 * 
	 * @param e
	 */
	public void update()
	{
		List<Integer> toRemove = new ArrayList<Integer>();
		Player P = GB.P;

		for (int i = 0; i < numEntities; i++)
		{
			Entity e = entities.get(i);

			if (e.posY + e.sizeY > GameBoard.SIZE_Y || aabbCheck(P, e))
			{
				toRemove.add(i);
			}
			else
			{
				for (int j = 0; j < numEntities; j++)
				{
					Entity f = entities.get(i);
					if (aabbCheck(e, f))
					{
						toRemove.add(i);
					}
				}

				e.move();
				e.draw(GB.C);
			}
		}

		for (int i : toRemove)
		{
			removeEntity(i);
		}

		// player update
		P.move();
		P.draw(GB.C);
	}

	/**
	 * Handles the spawning of meteors on the board.
	 */
	public void spawn()
	{
		if (spawn_tracker++ == 0)
		{
			int spawnX = ThreadLocalRandom.current().nextInt(GameBoard.P_BOUNDS_X0, GameBoard.SIZE_X);
			float moveY = (float) ThreadLocalRandom.current().nextDouble(6L, 8L);

			Entity M = appendEntity(new Meteor(spawnX, 0));
			M.setDirection(0, moveY);

			if (spawn_interval > 3) spawn_interval--;
		}
		spawn_tracker %= spawn_interval;
	}

	/**
	 * Adds an Entity to the entity list and returns it
	 * 
	 * @param e
	 */
	public Entity appendEntity(Entity e)
	{
		entities.add(e);
		numEntities++;
		return e;
	}

	public void removeEntity(int index)
	{
		entities.remove(index);
		numEntities--;
	}

}
