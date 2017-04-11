package Object;

/**
 * This class is used to throw an exception if a shape has too many vertices.
 * 
 */
public class ShapeTooComplexException extends Exception
{
	private static final long serialVersionUID = 3231166921519753452L;

	public ShapeTooComplexException()
	{

	}

	public ShapeTooComplexException(String message)
	{
		super(message);
	}

	public ShapeTooComplexException(Throwable cause)
	{
		super(cause);
	}

	public ShapeTooComplexException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
