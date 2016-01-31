package Kotska;

import Kotska.DataClasses.Displacement;
import Kotska.DataClasses.Pair;

public class SMath {

	/**
	 * Returns an angle in the direction of the given x, y.
	 * The sign of the angle reflects the inverted coordinate system.
	 * @param x : Displacement in the x direction.
	 * @param y : Displacement in the y direction.
	 * @return Angle
	 */
	public static double getAngle(double x, double y)
	{
		if (x == 0 && y == 0) return 0;
		
		double acute_angle;
		double angle;

		acute_angle = Math.abs(Math.atan(y / x));

		if (y > 0)
			if (x > 0)	angle = acute_angle;
			else		angle = Math.PI - acute_angle;
		else 
			if (x > 0)	angle = -acute_angle;
			else		angle = acute_angle - Math.PI;

		return angle;
	}
	
	/**
	 * Returns the dot product evaluation of two Pair types.
	 * @param x : First Pair.
	 * @param y : Second Pair.
	 * @return Dot product
	 */
	public static float dot(Pair<Float> x, Pair<Float> y)
	{
		return x.A() * y.A() + x.B() * y.B();		
	}
	
	/**
	 * Returns the scalar projection between two displacements.
	 * @param x : First displacement.
	 * @param y : Second displacement.
	 * @return Scalar projection
	 */
	public static float proj_scal(Displacement x, Displacement y)
	{
		return dot(x, y) / y.getMagnitude();
	}

	/**
	 * Returns the vector projection of x onto y.
	 * @param x : First displacement.
	 * @param y : Second displacement.
	 * @return Vector projection
	 */
	public static Displacement proj_vec(Displacement x, Displacement y)
	{
		float scalar = proj_scal(x, y) / y.getMagnitude();
		Displacement vector = y.getCopy();
		vector.scale(scalar);
		
		return vector;
	}
}
