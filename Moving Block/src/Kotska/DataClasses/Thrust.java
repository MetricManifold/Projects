package Kotska.DataClasses;

import Kotska.Object.Shape;

public class Thrust {
	
	double angle;
	float acceleration, speed, max_speed;
	boolean active;
	
	Shape host;
	
	/**
	 * Initializes thrust object for a shape.
	 * @param host : Shape to which thrust applies.
	 * @param acceleration : Acceleration with which thrust acts.
	 * @param max_speed : Maximum speed the thrust can apply.
	 */
	public Thrust(Shape host, float acceleration, float max_speed)
	{
		this.host = host;
		this.max_speed = max_speed;
		this.acceleration = acceleration;
		
		this.speed = 0;
	}
	
	/**
	 * Applies the thrust at the given angle.
	 * @param angle : New angle.
	 */
	public void setAngle(double angle)
	{
		this.angle = angle;
	}
	
	/**
	 * Sets the acceleration of the thrust.
	 * @param acceleration : New acceleration.
	 */
	public void setAccelerationFlat(float acceleration)
	{
		this.acceleration = acceleration;
	}
	
	/**
	 * Sets the max speed of the thrust.
	 * @param max_speed : New max speed.
	 */
	public void setMaxSpeed(float max_speed)
	{
		this.max_speed = max_speed;
	}
	
	/**
	 * Will update the thrust depending on its active state.
	 */
	public void update()
	{
		if (active)
		{
			//Increases speed to accelerate.
			if (speed < max_speed) speed += acceleration;
			
			if (speed > max_speed) speed = max_speed;
		}
		else
		{
			//Negates speed to slow down.
			if (speed > 0) speed = -speed;
			if (speed < 0) speed += acceleration;
			
			if (speed > 0) speed = 0;
		}
		
		//Sets the speed for thrust.
		host.momentum.addVelocity(new Displacement(speed * Math.cos(angle), speed * Math.sin(angle)));
	}
	
	/**
	 * Will toggle the activity of this thrust to the given parameter.
	 * @param active : Whether thrust is on or off.
	 */
	public void toggle(boolean active)
	{
		this.active = active;
	}
	
	
}
