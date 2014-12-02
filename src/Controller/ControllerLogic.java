package Controller;

import java.util.*;
import Model.*;
import View.ViewLogic;

/**Controller Logic class*/
public final class ControllerLogic {// implements I_ControllerLogic {  <<<--- for 2nd HW !!
	//***************************************** Variables *********************************************
	/**Singleton instance of this class, loaded on the first execution of ControllerLogic.getInstance()*/
	private static ControllerLogic instance;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	/**ViewLogic reference pointer*/
	private static ViewLogic view; //assuming we've only one.
	/**ModelLogic reference pointer*/
	private static ModelLogic model; //assuming we've only one.
	//***************************************** Constructors ******************************************
	/**
	 * Full C'tor, for singleton support. 
	 */
	private ControllerLogic() {
		
	}
	
	//***************************************** Methods ***********************************************
	/**
	 * The method creates this class's instance & provides access to it, by returning a reference (singleton).
	 * @return reference to this class's only instance, or null if reference was already returned (singleton).
	 */
	public static ControllerLogic getInstance()  {
		try {
			if(! exists){
				exists = true;
				model = ModelLogic.getInstance();
				instance = new ControllerLogic();
	 			view = ViewLogic.getInstance(instance);
				return instance;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error: cannot initialize ModelLogic, please contact you administrator!");
        }
		return instance;
	}												
	
    //----------------------------------- add methods -------------------------------------------------
	
	//----------------------------------- remove methods ----------------------------------------------
	
	//----------------------------------- connect methods ---------------------------------------------
	
	//----------------------------------- disconnect methods ------------------------------------------
	
	//----------------------------------- update methods ----------------------------------------------
	
	
    //***************************************** other Methods *****************************************
      
}
