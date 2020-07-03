import java.util.Scanner;

public class MovieReviewerConsole
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		MovieReviewer myReviewer = new MovieReviewer();
		
		int choice = 0;

		String text = "";
		while(!(choice == 2))
		{

			System.out.println("1. Enter movie review");
			System.out.println("2. Exit");
			System.out.println("Enter choice: ");

			choice = scan.nextInt();

			if(choice == 1)
			{
				System.out.println("Enter movie review");
				scan.nextLine();
				text = scan.nextLine();
				int rating =  myReviewer.getAutomatedRating(text);
				if(rating == -1)
				{
					System.out.println("Your review includes multiple unfamiliar words. We cannot provide a rating for this review.");
					System.out.println("Please provide your own rating");
					rating = scan.nextInt();
				}
		
				System.out.println("Rating: " + rating);
			}
			if(choice == 2)
			{
				myReviewer.exitProgram();
			}
		}

	}
}