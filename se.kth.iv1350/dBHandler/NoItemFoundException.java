package dBHandler;

public class NoItemFoundException extends IllegalArgumentException
{
	public NoItemFoundException()
	{
		super();
	}
		
	public NoItemFoundException(String fel)
	{
		super(fel);
	}
}