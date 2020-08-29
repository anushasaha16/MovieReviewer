import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * Scene that is set when application is viewed
 * @author anush
 *
 */
public class MovieRevScene extends Scene
{

	private BorderPane myRoot;
	private MovieReviewer myReviewer;

	public MovieRevScene(Parent root) {
		super(root);
		myRoot = (BorderPane) root;
		myRoot.setStyle("-fx-background-color: Grey");
		myReviewer = new MovieReviewer();

		BorderPane titleBox = new BorderPane();
		titleBox.setPrefHeight(100);
		myRoot.setTop(titleBox);

		Text title = new Text("Movie Reviewer");
		title.setFont(new Font(40));
		titleBox.setCenter(title);

		TextArea input = new TextArea();
		input.setWrapText(true);
		input.setPrefSize(600, 450);

		VBox contents = new VBox();
		HBox h = new HBox();
		h.getChildren().add(input);
		Button getRating = new Button("Calculate Rating For Your Review");
		h.setSpacing(30);
		h.setAlignment(Pos.CENTER);
		contents.getChildren().add(h);

		TextField displayRating = new TextField();
		HBox score = new HBox();
		Button clear = new Button("Clear");
		score.getChildren().add(getRating);
		score.getChildren().add(displayRating);
		score.getChildren().add(clear);
		score.setSpacing(40);

		score.setAlignment(Pos.CENTER);
		contents.getChildren().add(score);
		contents.setSpacing(30);

		myRoot.setCenter(contents);
		
		/**
		 * Once this button is pressed, the rating of the review is displayed
		 */
		getRating.setOnAction(e -> {
			String i = input.getText();
			String r = myReviewer.getAutomatedRating(i) + "";
			/*If there are no recognizable words in the review an error message is displayed*/
			if(myReviewer.getAutomatedRating(i) == -1)
			{
				input.setText(i + "\n" + "A rating cannot be calculated for this review as there is too many unfamiliar words.");
			}
			else
				displayRating.setText(r);
		});
		
		/**
		 * Sets the clear button to empty the text box once pressed
		 */
		clear.setOnAction(e -> {
			input.setText("");
		});
	}


}
