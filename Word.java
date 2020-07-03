public class Word
{
	private String str;
	private int totalWeight;
	private int totalCount;

	public Word(String s, int w, int c)
	{
		str = s;
		totalWeight = w;
		totalCount = c;
	}

	public String getText()
	{
		return str;
	}

	public int getTotalWeight()
	{
		return totalWeight;
	}

	public void addToTotalWeight(int r)
	{
		totalWeight = totalWeight + r;
	}

	public int getTotalCount()
	{
		return totalCount;
	}

	public void addToTotalCount()
	{
		totalCount++;
	}

	public int getSentimentRating()
	{
		double r = this.getTotalWeight()/this.getTotalCount();
		if(r%1 < 0.5)
			return this.getTotalWeight()/this.getTotalCount();
		return this.getTotalWeight()/this.getTotalCount() + 1;
	}
}