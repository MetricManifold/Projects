package Kotska.DataClasses;

public interface DataObjectBase<T> {
	
	/**
	 * Returns a complete copy of the class.
	 * @return
	 */
	public T getCopy();
	
	/**
	 * Returns an ID representing this class.
	 * @return ID
	 */
	public String getID();
	
	/**
	 * Sets the ID representing this class.
	 * @param ID : New ID to be set.
	 */
	public void setID(String ID);
	
}
