package Kotska.Object;

import java.awt.Color;

import Kotska.Kotska;
import Kotska.Physics;
import Kotska.SMath;
import Kotska.DataClasses.DataObjectBase;
import Kotska.DataClasses.Direction;
import Kotska.DataClasses.Displacement;
import Kotska.DataClasses.Momentum;
import Kotska.DataClasses.ObjectMan;
import Kotska.DataClasses.Pair;
import Kotska.DataClasses.PointArray;
import Kotska.DataClasses.VertexArray;

/**
 * Superclass of all shapes.
 */
public abstract class Shape implements DataObjectBase<Shape> {

	protected String ID;

	protected int x_size, y_size;
	protected float x_pos, y_pos;
	protected VertexArray vertices;
	protected PointArray points;
	protected Color color;
	protected double scale;
	protected double friction;

	public Momentum momentum;
	private Direction[] normals;
	protected int normals_to_make;

	/**
	 * Creates a new shape.
	 * @param x_pos : Top left x coordinate.
	 * @param y_pos : Top left y coordinate.
	 * @param x_size : Width of the shape.
	 * @param y_size : Height of the shape.
	 * @param mass : Mass of the shape.
	 */
	protected Shape(int x_pos, int y_pos, int x_size, int y_size, int mass)
	{
		momentum = new Momentum(mass);
		vertices = new VertexArray();
		points = new PointArray();

		this.x_pos = x_pos;
		this.y_pos = y_pos;
		this.x_size = x_size;
		this.y_size = y_size;

		color = Color.BLACK;
		scale = 1.0;

		addPoints();
		makeNormals();

		drawShape();
	}

	/**
	 * Adds points on the shape. Implemented per subclass.
	 */
	protected abstract void addPoints();

	/**
	 * Creates the normal vectors of the shape.
	 */
	protected void makeNormals()
	{
		//Make the array that contains the normals
		normals = new Direction[normals_to_make];

		//Declare x and y coordinate arrays to make derivation of normals more clear
		int s_X[] = getVertices().X();
		int s_Y[] = getVertices().Y();

		//Iterate through all the indices of the normal array, finding line segments between two vertices and normalizing them
		for (int j = 0; j < normals.length; j++)
			normals[j] = new Direction(
					-(s_Y[j] - s_Y[(j + 1 == getVertices().size()) ? 0 : j + 1]),
					s_X[(j + 1 == getVertices().size()) ? 0 : j + 1] - s_X[j]);
	}

	/**
	 * Draws the shape to the screen with a colour.
	 */
	public void drawShape()
	{
		Kotska.c.setColor(color);
		draw();
	}

	/**
	 * Draws the shape. Implemented per subclass.
	 */
	protected abstract void draw();

	/**
	 * Adds a new point used to define the shape.
	 * @param x_new : The x coordinate of a new point.
	 * @param y_new : The y coordinate of a new point.
	 */
	protected void addVertex(int x_new, int y_new)
	{
		try 
		{
			vertices.addVertex(x_new, y_new);
			points.addPoint(x_new, y_new);
		} 
		catch (ShapeTooComplexException e) 
		{ 
			e.printStackTrace(); 
		}
	}

	/**
	 * Returns the projection of the shape onto the given axis.
	 * @param axis : The axis of projection.
	 * @return The values representing the projection of the shape onto the given axis.
	 */
	public Pair<Float> proj(Direction axis)
	{
		float min = Float.MAX_VALUE;
		float max = -Float.MAX_VALUE;
		float dot;

		for (int i = 0; i < getPoints().size(); i++)
		{
			dot = SMath.dot(axis, points.pointFloat(i));
			
			if (dot < min)
				min = dot;
			if (dot > max)
				max = dot;
		}

		return new Pair<Float>(min, max);
	}

	/**
	 * Sets the drawing colour of the shape.
	 * @param new_color : The new drawing colour of the shape.
	 */
	public void setColor(Color new_color)
	{
		color = new_color;
	}

	/**
	 * Sets the scale of the shape.
	 * @param scale : The new scale of the shape.
	 */
	public void setScale(double scale)
	{
		this.scale = scale;
	}

	/**
	 * Updates this shape and its position based on the surroundings.
	 */
	public void updatePos()
	{
		/*
		 * A priori collision response.
		 */
		Displacement shift = getShift();
		
		x_pos += shift.A();
		y_pos += shift.B();
		points.shift(shift.A(), shift.B());
		
		Displacement mtv = Physics.checkCollision(this);

		x_pos -= shift.A();
		y_pos -= shift.B();
		points.shift(-shift.A(), -shift.B());
		
		if (mtv != null) 
			shift.add(mtv);
		
		//Move the shape with given shift.
		move(shift);
		
		//Momentum update.
		momentum.update();
		if (momentum.getVelocity().getMagnitude() > 0) 
			Physics.addActiveObject(this);
	}
	
