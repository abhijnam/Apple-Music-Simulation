public class SongNotFoundException extends RuntimeException
{
	public SongNotFoundException(String errorMessage)
	{
		super(errorMessage);
	}
}
