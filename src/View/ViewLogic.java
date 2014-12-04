package View;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Controller.*;

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
	@SuppressWarnings("unused")
	private static ControllerLogic controller;
	//***************************************** Constructors ******************************************
	/**
	 * Full C'tor, for singleton support. 
	 */
	/*private ViewLogic() {
            
	}*/
	//***************************************** Methods ***********************************************
	/**
	 * The method creates this class's instance & provides access to it, by returning a reference (singleton).
	 * @return reference to this class's only instance, or null if reference was already returned (singleton).
	 * @throws GeneralException 
	 */
	/*public static ViewLogic getInstance(ControllerLogic instanceController) throws IOException {
                if(!exists)
                {
					exists = true;
					instance  = new ViewLogic();
					controller=instanceController;
					return instance;
                }
                return null;
	}*/
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) 	 {
		// TODO Auto-generated method stub
		try{
			
			
			  AnchorPane page = (AnchorPane) FXMLLoader.load(ViewLogic.class.getResource("Table.fxml"));
	            Scene scene = new Scene(page);
	            scene.getStylesheets().add("/view/TableCss.css");
	            primaryStage.setScene(scene);
	            primaryStage.setTitle("FXML is Simple");
	            primaryStage.show();
	       
	} catch(Exception e) {
		e.printStackTrace();
	}
	}

       //----------------------------------- add methods -------------------------------------------------
 
       //----------------------------------- Remove methods ----------------------------------------------

       //----------------------------------- update methods ----------------------------------------------
	
       //***************************************** other Methods *****************************************
		
}