package Kotska.DataClasses;

import Kotska.Physics;
import Kotska.SMath;

/**
 * Class representing the energy property used for objects.
 * 
 */
public class Momentum implements DataObjectBase<Momentum> {
	
	private String ID;
	
	private double speed, acceleration, angle_vel, angle_acc;
	public int mass;
	
	/**
	 * Creates an energy class, a property of objects
	 * @param mass : The mass of the object this momentum is part of.
	 */
	public Momentum(int mass)
	{
		this.mass = mass;
		
		acceleration = 0;
		speed = 0;
		angle_vel = 0;
		angle_acc = 0;
		
	}
	
	/**
	 * Sets velocity of the system.
	 * @param velocity2 : New velocity.
	 */
	public void setVelocity(Displacement velocity2)
	{
		this.speed = velocity2.getMagnitude();
		this.angle_vel = velocity2.getAngle();
	}
	
	/**
	 * Sets the acceleration that this momentum undergoes per tick.
	 * @param acceleration2 : The acceleration to be added.
	 */
	public void setAcceleration(Displacement acceleration2)
	{
		acceleration = acceleration2.getMagnitude();
		angle_acc = acceleration2.getAngle();
	}
	
	/**
	 * Combines current velocity with given velocity.
	 * @param velocity2 : The velocity to be added.
	 */
	public void addVelocity(Displacement velocity2)
	{
		double speed_x = speed * Math.cos(angle_vel) + velocity2.A();
		double speed_y = speed * Math.sin(angle_vel) + velocity2.B();
		
		speed = Math.sqrt(Math.pow(speed_x, 2) + Math.pow(speed_y, 2));
		angle_vel = SMath.getAngle(speed_x, speed_y);
	}
	
	/**
	 * Combines current acceleration with given acceleration.
	 * @param acceleration2 : The acceleration to be added.
	 */
	public void addAcceleration(Displacement acceleration2)
	{
		double acc_x = speed * Math.cos(angle_acc) + acceleration2.A();
		double acc_y = speed * Math.sin(angle_acc) + acceleration2.B();
		
		speed = Math.sqrt(Math.pow(acc_x, 2) + Math.pow(acc_y, 2));
		angle_vel = SMath.getAngle(acc_x, acc_y);
	}
	
	/**
	 * Returns the velocity of the momentum.
	 * @return : speed
	 */
	public Displacement getVelocity()
	{
		return new Displacement((float) (speed * Math.cos(angle_vel)), (float) (speed * Math.sin(angle_vel)));
	}
	
	/**
	 * Returns the acceleration of the momentum.
	 * @return : acceleration
	 */
	public Displacement getAcceleration()
	{
		return new Displacement((float) (acceleration * Math.cos(angle_acc)), (float) (acceleration * Math.sin(angle_acc)));
	}
	
	/**
	 * Will update the speed based on the acceleration.
	 */
	public void update()
	{
		speed *= 1.0 - Physics.DRAG;
		addVelocity(getAcceleration());
		
	}
	
	/**
	 * Will decrease the energy as the object moves, using friction.
	 * @param friction : Value of friction from 0 to 1.
	 */
	public void dissipate(float friction)
	{
		speed *= 1.0 - Physics.DRAG - friction;
	}
	
	public Momentum getCopy()
	{
		Momentum m = new Momentum(mass);
		m.setVelocity(getVelocity());
		return m;
	}
	
	public String getID()
	{
		return ID;
	}
	
	public void setID(String ID)
	{
		this.ID = ID;
	}
}
