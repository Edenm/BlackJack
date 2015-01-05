package View;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	/** varible login video**/
	@FXML
	MediaView video;
	/** varible text field username**/
	@FXML
	TextField txtnameLogin;
	/** varible background pic of login**/
	@FXML
	 ImageView LoginPic;
	/** varible button skip video pf login**/
	@FXML
	Button btnSkip;
	/** varible login button **/
	@FXML
	Button btnLogin;
	
	MediaPlayer mediaPlayer;
	
	private static boolean isFirstTime=true;
	
	/**
	 * method intalize the page- disable the loggin buuton text field and background pic
	 * until the video end
	 */
	

	public void initialize(URL location, ResourceBundle resources) {
		txtnameLogin.setVisible(false);
		btnLogin.setVisible(false);
		if(isFirstTime){
			playVideo();
			isFirstTime=false;
		}
		else{
			LoginPic.setVisible(true);
			videosTOP();
		}
		
		
		
	}
	/**
	 * method open the video and play it on
	 */
	
	public void playVideo()
	{
		try{
	       Media media = new Media(new File("Login.mp4").toURI().toString());
	      mediaPlayer = new MediaPlayer(media);
	        mediaPlayer.setAutoPlay(true);
	      //  mediaPlayer.setCycleCount(10);
	        video.setMediaPlayer(mediaPlayer);
	        mediaPlayer.setOnReady(new Runnable() {
	            public void run() {
	                
	            }
	        });
	        LoginPic.setVisible(true);
	
	        mediaPlayer.setOnEndOfMedia((new Runnable() {
	            public void run() {
	            	videosTOP();
	               
	            }}));
	        
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * method open page after video to login
	 * call by play video, when video finish
	 */
	
	public void videosTOP()
	{
		txtnameLogin.setVisible(true);
		btnLogin.setVisible(true);
		video.setVisible(false);
		if(mediaPlayer!=null)
			mediaPlayer.stop();
        btnSkip.setVisible(false);
   
	}
	/**
	 * fxml method call when skip button clicked on video
	 */

	@FXML
	public void clickSkip()
	{
		videosTOP();
        
	}
	/**
	 * click login open the game page by call method start game
	 * check the input from the textbox
	 * @param event
	 * @throws IOException
	 */
	@FXML
	public void ClickLogin(ActionEvent event) throws IOException
	{
		if(txtnameLogin.getText().equals(""))
		{
			txtnameLogin.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400)");
			return;
		}
		
		ViewLogic.login(txtnameLogin.getText());
		
		ViewLogic.startGame(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
		
		
		
		
	}

	
	
	

}
