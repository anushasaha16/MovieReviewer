import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * MovieReviewer class provides methods to calculate an automated rating for an user's review
 * @author anush
 *
 */
public class MovieReviewer
{
	private Map<String, Integer> myReviews;			//maps String reviews to their rating indicated by the text file	
	private Map<String, Word> myWords;				//maps String words to Word object
	private Map<Word, Integer> mySentimentRatings;	//maps each Word to their calculated sentiment rating

	public MovieReviewer()
	{
		myReviews = new HashMap<>();
		myWords = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		mySentimentRatings = new HashMap<>();
		this.loadDataFromFile();
	}
	
	/**
	 * reads data from text file "moviereviews.txt" and puts it into maps to be accessed in other methods
	 */
	private void loadDataFromFile()
	{
		try
		{
			Scanner scan = new Scanner(this.getClass().getResourceAsStream("moviereviews.txt"));
			while(scan.hasNextLine())
			{
				String line = scan.nextLine();
				String[] parts = line.split(" ");

				int rating = Integer.parseInt(parts[0]);
				String s = line.substring(2);
				myReviews.put(s, rating);
				String[] words = s.toLowerCase().split("[^a-zA-Z0-9'-]+");
				for(String w: words)
				{
					if(!myWords.containsKey(w))
					{
						Word word = new Word(w, rating, 1);
						myWords.put(w, word);
						mySentimentRatings.put(word, rating);
					}
					else
					{
						Word word = myWords.get(w);
						word.addToTotalWeight(rating);
						word.addToTotalCount();							
						myWords.put(w, word);
						mySentimentRatings.put(word, word.getSentimentRating());
					}
					
				}
			}
			scan.close();
		}
		catch(Exception e)
		{
			System.out.println("File Not Found. No Data Loaded");
		}
	}
	
	/**
	 * Takes the average of the sentiment ratings of all words in the given review and returns that as the automated
	 * rating of the review
	 * @param rev	User-inputted review
	 * @return		calculated automated rating of user's review or -1 if rating was not able to be calculated
	 */
	public int getAutomatedRating(String rev)
	{
		String[] words = rev.toLowerCase().split("[^a-zA-Z0-9'-]+");
		int totalRating = 0;
		int totalWords = 0;
		for(int i = 0; i < words.length; i++)
		{
			if(myWords.containsKey(words[i]))
			{
				totalRating = totalRating + mySentimentRatings.get(myWords.get(words[i]));
				totalWords++;
			}
		}
		if(totalWords > words.length/2)
		{
			double r = totalRating/totalWords;
			if(r%1 < 0.5)
				return totalRating/totalWords;
			return totalRating/totalWords + 1;
		}

		return -1;
	}
	
}
