package Kotska;

import java.util.LinkedList;
import java.util.Queue;

import Kotska.DataClasses.Direction;
import Kotska.DataClasses.Displacement;
import Kotska.DataClasses.Momentum;
import Kotska.DataClasses.ObjectMan;
import Kotska.DataClasses.Pair;
import Kotska.Object.Shape;

public class Physics {
	public final static double g = 1.80665;
	public final static double g_angle = -Math.PI / 2;
	public final static int MAX_SPEED = 25;
	public final static double DRAG = 0.00;
	public final static float mtv_cushion = 0.5f;

	private static Queue<Shape> ACTIVE_OBJECTS = new LinkedList<Shape>();
	private static Shape checked_objects[];
	private static int checked_object_count;

	/**
	 * Will perform an update operation on all physics elements in the game.
	 */
	public static void update()
	{
		/*
		 * Perform position update on the shapes.
		 */
		checked_objects = new Shape[ACTIVE_OBJECTS.size() + 1];
		checked_object_count = 0;
		
		Queue<Shape> temp = new LinkedList<Shape>();

		while (!ACTIVE_OBJECTS.isEmpty())
		{
			temp.add(ACTIVE_OBJECTS.remove());
		}

		while (!temp.isEmpty())
		{
			temp.remove().updatePos();
		}
	}

	/**
	 * Adds an object to the array of objects which require position updates.
	 * @param shape : The object to be added.
	 */
	public static void addActiveObject(Shape shape)
	{
		if (!(shape instanceof Player))
			ACTIVE_OBJECTS.add(shape);
	}

	/**
	 * Main check for collision of the given shape with something in the environment.
	 * @param s : The shape that is being checked against its environment for collision.
	 * @return collision status
	 */
	public static Displacement checkCollision(Shape s)
	{
		checked_objects[checked_object_count++] = s;

		Displacement mtv_map, mtv;
		mtv_map = checkCollisionMapBounds(s);

		Shape OBJECTS[] = ObjectMan.getObjects();

		for (Shape cur_obj : OBJECTS)
		{
			for (Shape o : checked_objects)
				if (cur_obj == o)
					continue;
			if (cur_obj == s) continue;

			if (!s.isRound() && !cur_obj.isRound())
				mtv = checkCollisionPoly(s, cur_obj);
			else if (s.isRound() ^ cur_obj.isRound())
				mtv = checkCollisionPolyRound(s, cur_obj);
			else
				mtv = checkCollisionRound(s, cur_obj);

			if (mtv == null) continue;

			/*
			 * Will resolve the momentum in case of a collision.
			 */
			Direction impact_normal = mtv.getDirection();
			resolveMomentum(s.momentum, cur_obj.momentum, impact_normal);
			
			if (mtv_map != null) mtv.add(mtv_map);

			mtv.add(new Displacement(mtv.A() / Math.abs(mtv.A()) * mtv_cushion, mtv.B() / Math.abs(mtv.B()) * mtv_cushion));
			return mtv;
		}
		
		return mtv_map;
	}

	/**
	 * Check for collision between two shapes defined by vertices.
	 * @param s1 : The first shape that is checked for collision
	 * @param s2 : The second shape that is checked for collision
	 * @return collision status
	 */
	public static Displacement checkCollisionPoly(Shape s1, Shape s2)
	{
		//Variables used for the projections
		Direction proj_planes[] = SUtils.array_concat(s1.getNormals(), s2.getNormals());
		Pair<Float> p1 = null, p2 = null;

		//Variables used for the minimum translation vector
		int mtd = Integer.MAX_VALUE, mtd1, mtd2;
		Direction normal = null;
		Displacement connector = new Displacement(s1.getCenter().A() - s2.getCenter().A(), s1.getCenter().B() - s2.getCenter().B());

		/*
		 * Conduct the Separating Axis Theorem collision checker
		 */
		for (Direction proj_plane : proj_planes)
		{
			p1 = s1.proj(proj_plane);
			p2 = s2.proj(proj_plane);

			if (p1.B() < p2.A() || p2.B() < p1.A())	
				return null;

			/*
			 * Find the minimum translation vector
			 */
			mtd1 = (int) (p1.B() - p2.A());
			mtd2 = (int) (p2.B() - p1.A());

			if (mtd1 < mtd || mtd2 < mtd)
				normal = proj_plane;

			if (mtd1 < mtd)
				mtd = mtd1;
			if (mtd2 < mtd)
				mtd = mtd2;
		}

		Displacement mtv = normal.getCopy();
		mtv.reverse();
		mtv.scale(mtd + 1);

		//Reverse mtv if it points in wrong direction.
		if (SMath.dot(mtv, connector) < 0) 
			mtv.reverse();

		return mtv;
	}

