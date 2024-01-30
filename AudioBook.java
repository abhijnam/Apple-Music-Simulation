import java.util.ArrayList;

/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
// Student Number: 501191089
// Name: Tejaswini Abhijna Medicharla
public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;

	
	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		super(title,year,id,type,audioFile,length);
		this.author = author;
		this.narrator = narrator;
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;

		// Make use of the constructor in the super class AudioContent. 
		// Initialize additional AudioBook instance variables. 
	}
	
	public String getType()
	{
		return TYPENAME;
	}

  // Print information about the audiobook. First print the basic information of the AudioContent 
	// by making use of the printInfo() method in superclass AudioContent and then print author and narrator
	// see the video
	public void printInfo()
	{
		super.printInfo();
		System.out.println("Author: "+this.author+" "+ "Narrator: "+" "+ this.narrator);
	}
	
  // Play the audiobook by setting the audioFile to the current chapter title (from chapterTitles array list) 
	// followed by the current chapter (from chapters array list)
	// Then make use of the the play() method of the superclass
	public void play()
	{
		// prints the audiobook info
		printInfo();
		// sets the audiofile to print out the chapter title
		super.setAudioFile(chapterTitles.get(currentChapter));
		// prints out the audiobook based on the audiofile we set it to
		super.play();
		// sets the audiofile to print out the chapter
		super.setAudioFile(chapters.get(currentChapter));
		super.play();
	}
	
	// Print the table of contents of the book - i.e. the list of chapter titles
	// See the video
	public void printTOC()
	{
		//for loop to interate through the chapter titles arraylist to print out each title with it's index's starting with 1
		for(int i = 0; i < chapterTitles.size();i++)
		{
			int index = i + 1;
			System.out.print(""+"Chapter"+" "+ index + ". ");
			System.out.println(chapterTitles.get(i));
			System.out.println();
		}
	}

	// Select a specific chapter to play - nothing to do here
	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter-1;
		}
	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{
		
		AudioBook a1 = (AudioBook)other;
		if(super.equals(a1) && this.author.equals(a1.author) && this.narrator.equals(a1.narrator))
		{
			return true;
		}
		return false;
	}
	
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}

}