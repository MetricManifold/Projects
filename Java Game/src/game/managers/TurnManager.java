package game.managers;

import java.util.ArrayList;
import game.groups.Fleet;
import game.players.Player;
import game.tiles.Planet;
<<<<<<< HEAD
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
=======
>>>>>>> dev

/**
 * Responsible for managing turns including updating planet production, fleets sent to other planets Also contains the graphical element
 * 
 * @author MR SMITH
 *
 */
public class TurnManager
{
	protected PlanetManager PG;
	protected PlayerManager PM;
	protected ConfigManager CM;
	protected ArrayList<Fleet> fleets;

<<<<<<< HEAD
	private HBox turnBar = new HBox();

	/*
	 * elements associated with ship sending and next turn
	 */
	private Label lblNextTurn = new Label();
	private Label lblSendShips = new Label();
	private TextField tfShipNum = new TextField();
	private Label lblPlayer = new Label();

	/*
	 * elements associated with the player label and planet list
	 */
	private Tooltip ttPlayer = new Tooltip();
	private BorderPane tblPlayer = new BorderPane();
	private boolean lblClick = false;
	private VBox v1 = new VBox();
	private VBox v2 = new VBox();
	private VBox v3 = new VBox();

	/*
	 * elements associated with the game time
	 */
	private HBox rightAlignBox = new HBox();
	private Text gameTimeText = new Text();
	private TextFlow gameTime = new TextFlow();
	private int totalSeconds = 0;
	private String timeFormat = "%02d : %02d";

	private List<Fleet> fleets = new ArrayList<Fleet>();

	public TurnManager()
=======
	public TurnManager(ConfigManager CM)
>>>>>>> dev
	{
		this.CM = CM;
		initialize();
	}

	/**
	 * set the events when interacting with the turnbar
	 * 
	 * @param pm
	 * @param pg
	 */
	public void setup(PlayerManager pm, PlanetManager pg)
	{
		PM = pm;
		PG = pg;
	}
	
	public void reset()
	{
		initialize();
	}
	
	protected void initialize()
	{
		fleets = new ArrayList<Fleet>();
	}

	/**
	 * activate the next turn
	 * 
	 */
	public void nextTurn()
	{
<<<<<<< HEAD
		pg.clearSelection(pm.getCurrentPlayer().getOrigin(), pm.getCurrentPlayer().getDestination());
		pm.nextPlayer();
		
=======
		PM.nextPlayer();

>>>>>>> dev
		// produce ships if all players have made their turn
		if (PM.getPlayer(1) == PM.getCurrentPlayer())
		{
			for (Planet p : PG.getPlanetArray())
			{
				// if owner is neutral, produce 75% total ships
				if (p.getOwner() == PM.neutral)
				{
					p.addShips(CM.defaultShip, (int) (p.getProduction().get(CM.defaultShip) * CM.neutralProdModifier));
				}
				else
				{
					p.produceShips(CM.defaultShip);
				}
			}

			for (Fleet f : fleets)
			{
				try
				{
					f.update(PG);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
<<<<<<< HEAD
			});

			fleets.removeIf(f -> f.getCount() == 0);
		}

		updatePlayerLabel(pm, pg);
		enableSend(false);

		lblClick = false;
		ttPlayer.hide();
		pm.getCurrentPlayer().update(pg, this, pm);
	}

	/**
	 * handle behaviour of textfield
	 * 
	 * @param pm
	 */
	public void shipNumFieldHandle(PlayerManager pm)
	{
		// check if the value does not exceed ships to send
		if (!tfShipNum.getText().isEmpty())
		{
			// create a new fleet to send
			int num = Integer.valueOf(tfShipNum.getText());
			ShipGroup f = new ShipInventory(ConfigurationManager.defaultShip, num);

			if (!pm.canSend(pm.getCurrentPlayer(), f))
			{
				tfShipNum.setText(String.valueOf(pm.getCurrentPlayer().getOrigin().getShipInventory().getCount()));
=======
>>>>>>> dev
			}

			fleets.removeIf(f -> f.getCount() == 0);
		}

<<<<<<< HEAD
		String time = String.format(timeFormat, minutes, seconds);
		gameTimeText.setText("");
=======
		PM.getCurrentPlayer().update(PG, this, PM);
>>>>>>> dev
	}

	/**
	 * return a copy of the set containing the fleets
	 * 
	 * @return
	 */
	public ArrayList<Fleet> getFleets()
	{
		return new ArrayList<>(fleets);
	}


	/**
	 * send the given number of ships
	 * 
	 * @param num
	 */
	public void sendShips(int num)
	{
		Player p = PM.getCurrentPlayer();
		Fleet f = new Fleet(CM.defaultShip, num, p);

		if (PM.canSend(p, f))
		{
			try
			{
				Planet o = p.getOrigin();
				Planet d = p.getDestination();

				o.getShipInventory().remove(CM.defaultShip, num);
				f.send(o, d);
				fleets.add(f);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
<<<<<<< HEAD
	
	public Pane getPane()
	{
		return turnBar;
	}

=======
>>>>>>> dev
}