	/**
	 * Updates the state of the shape.
	 * State includes all non-physics elements.
	 */
	public void updateState()
	{
		ObjectMan.addActiveObject(this);
	}

	public String getID()
	{
		return ID;
	}

	public void setID(String ID)
	{
		this.ID = ID;
	}

	/**
	 * Moves the shape using the given displacement.
	 * @param shift : Value of the shift along the x and y axes.
	 */
	private void move(Displacement shift)
	{
		x_pos += shift.A();
		y_pos += shift.B();		
		points.shift(shift.A(), shift.B());
		vertices.moveTo(points);
	}
	
	/**
	 * Gets the shift of the shape during the tick.
	 * @return Shift
	 */
	protected Displacement getShift()
	{
		return momentum.getVelocity();
	}

	/**
	 * Rotates the shape by a given angle using the rotation matrix.
	 * NOT IMPLEMENTED
	 * @param angle : Angle of rotation.
	 */
	public void rotate(double angle)
	{
		//Code for rotation if the shape is to rotate
		makeNormals();
	}

	/*
	 * GET METHODS
	 */

	/**
	 * Returns the array containing all vertices of the shape.
	 * @return The array of vertices.
	 */
	public VertexArray getVertices()
	{
		return vertices;
	}

	/**
	 * Returns the array containing all the points of the shape.
	 * @return Floating point array representation of the vertices of the shape.
	 */
	public PointArray getPoints()
	{
		return points;
	}

	public Pair<Float> getCenter()
	{
		return new Pair<Float>((float)X() + (float)sizeX() / 2, (float)Y() + (float)sizeY() / 2);
	}

	/**
	 * Returns the array containing the normals of all edges of the shape.
	 * @return Array of the normal planes.
	 */
	public Direction[] getNormals()
	{
		return normals;
	}

	/**
	 * Returns the number of normals of this shape.
	 * @return Number of normals.
	 */
	public int getNormalCount()
	{
		return normals_to_make;
	}

	/**
	 * Returns the angle of motion.
	 * @return Angle of motion.
	 */
	public double getAngle()
	{
		return momentum.getVelocity().getAngle();
	}

	/**
	 * Returns the friction of the surface of this shape.
	 * @return Friction of the shape.
	 */
	public double getFriction()
	{
		return friction;
	}

	/**
	 * Returns the mass of the shape.
	 * @return Mass of shape.
	 */
	public int getMass()
	{
		return momentum.mass;
	}

	/**
	 * Returns the current speed of the shape.
	 * @return Current speed.
	 */
	public double getSpeed()
	{
		return momentum.getVelocity().getMagnitude();
	}

	/**
	 * Returns the x coordinate of the top left corner of the shape.
	 * @return Top left corner x coordinate as an integer value.
	 */
	public int X()
	{
		return (int) x_pos;
	}

	/**
	 * Returns the y coordinate of the top left corner of the shape.
	 * @return Top left corner y coordinate as an integer value.
	 */
	public int Y()
	{
		return (int) y_pos;
	}
	
	/**
	 * Returns the x coordinate of the top left corner of the shape.
	 * @return Top left corner x coordinate as a float value.
	 */
	public float Xf()
	{
		return x_pos;
	}

	/**
	 * Returns the y coordinate of the top left corner of the shape.
	 * @return Top left corner y coordinate as a float value.
	 */
	public float Yf()
	{
		return y_pos;
	}

	/**
	 * Returns the height of the shape.
	 * @return Height of the shape.
	 */
	public int sizeX()
	{
		return x_size;
	}

	/**
	 * Returns the width of the shape.
	 * @return Width of the shape.
	 */
	public int sizeY()
	{
		return y_size;
	}

	/**
	 * Identifies the shape as elliptical or not. This means that it is not defined by vertices.
	 * @return Boolean value of whether or not the shape is round.
	 */
	public boolean isRound()
	{
		return false;
	}

	/**
	 * Returns a copy of the shape.
	 */
	public abstract Shape getCopy();

	/**
	 * Copies all the data from this shape into the given shape s.
	 * @param s : Shape to copy data into.
	 */
	protected void copyData(Shape s)
	{
		s.momentum = momentum.getCopy();
		s.points = points.getCopy();
		s.vertices = vertices.getCopy();
		s.setColor(color);
		s.setScale(scale);

		s.updatePos();
	}

}
