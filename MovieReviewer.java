import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MovieReviewer
{
	private Map<String, Integer> myReviews;
	private Map<String, Word> myWords;
	private Map<Word, Integer> mySentimentRatings;

	public MovieReviewer()
	{
		myReviews = new HashMap<>();
		myWords = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		mySentimentRatings = new HashMap<>();
		this.loadDataFromFile();
	}

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
				for(int i = 1; i < parts.length; i++)
				{
					if(this.isPunctuation(parts[i]) == false)
					{
						if(!myWords.containsKey(parts[i]))
						{
							Word w = new Word(parts[i], rating, 1);
							myWords.put(parts[i], w);
							mySentimentRatings.put(w, rating);
						}
						else
						{
							Word w = myWords.get(parts[i]);
							w.addToTotalWeight(rating);
							w.addToTotalCount();
							myWords.put(parts[i], w);
							mySentimentRatings.put(w, w.getSentimentRating());
						}
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

	public int getAutomatedRating(String rev)
	{
		String result = rev.replaceAll("\\p{Punct}", " $0 ");
		String[] words = result.split(" ");
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

	public boolean isPunctuation(String c)
	{
		String punctuations = ".,:;!?'";
		if(punctuations.contains(c))
			return true;
		return false;
	}

	public void exitProgram()
	{
		System.exit(0);
	}

}