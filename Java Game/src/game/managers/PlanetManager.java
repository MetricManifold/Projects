package game.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import game.managers.PlayerManager.Controller;
import game.players.Player;
import game.tiles.Planet;
import game.tiles.Space;
<<<<<<< HEAD
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class PlanetManager
{
	// static values for ui
	public static final int PADH = 5, PADV = 5,
		TILEH = 25, TILEV = 25,
		NUM_P_BG = 4, NUM_B_BG = 4,
		MARGIN = 5, TOPMARGIN = 2;

=======

public class PlanetManager
{
>>>>>>> dev
	// planets and corresponding buttons
	protected Map<Integer, Planet> planets;
	protected Map<Integer, Space> spaces;

	protected int x, y, len;
	protected double density;

<<<<<<< HEAD
	private StackPane pane = new StackPane();
	private TilePane tilePane = new TilePane(Orientation.HORIZONTAL);
=======
	protected PlayerManager PM;
	protected TurnManager TM;
	protected ConfigManager CM;
>>>>>>> dev

	public PlanetManager(ConfigManager CM)
	{
<<<<<<< HEAD
		// initialize local variables
		this.x = ConfigurationManager.gridX;
		this.y = ConfigurationManager.gridY;
		this.density = ConfigurationManager.planetDensity;

		this.maxh = (TILEH + PADH) * x + MARGIN * 2 - PADH;
		this.maxv = (TILEV + PADV) * y + MARGIN * 2 - PADV;
		this.len = x * y;

		makeTilePaneUI(); // 		setup tilepane UI
		spawnPlanets(); // 			create the planets on this grid

		System.out.println("finished grid");
=======
		this.CM = CM;
		initialize();
>>>>>>> dev
	}

	/**
	 * Sets the mouse event associated with the tilepane to find all the grid locations
	 */
	public void setup(PlayerManager pm, TurnManager tm)
	{
<<<<<<< HEAD
		VBox.setMargin(tilePane, new Insets(TOPMARGIN, 0, 0, 0));
		pane.getChildren().add(tilePane);
=======
		TM = tm;
		PM = pm;
>>>>>>> dev

		spawnPlanets();
		setStartPlanets();
	}

	/**
	 * resets the player manager, initializes then resets the turn manager
	 */
	public void reset()
	{
		initialize();
		PM.reset();
		CM.clearNames();

		spawnPlanets();
		setStartPlanets();
	}

	/**
	 * initialization activity for this manager to set variables and create planets
	 */
	protected void initialize()
	{
<<<<<<< HEAD
		setStartPlanets(pm);

		tilePane.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				double sx = (event.getX() - MARGIN) / (TILEH + PADH); // 	normalized selected x
				double sy = (event.getY() - MARGIN) / (TILEV + PADV); // 	normalized selected y
				int ix = (int) sx; //										integer distance x
				int iy = (int) sy; //										integer distance y
				double dx = sx - ix; //										fractional distance x
				double dy = sy - iy; // 									fractional distance y

				// check if selection is before padding
				if (dx < (TILEH + PADH) / PADH && dy < (TILEV + PADV) / PADV)
				{
					Planet p = planets.get(hashLocation(ix, iy));
					handleClickPlanet(p, pm, tm);
				}
			}
		});
=======
		this.x = CM.gridX;
		this.y = CM.gridY;
		this.density = CM.planetDensity;
		this.len = x * y;
