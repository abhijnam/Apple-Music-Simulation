import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

import javax.security.auth.kerberos.KerberosKey;



// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library
// Student Number: 501191089
// Name: Tejaswini Abhijna Medicharla
public class AudioContentStore
{

		private HashMap<String,Integer> titles; // key = String , value = Integer
		private HashMap<String,ArrayList<Integer>> artist;
		private HashMap<Song.Genre,ArrayList<Integer>> genre;
		private ArrayList<AudioContent> contents; 
		
		// when a method shouldn't catch exception
		public AudioContentStore()  
		{
			contents = new ArrayList<AudioContent>();
			titles = new HashMap<String,Integer>(); // initialisation of hashmap that takes the string of title names as key and the indexes where the title occured
			artist = new HashMap<String,ArrayList<Integer>>(); // initialisation of hashmap that takes in a string of artist name as key and the indices of that artist in the contents arraylist as the value in the map
			genre = new HashMap<Song.Genre,ArrayList<Integer>>();// initalisation og hashmap that takes in the enum Genre in class song as the key and maps to the indices of where the song was located in the contents arraylist
			try 
			{
				// in the try-catch exception handelling, we are passing in a string that contains a text file and the read me method will take the contents of that file and add it to the contents arraylist as objects
				readFile("store.txt");

				// building maps


				// title map
				for(int i = 0; i < contents.size(); i++)// goes through arraylist of contents ( which have objects of super class AudioContent ex: songs, audiobooks, podcast)
				{
					String title = contents.get(i).getTitle(); // storing the titles of each audio content object into the variable string title
					titles.put(title,i);// we are building the hashmap by setting the key as the title and the indexes as the value
				}
				// artist map
				for(int j = 0; j < contents.size();j++)// goes through arraylist of contents
				{
					if(contents.get(j).getType().equalsIgnoreCase(Song.TYPENAME)) // checks to see if the object is of type "SONG"
					{
						Song s1 = (Song) contents.get(j);// Downcasting into song object to get methods in sub-class song
						String myartist = s1.getArtist();// adding the artist name to string
						if(!artist.containsKey(myartist))// checking to see if the hashmap  artist does not already contains the key (i.e the artist name)
						{
							ArrayList<Integer> key_count = artist.get(myartist); // getting the value (i.e arraylist of integer) and pointing to the reference variable key_count
							if(key_count == null) // if the arraylist of integers for that artist results in null, it means that the arraylist was never created for that artist
							{
								key_count = new ArrayList<Integer>();// making a new arraylist
								key_count.add(j);// adding the index where this artist occurred
								artist.put(myartist,key_count);// adding the key-value pair to artist hashmap
							}
						}
						else if(artist.containsKey(myartist)) // checking to see if key exists
						{
							ArrayList<Integer> songmade = artist.get(myartist);// referencing 
							songmade.add(j);// adding index
							
						}

					
					
					}
					else if(contents.get(j).getType().equalsIgnoreCase(AudioBook.TYPENAME))// looks for audiobook
					{
						AudioBook b1 = (AudioBook) contents.get(j); // downcasting
						String author = b1.getAuthor(); // setting the author as key
						if(!artist.containsKey(author)) 
						{
							// same format as songs but instead of using artist as key we use the author
							ArrayList<Integer> key_count1 = artist.get(author);
							if(key_count1 == null)
							{
								key_count1 = new ArrayList<Integer>();
								key_count1.add(j);
								artist.put(author,key_count1);
							}
						}
						else if(artist.containsKey(author))
						{
							ArrayList<Integer>bookmade = artist.get(author);
							bookmade.add(j);
						}
						
					}

				}
				// genre map
				for(int k = 0; k < contents.size();k++) // looping through contents arraylist
				{
					if(contents.get(k).getType().equalsIgnoreCase(Song.TYPENAME)) // checks to see if object is a song
					{
						Song s2 = (Song) contents.get(k); // downcasting AudioContent object to song to get methods
						Song.Genre togenre = s2.getGenre(); // converting string to Song.Genre enum value
						if(!genre.containsKey(togenre)) // if the genre map doesn't contain the key
						{
							ArrayList<Integer> count3 = genre.get(togenre); // check to see if an arraylist was created for that genre
							if(count3 == null) // if the arraylist wasn't created, then
							{
								count3 = new ArrayList<Integer>(); // make a new arraylist
								count3.add(k); // add the index of the object of contents arraylist
								genre.put(togenre,count3); // put the key-value pair in the map
							}
						}
						else if(genre.containsKey(togenre)) // if the key does exist
						{
							ArrayList<Integer> madegenre = genre.get(togenre); // get the arraylist associated with the key
							madegenre.add(k); // add the appropriate index from contents arraylist to map
						}
					}
				}

			}
			catch(FileNotFoundException e) // catch the exception thrown from the try-block
			{
				System.out.println(e.getMessage());
				System.exit(0);
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
				System.exit(0);
			}
			catch(NumberFormatException e)
			{
				System.out.println(e.getMessage());
				System.exit(0);
			}


			
		  // Create some songs audiobooks and podcasts and to store
			/*String file = "Yesterday, all my troubles";
			contents.add(new Song("Yesterday", 1965, "123", Song.TYPENAME, file, 2, "The Beatles", "Paul McCartney", Song.Genre.POP, file));
			
			file = "I'm sorry if I seem uninterested\r\n"
					+ "Or I'm not listenin' or I'm indifferent\r\n"
					+ "Truly, I ain't got no business here\r\n"
					+ "But since my friends are here, I just came to kick it\r\n"
					+ "But really I would rather be at home all by myself not in this room\r\n"
					+ "With people who don't even care about my well being";
			contents.add(new Song("Here", 2015, "391", Song.TYPENAME, file, 3, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
			
			file = "Yo, Big Shaq, the one and only\r\n"
					+ "Man's not hot, never hot\r\n"
					+ "Skrrat (GottiOnEm), skidi-kat-kat\r\n"
					+ "Boom\r\n"
					+ "Two plus two is four\r\n"
					+ "Minus one that's three, quick maths\r\n"
					+ "Everyday man's on the block\r\n"
					+ "Smoke trees (Ah)";
			contents.add(new Song("Man's Not Hot", 2017, "374", Song.TYPENAME, file, 2, "Michael Dapaah", "Michael Dapaah", Song.Genre.RAP, file));
			
			file = "The world was on fire and no one could save me but you\r\n"
					+ "It's strange what desire will make foolish people do\r\n"
					+ "I never dreamed that I'd meet somebody like you\r\n"
					+ "And I never dreamed that I'd lose somebody like you";
			contents.add(new Song("Wicked Game", 1989, "185", Song.TYPENAME, file, 4, "Chris Isaak", "Chris Isaak", Song.Genre.ROCK, file));
			
			file = "The lights go out and I can't be saved\r\n"
					+ "Tides that I tried to swim against\r\n"
					+ "Have brought me down upon my knees\r\n"
					+ "Oh, I beg, I beg and plead\r\n"
					+ "Singin' come out of things un said";
			contents.add(new Song("Clocks", 2002, "875", Song.TYPENAME, file, 5, "Coldplay", "Guy Berryman, Chris Martin", Song.Genre.ROCK, file));
			
			file = "I'm waking up to ash and dust\r\n"
					+ "I wipe my brow and I sweat my rust\r\n"
					+ "I'm breathing in the chemicals";
			contents.add(new Song("Radioactive", 2012, "823", Song.TYPENAME, file, 3, "Imagine Dragons", "Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee", Song.Genre.ROCK, file));
			
			file = "Birds flying high\r\n"
					+ "You know how I feel\r\n"
					+ "Sun in the sky\r\n"
					+ "You know how I feel\r\n"
					+ "Breeze driftin' on by\r\n"
					+ "You know how I feel\r\n"
					+ "It's a new dawn\r\n"
					+ "It's a new day\r\n"
					+ "It's a new life\r\n"
					+ "For me";
			contents.add(new Song("Feelin' Good", 1965, "875", Song.TYPENAME, file, 3, "Nina Simone", 
					"Anthony Newley, Leslie Bricusse",Song.Genre.JAZZ, file));
			
			file = "Find table spaces, say your social graces\n"
					+ "Bow your head, they're pious here\n"
					+ "But you and I, we're pioneers, we make our own rules\n"
					+ "Our own room, no bias here";
			contents.add(new Song("Wild Things", 2015, "443", Song.TYPENAME, file, 4, "Alessia Cara", "Alessia Cara", Song.Genre.POP, file));
			
			AudioBook book = new AudioBook("Harry Potter and the Goblet of Fire", 2015, "894", AudioBook.TYPENAME,  "", 1236,
					"J.K. Rowling", "Jim Dale", makeHPChapterTitles(), makeHPChapters());
			contents.add(book);
			
			book = new AudioBook("Moby Dick", 2018, "376", AudioBook.TYPENAME,  "", 1422,
					"Herman Melville", "Pete Cross", makeMDChapterTitles(), makeMDChapters());
			contents.add(book);
			
			book = new AudioBook("Shogun", 2018, "284", AudioBook.TYPENAME,  "", 3213,
					"James Clavel", "Ralph Lister", makeSHChapterTitles(), makeSHChapters());
			contents.add(book);
			
			// Create a podcast object if you are doing the bonus see the makeSeasons() method below
			// It is currently commented out. It makes use of a class Season you may want to also create
			// or change it to something else */
					
		}
		
		
		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		//prints the information of all audio content
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)// iterate through the audio content arraylist 
			{
				int index = i + 1; // get the index
				System.out.print("" + index + ". ");
				contents.get(i).printInfo(); // print the information of each audio content object
				System.out.println(); // start the print from a new line
			}
		}
		// searchs for title in contents and prints out the info relating to title, throws custom AudioContentNotFoundException if the title isn't found in the map
		public void search(String title) throws AudioContentNotFoundException
		{
			boolean myfound = false; // variable defined to see if the song is found, initially false because we haven't started looking for the song
			for(String mytitle:titles.keySet()) // iterates through the keys in titles
			{
				if(title.equalsIgnoreCase(mytitle)) // checks to see if the title the user is passing in (aka string title) is equal to any of the keys in the titles map
				{
					int value = titles.get(mytitle); // getting the value for the title
					if(contents.get(value).getType().equalsIgnoreCase(Song.TYPENAME)) // if the title belongs to a song object
					{
						Song myson = (Song) contents.get(value); // downcasting
						System.out.print(value+1+"."+" "); // printing out the index 
						myson.printInfo(); // printing out the song info
						System.out.println(); // the next song starts on a new line
						myfound = true; // changing variable to true because song is found
					}
					else if(contents.get(value).getType().equalsIgnoreCase(AudioBook.TYPENAME))// if the title belongs to a audiobook object
					{
						AudioBook mybook = (AudioBook) contents.get(value);// downcasting
						System.out.print(value+1+"."+" ");// printing out the index 
						mybook.printInfo();// printing out the audiobook info
						System.out.println();// the next audiobook starts on a new line
						myfound = true;// changing variable to true because audiobook is found
					}
				}
			}
			if(!myfound) // after searching arraylist if the title still doesn't exist
			{
				throw new AudioContentNotFoundException("No match for the title"+" "+title); // print custom exception error
			}
			else
			{
				return; // return nothing otherwise (prints the info)
			}
			
		}
		// searchs contents arraylist using artist map to print out the content information of artist
		public void searcha(String artistname) throws AudioContentNotFoundException
		{
			boolean myfound_1 = false;
			for(String names: artist.keySet()) // goes through the keys of the map
			{
				if(names.equalsIgnoreCase(artistname)) // checks to see if the artist name the user is passing in (aka string artistname) is equal to any of the keys in the artist map
				{
					ArrayList<Integer> value_count = artist.get(names); // getting the arraylist for the artist if the key exists
					for(int i =0; i < value_count.size();i++) // goes through arraylist of index in audiocontent with artist name
					{
						if(contents.get(value_count.get(i)).getType().equalsIgnoreCase(Song.TYPENAME)) // if the content is song
						{
							Song s3 = (Song) contents.get(value_count.get(i));
							System.out.print(value_count.get(i)+1+"."+" "); // print out index
							s3.printInfo(); // print song info
							System.out.println();
							myfound_1 = true; // set variable to notify that the song was found
						}
						else if(contents.get(value_count.get(i)).getType().equalsIgnoreCase(AudioBook.TYPENAME)) // if content is audiobook
						{
							AudioBook b3 = (AudioBook)contents.get(value_count.get(i));
							System.out.print(value_count.get(i)+1+"."+" "); // print out index
							b3.printInfo(); // print book info
							System.out.println();
							myfound_1 = true; // set variavle to notify that the audiobook was found
						}
					}
				}
			}
			if(!myfound_1) // if not found print exception message
			{
				throw new AudioContentNotFoundException("No matches for"+" "+artistname);
			}
			else
			{
				return;
			}
		}
		// searchs genre map to see if there is any audiocontent from contents arraylist that has the genre
		public void searchg(String genrename) throws SongNotFoundException
		{
			boolean mytrue = false;
			for(Song.Genre gen:genre.keySet()) // goes through the keys in genre map
			{
				Song.Genre mynewsong = Song.Genre.valueOf(genrename); // gets the Song.Genre enum value
				if(gen == mynewsong)
				{
					ArrayList<Integer> values = genre.get(mynewsong); // gets the arraylist of indicies  
					for(int i = 0; i < values.size();i++)
					{
						System.out.print(values.get(i)+1+"."+" ");
						contents.get(values.get(i)).printInfo(); // prints info of audio content
						System.out.println();
						mytrue = true;
					}
				}
			}
			if(!mytrue)
			{
				throw new SongNotFoundException("No song with the genre"+" "+ genrename);
			}
			else
			{
				return;
			}
		}

		// returns arraylist of indicies in map of artist to use in audioUI
		public ArrayList<Integer> getArtistMapping(String myartist)
		{
			ArrayList<Integer> x= new ArrayList<Integer>(); // reference variable to return at the end if there is no artist that is a key in the artist map
			if(artist.containsKey(myartist))
			{
				ArrayList<Integer> v = artist.get(myartist); // returns the respective arraylist of artist key being passed into the method
				return v;
			}
			return x;
		
		}
			// returns arraylist of indexes in map of genre to use in audioUI
		public ArrayList<Integer> getGenreMapping(String mapmygenre)
		{
			ArrayList<Integer> y = new ArrayList<Integer>();// reference variable to return at the end if there is no genre that is a key in the genre map
			Song.Genre tomapgenre = Song.Genre.valueOf(mapmygenre);// since genre map takes in the enum values of genre in song as their key's we need to convert the string used as the argument for this method to it's respective enum.
			if(genre.containsKey(tomapgenre))
			{
				ArrayList<Integer> p = genre.get(tomapgenre);  // returns the respective arraylist of genre key being passed into the method
				return p;
			}
			return y;
		}



		/*private ArrayList<String> makeHPChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("The Riddle House");
			titles.add("The Scar");
			titles.add("The Invitation");
			titles.add("Back to The Burrow");
			return titles;
		}
		
		private ArrayList<String> makeHPChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("In which we learn of the mysterious murders\r\n"
					+ " in the Riddle House fifty years ago, \r\n"
					+ "how Frank Bryce was accused but released for lack of evidence, \r\n"
					+ "and how the Riddle House fell into disrepair. ");
			chapters.add("In which Harry awakens from a bad dream, \r\n"
					+ "his scar burning, we recap Harry's previous adventures, \r\n"
					+ "and he writes a letter to his godfather.");
			chapters.add("In which Dudley and the rest of the Dursleys are on a diet,\r\n"
					+ " and the Dursleys get letter from Mrs. Weasley inviting Harry to stay\r\n"
					+ " with her family and attend the World Quidditch Cup finals.");
			chapters.add("In which Harry awaits the arrival of the Weasleys, \r\n"
					+ "who come by Floo Powder and get trapped in the blocked-off fireplace\r\n"
					+ ", blast it open, send Fred and George after Harry's trunk,\r\n"
					+ " then Floo back to the Burrow. Just as Harry is about to leave, \r\n"
					+ "Dudley eats a magical toffee dropped by Fred and grows a huge purple tongue. ");
			return chapters;
		}
		
		private ArrayList<String> makeMDChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Loomings.");
			titles.add("The Carpet-Bag.");
			titles.add("The Spouter-Inn.");
			return titles;
		}
		private ArrayList<String> makeMDChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("Call me Ishmael. Some years ago never mind how long precisely having little\r\n"
					+ " or no money in my purse, and nothing particular to interest me on shore,\r\n"
					+ " I thought I would sail about a little and see the watery part of the world.");
			chapters.add("stuffed a shirt or two into my old carpet-bag, tucked it under my arm, \r\n"
					+ "and started for Cape Horn and the Pacific. Quitting the good city of old Manhatto, \r\n"
					+ "I duly arrived in New Bedford. It was a Saturday night in December.");
			chapters.add("Entering that gable-ended Spouter-Inn, you found yourself in a wide, \r\n"
					+ "low, straggling entry with old-fashioned wainscots, \r\n"
					+ "reminding one of the bulwarks of some condemned old craft.");
			return chapters;
		}
		
		private ArrayList<String> makeSHChapterTitles()
		{
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("Prologue");
			titles.add("Chapter 1");
			titles.add("Chapter 2");
			titles.add("Chapter 3");
			return titles;
		}
		
		private ArrayList<String> makeSHChapters()
		{
			ArrayList<String> chapters = new ArrayList<String>();
			chapters.add("The gale tore at him and he felt its bite deep within\r\n"
					+ "and he knew that if they did not make landfall in three days they would all be dead");
			chapters.add("Blackthorne was suddenly awake. For a moment he thought he was dreaming\r\n"
					+ "because he was ashore and the room unbelieveable");
			chapters.add("The daimyo, Kasigi Yabu, Lord of Izu, wants to know who you are,\r\n"
					+ "where you come from, how ou got here, and what acts of piracy you have committed.");
			chapters.add("Yabu lay in the hot bath, more content, more confident than he had ever been in his life.");
			return chapters;
		}*/
		
		// Podcast Seasons
		/*
		private ArrayList<Season> makeSeasons()
		{
			ArrayList<Season> seasons = new ArrayList<Season>();
		  Season s1 = new Season();
		  s1.episodeTitles.add("Bay Blanket");
		  s1.episodeTitles.add("You Don't Want to Sleep Here");
		  s1.episodeTitles.add("The Gold Rush");
		  s1.episodeFiles.add("The Bay Blanket. These warm blankets are as iconic as Mariah Carey's \r\n"
		  		+ "lip-syncing, but some people believe they were used to spread\r\n"
		  		+ " smallpox and decimate entire Indigenous communities. \r\n"
		  		+ "We dive into the history of The Hudson's Bay Company and unpack the\r\n"
		  		+ " very complicated story of the iconic striped blanket.");
		  s1.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeFiles.add("here is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeLengths.add(31);
		  s1.episodeLengths.add(32);
		  s1.episodeLengths.add(45);
		  seasons.add(s1);
		  Season s2 = new Season();
		  s2.episodeTitles.add("Toronto vs Everyone");
		  s2.episodeTitles.add("Water");
		  s2.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s2.episodeFiles.add("Can the foundation of Canada be traced back to Indigenous trade routes?\r\n"
		  		+ " In this episode Falen and Leah take a trip across the Great Lakes, they talk corn\r\n"
		  		+ " and vampires, and discuss some big concerns currently facing Canada's water."); 
		  s2.episodeLengths.add(45);
		  s2.episodeLengths.add(50);
		 
		  seasons.add(s2);
		  return seasons;
		}
		*/

		// returns arraylist of audiocontent objects
		private ArrayList<AudioContent> readFile(String filename) throws IOException,FileNotFoundException,NumberFormatException
		{
			File file = new File(filename); // opens the file
			if(file.length() == 0)
			{
				throw new IOException("File is empty/Not found");
			}
			Scanner myscan = new Scanner(file); // reading the file
			String type = ""; // storing the text in variables
			String id = "";
			String title = "";
			String year = "";
			String length = "";
			String artist = "";
			String composer = "";
			String genre = "";
			String audiofile = "";
			String author = "";
			String narrator = "";
			while(myscan.hasNextLine())
			{
				String line = myscan.nextLine(); // reads the nextLine
				String lyrics = "";
				String chapter = "";
				if(line.equalsIgnoreCase("SONG")) // if the line contains the string "SONG"
				{
					type = line;
					id = myscan.nextLine(); // stores the next line in variable
					title = myscan.nextLine();
					year = myscan.nextLine();
					length = myscan.nextLine(); // when we are taking in an integer, it will not read a new line, it will only read the next character.
					artist = myscan.nextLine();
					composer = myscan.nextLine();
					genre = myscan.nextLine();
					int lyrics_length = myscan.nextInt();
					for(int i = 0; i <= lyrics_length;i++)
					{
						lyrics += myscan.nextLine()+"\n";
					}
					int year_1 = Integer.parseInt(year);
					int length_1 = Integer.parseInt(length);
					Song.Genre mysong = Song.Genre.valueOf(genre);
					contents.add(new Song(title, year_1, id, type, audiofile, length_1, artist, composer, mysong, lyrics)); // takes the input variables and creates object of class AudioContent, i.e Song
					System.out.println("LOADING SONG");
					
				}
				if(line.equalsIgnoreCase("AUDIOBOOK")) // if the line contains the string "AUDIOBOOK"
				{
					ArrayList<String> chapterTitles = new ArrayList<String>(); // arraylist stores input of chapter titles
					ArrayList<String> chapters = new ArrayList<String>(); // arraylist stores input of chapters
					type = line;
					id = myscan.nextLine();
					title = myscan.nextLine();
					year = myscan.nextLine();
					length = myscan.nextLine(); 
					author = myscan.nextLine();
					narrator = myscan.nextLine();
					int titles_length = myscan.nextInt(); // takes in the number of chapter titles
					myscan.nextLine();
					for(int i = 0; i < titles_length;i++)
					{
						String ch = myscan.nextLine();
						chapterTitles.add(ch); // adds line to chapter titles arraylist
					}
					for(int j =0;j<chapterTitles.size();j++) // for each chapter title
					{
						chapter = ""; // makes a new chapter to add from the file
						int chap_length = myscan.nextInt(); // takes in the number of lines to read as the chapter
						myscan.nextLine(); // starts reading from the next line
						for(int k = 0; k < chap_length;k++)
						{
							chapter += myscan.nextLine() + "\n"; // adds chapter to empty string to create the chapter
						}
						chapters.add(chapter); // add to arraylist chapters
					}
					int year_2 = Integer.parseInt(year);
					int length_2 = Integer.parseInt(length);
					contents.add(new AudioBook(title, year_2, id, type, audiofile, length_2, author, narrator, chapterTitles, chapters)); // creating AudioBook object
					System.out.println("LOADING AUDIOBOOK");
					
	
				}
			
				
			}
			myscan.close();
			return contents;
			
		}
}
