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
	static final int spawn_interval = 100;
	int spawn_tracker = 0;

	List<Entity> entities = new ArrayList<Entity>();
	GameBoard GB;

	public ObjectMan(GameBoard GB)
	{
		this.GB = GB;
	}

	/**
	 * Handles collision between all entities
	 * 
	 * @param e
	 */
	public void update()
	{
		for (Entity e : entities)
		{
			for (Entity f : entities)
			{
				float spaceX = e.posX - f.posX;
				float spaceY = e.posY - f.posY;

				// AABB check and remove colliding elements
				if (f != e &&
						spaceX > 0.0 && spaceX < f.sizeY ||
						spaceX < 0.0 && spaceX > -e.sizeX ||
						spaceY > 0.0 && spaceY < f.sizeY ||
						spaceY < 0.0 && spaceY > -e.sizeY)
				{
					entities.remove(f);
				}
			}
			e.move();
			e.draw(GB.C);
		}
		
		// player update
		Player P = GB.P;
		P.move();
		P.draw(GB.C);
	}

	/**
	 * Handles map bounds collisions for all objects.
	 * 
	 * @param index
	 *            : Index along the entities list.
	 */
	public void mapBounds()
	{
		for (Entity e : entities)
		{
			if (e.posY + e.sizeY > GameBoard.SIZE_Y)
			{
				entities.remove(e);
			}
		}
	}

	/**
	 * Handles the spawning of meteors on the board.
	 */
	public void spawn()
	{
		if (spawn_tracker++ == 0)
		{
			int randomX = 0;
			Entity M = appendEntity(new Meteor(randomX, 0));
			M.setDirection(0, 5);
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
		return e;
	}

	/**
	 * Evaluates the bounds and movement of player to return new translation
	 * vector.
	 * 
	 * @param P
	 */
	public void playerBounds(Player P)
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
