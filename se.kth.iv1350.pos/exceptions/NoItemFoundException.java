package exceptions;

public class NoItemFoundException extends IllegalArgumentException
{
	public NoItemFoundException()
	{
		super();
	}
		
	public NoItemFoundException(String message)
	{
		super(message);
	}
}