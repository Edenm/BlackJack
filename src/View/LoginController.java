package View;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	@FXML
	MediaView video;
	MediaPlayer mediaPlayer;
	

	public void initialize(URL location, ResourceBundle resources) {
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
		}
		catch(Exception e){e.printStackTrace();}
	       
	    }
	@FXML
	public void ClickLogin(ActionEvent event)
	{
		ViewLogic.startGame(new Stage());
		((Node) event.getSource()).getScene().getWindow().hide();
		mediaPlayer.stop();
		
	}
	
	

}