>>>>>>> dev
	}

	/**
	 * puts planets in the grid and associates them with buttons
	 */
	public void spawnPlanets()
	{
<<<<<<< HEAD
		for (int i = 0; i < len; i++)
		{
			// create the necessary elements
			Label l = new Label();
			Space s = new Space(i % x, i / x);
=======
		planets = new HashMap<Integer, Planet>();
		spaces = new HashMap<Integer, Space>();
>>>>>>> dev

		do
		{
			for (int i = 0; i < len; i++)
			{
				// create the necessary elements
				Space s = new Space(i % x, i / x);

<<<<<<< HEAD
				// set styles
				String bgSelect = String.format("planet-button%d", ThreadLocalRandom.current().nextInt(NUM_P_BG) + 1);
				l.getStyleClass().addAll("space-button", bgSelect);

				// add the planet to the grid and planets list
				tiles.put(p, l);
				planets.put(hashLocation(i % x, i / x), p);

				setPlanetTooltip(p);
			}
			else
			{
				l.getStyleClass().add("space-button");
				tiles.put(s, l);
=======
				// choose whether to create a planet or empty space
				double prob = ThreadLocalRandom.current().nextDouble();
				if (prob < density)
				{
					Planet p = new Planet(s, CM);
					planets.put(hashLocation(i % x, i / x), p);
				}
				else
				{
					spaces.put(hashLocation(i % x, i / x), s);
				}
>>>>>>> dev
			}
		} while (planets.size() < PM.numPlayers);
	}

	/**
	 * give players one starting planet
	 */
	public void setStartPlanets()
	{
		// make a list of planets to choose from
		Planet[] planetArray = getPlanetArray();

		// make a list denoting each of the planets
		List<Integer> nums = new ArrayList<>();
		IntStream.range(0, planets.size()).forEach(i -> nums.add(i));

		for (int i = 0; i < CM.numHumanPlayers; i++)
		{
			// pick a random value from the planet array
			int r = ThreadLocalRandom.current().nextInt(nums.size());
			int pick = nums.remove(r);
			Planet p = planetArray[pick];

<<<<<<< HEAD
			setPlanetOwner(pm.getPlayer(numPlayers), p);
			p.setProduction(10);
			p.produceShips();
		}

		nums.forEach(n -> setPlanetOwner(pm.neutral, planetArray[n]));
	}

	/**
	 * handles the ui change when a planet is clicked
	 * 
	 * @param p
	 * @param pm
	 * @param tm
	 */
	public void handleClickPlanet(Planet p, PlayerManager pm, TurnManager tm)
	{
		Planet o = pm.getCurrentPlayer().getOrigin();
		Planet d = pm.getCurrentPlayer().getDestination();
		pm.getCurrentPlayer().clickTile(p);

		if (p != null)
		{
			if (o == null)
			{
				if (p.getOwner() == pm.getCurrentPlayer())
				{
					tiles.get(p).getStyleClass().add("space-button-origin");
				}
			}
			else if (d == null)
			{
				if (p != o)
				{
					tiles.get(p).getStyleClass().add("space-button-destination");
					tm.enableSend(true);
				}
			}
			else
			{
				clearSelection(o, d);
				tm.enableSend(false);
			}
		}
		else
		{
			clearSelection(o, d);
			tm.enableSend(false);
=======
			setPlanetOwner(PM.getPlayersOfType(Controller.HUMAN)[i], p);
			p.setProduction(CM.defaultShip, CM.initialProduction);
			p.addShips(CM.defaultShip, CM.shipStartCount);
>>>>>>> dev
		}

		nums.forEach(n -> setPlanetOwner(PM.neutral, planetArray[n]));
		if (!CM.neutralShipsVisible) nums.forEach(n -> planetArray[n].setDisplayShips(false));
	}

	/**
	 * transfer ownership of a planet to a given player
	 * 
	 * @param player
	 * @param p
	 */
	public void setPlanetOwner(Player player, Planet p)
	{
<<<<<<< HEAD
		// if owner is not null 
		if (p.getOwner() != null)
		{
			tiles.get(p).getStyleClass().remove(p.getOwner().getColor());
		}

		player.addPlanet(p);
		tiles.get(p).getStyleClass().add(player.getColor());
	}

	/**
	 * add the tooltip for the given planet
	 * 
	 * @param p
	 */
	public void setPlanetTooltip(Planet p)
	{
		Label l = tiles.get(p);
		
		l.setOnMouseEntered(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent event)
			{
				Point2D pnt = l.localToScreen(l.getLayoutBounds().getMaxX(), l.getLayoutBounds().getMaxY());
				p.getTooltip().show(l, pnt.getX() + 10, pnt.getY());
			}
		});
		l.setOnMouseExited(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				p.getTooltip().hide();
			}
		});
=======
		p.setOwner(player);
>>>>>>> dev
	}

	/**
	 * creates a unique integer associated with the provided 2d location
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	protected int hashLocation(int x, int y)
	{
		return x + this.x * y;
	}

	/**
	 * get the array of planets
	 * 
	 * @return
	 */
	public Planet[] getPlanetArray()
	{
		return planets.values().toArray(new Planet[planets.size()]);
	}

<<<<<<< HEAD
	/**
	 * return the width in tiles of the grid
	 * 
	 * @return
	 */
	public int getSizeX()
	{
		return maxh;
	}

	/**
	 * return the height in tiles of the grid.
	 * 
	 * @return
	 */
	public int getSizeY()
	{
		return maxv;
	}

	public Map<Space, Label> getTiles()
	{
		return tiles;
	}
	
	public Pane getPane()
	{
		return pane;
	}

=======
>>>>>>> dev
}