	/**
	 * Checks for collision between two round shapes
	 * @param s1 : The first shape that is checked for collision
	 * @param s2 : The second shape that is checked for collision
	 * @return collision status
	 */
	public static Displacement checkCollisionRound(Shape s1, Shape s2)
	{
		float dis_x, dis_y;													//Distance between shapes measured along x and y axis
		double angle, r_s, r_e;												//The angle, radius of s, and radius of OBJECTS[i]

		dis_x = (s1.X() + s1.sizeX() / 2) - (s2.X() + s2.sizeX() / 2);
		dis_y = (s1.Y() + s1.sizeY() / 2) - (s2.Y() + s2.sizeY() / 2);

		angle = SMath.getAngle(dis_x, dis_y);

		r_s = Math.sqrt(s1.sizeX() ^ 2) * Math.pow(Math.cos(angle), 2) + (s1.sizeY() ^ 2) * Math.pow(Math.sin(angle), 2);
		r_e = Math.sqrt(s2.sizeX() ^ 2) * Math.pow(Math.cos(angle), 2) + (s2.sizeY() ^ 2) * Math.pow(Math.sin(angle), 2);

		if (Math.sqrt(Math.pow(dis_x, 2) + Math.pow(dis_y, 2)) > r_s + r_e)		//Is the distance between their centres less than the sum of the radii?
			return null;

		return null;
	}

	/**
	 * Checks the collision between a polygon and a round shape.
	 * @param s1
	 * @param s2
	 * @return Maximum translation vector
	 */
	public static Displacement checkCollisionPolyRound(Shape s1, Shape s2)
	{
		return null;
	}

	/**
	 * Checks to see whether the shape has moved out of bounds.
	 * @param s : The shape which is being checked for crossing the boundary.
	 * @return Out of bounds status.
	 */	
	public static Displacement checkCollisionMapBounds(Shape s)
	{
		float x = 0, y = 0;

		if (s.Xf() < 0)
			x = -s.Xf();
		else if (s.Xf() + s.sizeX() > Kotska.MAX_WIDTH + Kotska.DIM_BUFF)
			x = Kotska.MAX_WIDTH + Kotska.DIM_BUFF - s.sizeX() - s.Xf();
		
		if (s.Yf() < 0)
			y = -s.Yf();
		else if (s.Yf() + s.sizeY() > Kotska.MAX_HEIGHT + Kotska.DIM_BUFF)
			y = Kotska.MAX_HEIGHT + Kotska.DIM_BUFF - s.sizeY() - s.Yf();

		if (x != 0 || y != 0)
		{
			Displacement mtv = new Displacement(x, y);
			bounceShape(s, mtv.getDirection());
			return mtv;
		}
		else
			return null;
	}

	/**
	 * Bounces the shape off a plane with the given normal.
	 * @param s : Shape being deflected by the direction.
	 * @param normal : The normal to the plane of deflection.
	 */
	public static void bounceShape(Shape s, Direction normal)
	{
		Displacement velocity = s.momentum.getVelocity();
		
		float v_x = velocity.A() - 2 * SMath.dot(velocity, normal) * normal.A();
		float v_y = velocity.B() - 2 * SMath.dot(velocity, normal) * normal.B();

		s.momentum.setVelocity(new Displacement(v_x, v_y));
	}

	/**
	 * Will simulate the interaction of two momenta.
	 * @param m1 : The first interacting momentum.
	 * @param m2 : The second interacting momentum.
	 * @param normal : The normal of the plane of impact between the momenta.
	 */
	public static void resolveMomentum(Momentum m1, Momentum m2, Direction normal)
	{
		Displacement v1 = m1.getVelocity(), v2 = m2.getVelocity();
		
		float s_x1 = 
				((float)(m1.mass - m2.mass) / (m1.mass + m2.mass)) * v1.A() + ((float)(2 * m2.mass) / (m1.mass + m2.mass)) * v2.A();
		float s_x2 = 
				((float)(2 * m1.mass) / (m1.mass + m2.mass)) * v1.A() + ((float)(m2.mass - m1.mass) / (m1.mass + m2.mass)) * v2.A();

		float s_y1 = 
				((float)(m1.mass - m2.mass) / (m1.mass + m2.mass)) * v1.B() + ((float)(2 * m2.mass) / (m1.mass + m2.mass)) * v2.B();
		float s_y2 = 
				((float)(2 * m1.mass) / (m1.mass + m2.mass)) * v1.B() + ((float)(m2.mass - m1.mass) / (m1.mass + m2.mass)) * v2.B();

		Displacement v1_new = new Displacement(s_x1, s_y1);
		Displacement v2_new = new Displacement(s_x2, s_y2);
				
		m1.setVelocity(v1_new);
		m2.setVelocity(v2_new);
	}
}
