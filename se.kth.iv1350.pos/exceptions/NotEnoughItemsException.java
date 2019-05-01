package exceptions;

public class NotEnoughItemsException extends IllegalArgumentException {
	
	public NotEnoughItemsException()
	{
		super();
	}
		
	public NotEnoughItemsException(String message)
	{
		super(message);
	}
}
