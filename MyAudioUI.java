import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)
// Student Number: 501191089
//Name: Tejaswini Abhijna Medicharla
public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				int index_1 = 0;
				int index_2 = 0;
				
				System.out.print("Store Content from #: ");
				if (scanner.hasNextInt())
				{
					index_1 = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				System.out.print("Store Content to #: ");
				if(scanner.hasNextInt())
				{
					index_2 = scanner.nextInt();
					scanner.nextLine();
				}
				for(int i = index_1; i <= index_2; i++)
				{
					AudioContent content = store.getContent(i);
					try
					{
						mylibrary.download(content);
					}
					catch(AudioContentNotFoundException e)
					{
						System.out.println(e.getMessage());
					}
				}
				
									
			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index = 0;
				
				System.out.print("Song number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
					//scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				// calls the library method playsong and takes the user input stored in the variable index, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.playSong(index);
				}
				catch(SongNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				// Print error message if the song doesn't exist in the library
			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index = 0;
				
				System.out.print("Audio Book number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
					//scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				// calls the library method playsong and takes the user input stored in the variable index, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.printAudioBookTOC(index);
				}
				catch(AudioBookNotFoundException e)
				{
					System.out.println(e);
				}
			// Print error message if the book doesn't exist in the library
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index = 0;
				int chp = 0;
		
				System.out.print("Audio Book number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				System.out.print("Chapter: ");
				if (scanner.hasNextInt())
				{
					chp = scanner.nextInt();
					scanner.nextLine();
				}
				// calls the library method playsong and takes the user input stored in the variable index and the varible chp, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.playAudioBook(index, chp);
				}
				catch(AudioBookNotFoundException e)
				{
					System.out.println(e);
				}
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String tt_2 = "";
				System.out.print("Playlist title: ");
				if(scanner.hasNext())
				{
					tt_2 = scanner.next();
					scanner.nextLine();
				}
				// calls the library method playsong and takes the user input stored in the variable tt_2, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.playPlaylist(tt_2);
				}
				catch(PlayListNotFoundException e)
				{
					System.out.println(e);
				}
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				// creating an empty string to store the scanner input
				String tt_1 = "";
				System.out.print("Playlist title: ");
				// checks if there is a next input from the scanner
				if(scanner.hasNext())
				{
					// storing the user input in variable

					tt_1 = scanner.next();
					scanner.nextLine();
				}
				// creating an integer variable
				int cont_num = 0;
				System.out.print("Content number: ");
				if(scanner.hasNext())
				{
					cont_num = scanner.nextInt();
					scanner.nextLine();
				}
				// calls the library method playsong and takes the user input stored in the variable tt_1 and cont_num, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.playPlaylist(tt_1, cont_num);
				}
				catch(PlayListNotFoundException e)
				{
					System.out.println(e);
				}
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int song_num = 0;
				System.out.print("Library Song #: ");
				if(scanner.hasNext())
				{
					song_num = scanner.nextInt();
					scanner.nextLine();
				}
				// calls the library method playsong and takes the user input stored in the variable song_num, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.deleteSong(song_num);
				}
				catch(SongNotFoundException e)
				{
					System.out.println(e);
				}
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String mytt = "";
				System.out.print("Playlist Title: ");
				if (scanner.hasNext())
				{
					mytt = scanner.next();
					scanner.nextLine();
					//scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				// calls the library method playsong and takes the user input stored in the variable mytt, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.makePlaylist(mytt);
				}
				catch(PlayListNotFoundException e)
				{
					System.out.println(e);
				}
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String mytt = "";
				System.out.print("Playlist Title: ");
				if (scanner.hasNext())
				{
					mytt = scanner.next();
					scanner.nextLine();
					//scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				// calls the library method playsong and takes the user input stored in the variable mytt, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.printPlaylist(mytt);
				}
				catch(PlayListNotFoundException e)
				{
					System.out.println(e);
				}
				
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				String mytt = "";
				System.out.print("Playlist Title: ");
				if (scanner.hasNext())
				{
					mytt = scanner.next();
					scanner.nextLine();
					//scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				String mycont = "";
				System.out.print("Content Type[Song, PodCast, AudioBook]: ");
				if (scanner.hasNext())
				{
					mycont = scanner.next();
					scanner.nextLine();
					//scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				int mylibindex = 0;
				System.out.print("Library Content #: ");
				if (scanner.hasNextInt())
				{
					mylibindex = scanner.nextInt();
					scanner.nextLine();
					//scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				// calls the library method playsong and takes the user input stored in the variable mycont,mylibindex,mytt, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.addContentToPlaylist(mycont,mylibindex,mytt);
				}
				catch(AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				catch(PlayListNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				catch(SongNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				catch(AudioBookNotFoundException e)
				{
					System.out.println(e);
				}
				
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				String tt_3 = "";
				System.out.print("Playlist title: ");
				if(scanner.hasNext())
				{
					tt_3 = scanner.next();
					scanner.nextLine();
				}
				int play_num = 0;
				System.out.print("Playlist Content #: ");
				if(scanner.hasNext())
				{
					play_num = scanner.nextInt();
					scanner.nextLine();
				}
				// calls the library method playsong and takes the user input stored in the variable play_num and tt_3, checks to see if the method calls true or false, when false is called, it will print out the error message
				try
				{
					mylibrary.delContentFromPlaylist(play_num, tt_3);
				}
				catch(PlayListNotFoundException e)
				{
					System.out.println(e);
				}
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}
			else if (action.equalsIgnoreCase("SEARCH"))
			{
				String mytitles = "";
				System.out.print("Title: ");
				if(scanner.hasNextLine())
				{
					mytitles = scanner.nextLine();
					
				}
				try
				{
					store.search(mytitles);
				}
				catch(AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());
				}
				
			}
			else if(action.equalsIgnoreCase("SEARCHA"))
			{
				String auth1 = "";
				System.out.print("Artist/Author: ");
				if(scanner.hasNextLine())
				{
					auth1 = scanner.nextLine();
				}
				try
				{
					store.searcha(auth1); // method in audiocontentstore
				}
				catch(AudioContentNotFoundException e)
				{
					System.out.println(e.getMessage());// prints out the message given in exception
				}

			}
			else if(action.equalsIgnoreCase("SEARCHG"))
			{
				String thegenre = "";
				System.out.print("Genre[POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				if(scanner.hasNextLine())
				{
					thegenre = scanner.nextLine();
				}
				try
				{
					store.searchg(thegenre); // method in audiocontentstore
				}
				catch(SongNotFoundException e)
				{
					System.out.println(e.getMessage()); // prints out message from exception
				}
			}
			else if(action.equalsIgnoreCase("DOWNLOADA"))
			{
				String auth = "";
				System.out.print("Artist/Author: ");
				if(scanner.hasNextLine())
				{
					auth = scanner.nextLine();
				}
				ArrayList<Integer> storevals = store.getArtistMapping(auth); // gets arraylist of integers in AudioContentStore
				if(!storevals.isEmpty())
				{
					for(int i = 0; i < storevals.size();i++)
					{
						AudioContent myContent = store.getContent(storevals.get(i)+1); // creating an audiocontent object to download
						try
						{
							mylibrary.downloadag(myContent); // downloading using the new download method
						}
						catch(AudioContentNotFoundException e)
						{
							System.out.println(e.getMessage());
						}
					}
				}
				else if(storevals.isEmpty())
				{
					System.out.println("Artist/Author not found");
				}
			}
			else if(action.equalsIgnoreCase("DOWNLOADG"))
			{
				String mygenre = "";
				System.out.print("Genre[POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
				if(scanner.hasNextLine())
				{
					mygenre = scanner.nextLine();
					//scanner.nextLine();
				}
				//Song.Genre evalofgenre = Song.Genre.valueOf(mygenre);
				ArrayList<Integer> genrevals = store.getGenreMapping(mygenre);
				if(!genrevals.isEmpty())
				{
					for(int i = 0; i < genrevals.size();i++)
					{
						AudioContent myContent = store.getContent(genrevals.get(i)+1);// creating an audiocontent object to download
						try
						{
							mylibrary.downloadag(myContent);// downloading using the new download method
						}
						catch(AudioContentNotFoundException e)
						{
							System.out.println(e.getMessage());
						}
					}
				}
				else if(genrevals.isEmpty())
				{
					System.out.println("Genre not found");
				}

			}
			//scanner.close();
			System.out.print("\n>");
		}
	}
}
