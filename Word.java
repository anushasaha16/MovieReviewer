/**
 * Word class provides methods to calculate a word's sentiment rating
 * @author anush
 *
 */
public class Word
{
	private String str;	//string representation of the word
	private int totalWeight;//sum of all the ratings of previous reviews Word has been used in
	private int totalCount;	//keeps track of how many times the word has been used before

	public Word(String s, int w, int c)
	{
		str = s;
		totalWeight = w;
		totalCount = c;
	}
	
	/**
	 * 
	 * @return	string representation of the word
	 */
	public String getText()
	{
		return str;
	}
	
	/**
	 * 
	 * @return integer totalWeight
	 */
	public int getTotalWeight()
	{
		return totalWeight;
	}
	
	/**
	 * Every time Word is used, totalWeight is increased by the rating of the review Word was used in
	 * @param r	rating of review Word was used in
	 */
	public void addToTotalWeight(int r)
	{
		totalWeight = totalWeight + r;
	}
	
	/**
	 * 
	 * @return integer totalCount which keeps track of how many times the word has been used before
	 */
	public int getTotalCount()
	{
		return totalCount;
	}
	
	/**
	 * increments the totalCount
	 */
	public void addToTotalCount()
	{
		totalCount++;
	}
	
	/**
	 * Calls the methods above in order to calculate the sentiment rating of an instance of Word
	 * @return sentiment rating of Word
	 */
	public int getSentimentRating()
	{
		double r = this.getTotalWeight()/this.getTotalCount();
		if(r%1 < 0.5)
			return this.getTotalWeight()/this.getTotalCount();
		return this.getTotalWeight()/this.getTotalCount() + 1;
	}
}
