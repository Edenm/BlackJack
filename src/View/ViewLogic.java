package View;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Controller.*;
import Model.Card;
import Utils.User;

/**
 * View Logic class
 * @author Eden
 */
public final class ViewLogic  extends Application {
	//***************************************** Variables *********************************************
	/**Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()*/
	private static ViewLogic instance ;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	

	/**ControllerLogic reference pointer*/
	private static ControllerLogic controller;
	/** root to the sence of the game */
	private static  AnchorPane page;
	
	//***************************************** Constructors ******************************************
	/**
	 * full C'tur
	 */
	public ViewLogic() {
	}
	
	//***************************************** Methods ***********************************************
	/**
	 * The method creates this class's instance & provides access to it, by returning a reference (singleton).
	 * @return reference to this class's only instance, or null if reference was already returned (singleton).
	 * @throws GeneralException 
	 */
	public static ViewLogic getInstance(ControllerLogic instanceController) throws IOException {
                if(!exists)
                {
					exists = true;
					instance  = new ViewLogic();
					controller=instanceController;
					startview();
					return instance;
                }
                return instance;
	}

	public static void startview()
	{
		launch();
	}

	@Override
	public void start(Stage primaryStage) 	 
	{
		try{
			    page  = (AnchorPane) FXMLLoader.load(ViewLogic.class.getResource("Table.fxml"));
			    Scene scene = new Scene(page);
		        scene.getStylesheets().add("/view/TableCss.css");
		        primaryStage.setScene(scene);
		        primaryStage.setTitle("BlackJack Enjoy!");
		       primaryStage.getIcons().add(new Image("/view/photos/icon.png"));
		        setWindowSize(primaryStage, 1000, 700);
		        primaryStage.show();
	           
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//-----------------------------------Window method--------------------------------------------------
	
	public static void setWindowSize(Stage primaryStage,int width, int height)
	{
		// max
		primaryStage.setMaxWidth(width);
        primaryStage.setMaxHeight(height);
        
        // min
        primaryStage.setMinHeight(height);
        primaryStage.setMinWidth(width);
	}
    //----------------------------------- cards methods -------------------------------------------------
 
	public static Card getCardFromDeck(User user)
	{
		return controller.getCard(user);
	}
	
	/**
	 * 	
	 * @return the value of all cards player have
	 */
	public static Integer playerValueCards() {
		return controller.playerValueCards();
	}
	
     //----------------------------------- Chip's methods ----------------------------------------------
		
	
	public static int getChips()
	{
	  return controller.getChips();
	}
	
	
	public static Boolean setBets(int amount)
	{
	  return controller.setbets(amount);
	}
	
	public static Integer getBets() {
	return controller.getBets();
	}
       //----------------------------------- update methods ----------------------------------------------
	
       //***************************************** other Methods *****************************************
	public static AnchorPane getPage() {
		return page;
	}

		
}