package model;

public class AmountLeftException extends IllegalArgumentException
{
	public AmountLeftException()
	{
		super();
	}
		
	public AmountLeftException(String message)
	{
		super(message);
	}
}
