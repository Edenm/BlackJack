package View;

import java.io.File;
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
	
	@FXML
	MediaView video;
	@FXML
	TextField txtnameLogin;
	@FXML
	 ImageView LoginPic;
	@FXML
	Button btnSkip;
	@FXML
	Button btnLogin;
	
	MediaPlayer mediaPlayer;
	

	public void initialize(URL location, ResourceBundle resources) {
		txtnameLogin.setVisible(false);
		btnLogin.setVisible(false);
		playVideo();
		
		
		
	}
	
	private void playVideo()
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
		catch(Exception e){e.printStackTrace();}
	       
	    }
	
	private void videosTOP()
	{
		txtnameLogin.setVisible(true);
		btnLogin.setVisible(true);
		video.setVisible(false);
		mediaPlayer.stop();
        btnSkip.setVisible(false);
   
	}
	@FXML
	public void clickSkip()
	{
		videosTOP();
        
	}
	@FXML
	public void ClickLogin(ActionEvent event)
	{
		if(txtnameLogin.getText().equals(""))
		{
			txtnameLogin.setStyle("-fx-background-color: linear-gradient(#ffd65b, #e68400)");
			return;
		}
		ViewLogic.startGame(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
		
		
	}

	
	
	

}
