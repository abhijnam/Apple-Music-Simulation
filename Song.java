/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
//Student Number: 501191089
// name: Tejaswini Abhijna Medicharla
public class Song extends AudioContent implements Comparable<Song> // implement the Comparable interface
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			String composer, Song.Genre genre, String lyrics)
	{
		super(title,year,id,type,audioFile,length);
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional Song instance variables. 
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print artist, composer, genre 
	public void printInfo()
	{
		super.printInfo();
		System.out.println("Artist: "+artist+" "+ "Composer: "+composer+" "+ "Genre: "+genre);
	}
	
	// Play the song by setting the audioFile to the lyrics string and then calling the play() method of the superclass
	public void play()
	{
		// first prints the details of the object that we are trying to play
		// ex: the title, composer, author etc.. depending on what type of audio content 
		printInfo();
		super.setAudioFile(lyrics); // invoking the setAudioFile method using the super class, sets the file to lyrics to be played
		super.play();// helps to display the lyrics or the file we are setting it to
	}
	
	public String getComposer()
	{
		return composer;
	}
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}
		
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	// Make use of the superclass equals() method
	public boolean equals(Object other)
	{
		Song other1 = (Song) other; // downcasting the Object class other to song to compare it with the song object that was created
		//if statements checks to see if the titles, artists, composer and genre is equal for both songs
		if(super.equals(other) && this.artist.equals(other1.artist) && this.composer.equals(other1.composer) && this.genre.equals(other1.genre)) 
		{
			return true; // returns true if the two objects are equal
		}
		return false;// else returns false
	}
	
	// Implement the Comparable interface 
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	@Override
	public int compareTo(Song other)
	{
		return this.getTitle().compareTo(other.getTitle());// compares the title which is a string to another song object, compares them according to alphabetical precedence 
	} // A negative number is returned if this.getTitle() comes before other.getTitle(), if they're the same, 0 is returned, a postive number otherwise

}
