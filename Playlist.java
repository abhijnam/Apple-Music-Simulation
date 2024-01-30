import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
// Student Number: 501191089
// name: Tejaswini Abhijna Medicharla
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */

	public void printContents()
	{
		// for loop is used to go through each and every element in the arraylist contents and printing the information of that element
		// in this case the element is an object of class AudioContent 
		for(int i = 0; i < contents.size();i++)
		{
			System.out.print((i+1)+"."+" ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		// interates through arraylist of AudioContent objects and uses the play method that is in the class of each object to do that specific play command
		for(int i =0;i < contents.size();i++)
		{
			contents.get(i).play();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		// plays the specific object at the index given by the user
		getTitle();
		if( index > 0 && index-1 <= contents.size()-1)
		{
			// gets the object from the contents arraylist and plays it
			contents.get(index-1).play();
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		// downcasting the object other to playlist to compare if the playlist created by the user and the playlist passed as the parameter are equal
		Playlist other2 = (Playlist) other;
		if(this.title.equals(other2.title))// checks to see if the title of the object we are referring to and the object that is being passed in as the parameter are equal
		{
			return true;
		}
		return false;
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index) // the index starts from 1, since the contents start as 1.song, 2.audiobook etc..
	{
		if(index -1 < contents.size()) // checks to see if the index that is being passed in as the parameter is a valid index in the contents arraylist
		{
			if(contents.contains(contents.get(index-1)))
			{
				contents.remove(index-1);
			}
			return;
		}
		return; // manditory return statement to call the function if the above if statement doesn't execute
	}
	
	
	
}
