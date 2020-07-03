import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mickel.gui.GUI;
public class View
{
	private Stage myStage;


	private MovieRevScene myScene;

	public View()
	{
		myScene = new MovieRevScene(new BorderPane());

		//GUI.highlight(myTranslateScene);
	}

	public Stage getStage()
	{
		return myStage;
	}

	public void setStage(Stage stage)
	{
		myStage = stage;
		myStage.setTitle("Movie Reviewer");

		myStage.setWidth(1300);
		myStage.setHeight(700);
		myStage.setScene(myScene);

		stage.show();
	}
}