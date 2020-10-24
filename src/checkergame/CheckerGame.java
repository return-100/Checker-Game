package checkergame;

import java.io.IOException;
import java.net.ServerSocket;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckerGame extends Application
{
    public static void main (String[] args) throws Exception
    {
        launch (args);
    }

    public void start (Stage primaryStage) throws Exception
    {
        Text server=new Text ("CHECKER GAME\n         SERVER");
        server.setStyle ("-fx-font: 70 rockwell;");
        server.setFill (Color.rgb (104, 83, 54));
        server.setLayoutX (20);
        server.setLayoutY (150);

        Button enter=new Button ("Enter");
        enter.setStyle ("-fx-base: #131a23;");
        enter.setLayoutX (280);
        enter.setLayoutY (300);

        Group group=new Group (server, enter);
        Scene scene=new Scene (group, 600, 600);
        scene.setFill (Color.rgb (19, 23, 30));

        enter.setOnAction (e->
        {
            Text txt=new Text ("WELCOME TO CHECKER GAME SERVER");
            txt.setStyle ("-fx-font: 30 rockwell;");
            txt.setFill (Color.rgb (104, 83, 54));
            txt.setLayoutX (10);
            txt.setLayoutY (50);

            Label online=new Label ("Online Player List");
            online.setLayoutX (65);
            online.setLayoutY (100);
            online.setPrefSize (200, 1);
            online.setTextFill (Color.WHITE);
            online.setStyle ("-fx-font: 20 rockwell;");

            Label onlinematch=new Label ("Current Match");
            onlinematch.setLayoutX (385);
            onlinematch.setLayoutY (100);
            onlinematch.setPrefSize (200, 1);
            onlinematch.setTextFill (Color.WHITE);
            onlinematch.setStyle ("-fx-font: 20 rockwell;");

            TextArea showplayer=new TextArea ();
            showplayer.setLayoutX (25);
            showplayer.setLayoutY (150);
            showplayer.setPrefSize (250, 400);

            TextArea showmatch=new TextArea ();
            showmatch.setLayoutX (325);
            showmatch.setLayoutY (150);
            showmatch.setPrefSize (250, 400);

            Group curgroup=new Group (online, onlinematch, showplayer, showmatch, txt);
            Scene curscene=new Scene (curgroup, 600, 600);
            curscene.setFill (Color.rgb (19, 23, 30));

            try
            {
                ServerSocket serverSocket=new ServerSocket (8080);
                new ConnectionThread (serverSocket, showplayer, showmatch);
            }
            catch (IOException ex)
            {

            }

            primaryStage.setScene (curscene);
        });

        primaryStage.setResizable (false);
        primaryStage.setScene (scene);
        primaryStage.show ();
    }
}
