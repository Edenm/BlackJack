package Controller;

public class MainClass {
	/**
	 * The main method of the system.
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			ControllerLogic.getInstance();
		}catch(Exception e){
			System.err.println("Error: cannot initialize ModelLogic, please contact you administrator!");
		}
	}
}