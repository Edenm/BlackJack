 package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

/**Model Logic class*/
public final class ModelLogic implements Serializable{
	//***************************************** Variables *********************************************
	/** ModelLogic's serialVersionUID*/
	private static final long serialVersionUID = 6141521813577450598L;
	/**Singleton instance of this class, loaded on the first execution of ModelLogic.getInstance()*/
	private static ModelLogic instance;
	/**Boolean flag for class instance existence (singleton)*/
	private static boolean exists = false;
	/**Dealer reference pointer*/
	private Dealer dealer;
	/**Player reference pointer*/
	private Player player;
	//***************************************** Constructors ******************************************
	/**
	 * Full C'tor, for singleton support. 
	 */
	private ModelLogic() {
		//dealer= new Dealer();// class not finished wait to finish constructor and then delete the note
		player= new Player();
	}
	//***************************************** Methods ***********************************************
	/**
	 * The method creates this class's instance & provides access to it, by returning a reference (singleton).
	 * @return reference to this class's only instance, or null if reference was already returned (singleton).
	 * @throws GeneralException 
	 */
	public static ModelLogic getInstance() throws IOException, FileNotFoundException, ClassNotFoundException {
		if(! exists)
		{
				exists = true;
				instance  = new ModelLogic();
				return instance;
		}
		return null;
	}													

    //----------------------------------- add methods -------------------------------------------------
	
	//----------------------------------- remove methods ----------------------------------------------

	//----------------------------------- update methods ----------------------------------------------

    //***************************************** other Methods *****************************************

}
