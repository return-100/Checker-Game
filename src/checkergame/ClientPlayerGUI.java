package checkergame;

import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientPlayerGUI extends Application
{
    Group group;
    Scene scene;
    gameInfo[][] info=new gameInfo[8][8];
    int id;
    boolean isTurn=true;

    public static void main (String[] args)
    {
        launch (args);
    }

    public void start (Stage primaryStage) throws Exception
    {
        Reflection reflection=new Reflection ();
        reflection.setFraction (0.7);

        Text checker=new Text ("CHECKER  GAME");
        checker.setStyle ("-fx-font: 70 rockwell;");
        checker.setFill (Color.rgb (104, 83, 54));
        checker.setLayoutX (10);
        checker.setLayoutY (100);
        checker.setEffect (reflection);

        Text game=new Text ("Player Mode");
        game.setStyle ("-fx-font: 40 rockwell;");
        game.setFill (Color.rgb (104, 83, 54));
        game.setLayoutX (200);
        game.setLayoutY (300);

        Button enter=new Button ("Enter");
        enter.setStyle ("-fx-base: #131a23;");
        enter.setLayoutX (270);
        enter.setLayoutY (490);

        TextField ip=new TextField ();
        Label ipLabel=new Label ("Enter IP :");
        ip.setText ("127.0.0.1");
        ip.setLayoutX (270);
        ip.setLayoutY (400);
        ip.setPrefSize (100, 10);
        ipLabel.setLayoutX (180);
        ipLabel.setLayoutY (400);
        ipLabel.setPrefSize (80, 10);
        ipLabel.setTextFill (Color.WHITE);

        TextField port=new TextField ();
        Label portLabel=new Label ("Enter Port :");
        port.setText ("8080");
        port.setLayoutX (270);
        port.setLayoutY (430);
        port.setPrefSize (100, 10);
        portLabel.setLayoutX (180);
        portLabel.setLayoutY (430);
        portLabel.setPrefSize (80, 10);
        portLabel.setTextFill (Color.WHITE);

        TextField name=new TextField ();
        Label nameLabel=new Label ("Enter Name :");
        name.setLayoutX (270);
        name.setLayoutY (460);
        name.setPrefSize (100, 10);
        nameLabel.setLayoutX (180);
        nameLabel.setLayoutY (460);
        nameLabel.setPrefSize (80, 10);
        nameLabel.setTextFill (Color.WHITE);

        Group tgp=new Group (enter, ip, ipLabel, port, portLabel, name, nameLabel, checker, game);
        Scene start=new Scene (tgp, 600, 600);
        start.setFill (Color.rgb (19, 23, 30));

        enter.setOnAction (f->
        {
            Alert msg=new Alert (Alert.AlertType.INFORMATION);
            msg.setTitle ("Message");
            msg.setContentText ("Waiting for connection........");
            msg.showAndWait ();
            primaryStage.close ();
            GameUtil util=new GameUtil (ip.getText (), Integer.parseInt (port.getText ()));

            id=(int) util.read ();
            primaryStage.show ();

            Label score=new Label ();
            score.setText ("Score :");
            score.setLayoutX (100);
            score.setLayoutY (5);
            score.setPrefSize (100, 1);
            score.setTextFill (Color.WHITE);
            score.setStyle ("-fx-font: 20 rockwell;");

            Label score1=new Label ();
            score1.setText ("Player 1 : "+12);
            score1.setLayoutX (100);
            score1.setLayoutY (30);
            score1.setPrefSize (125, 1);
            score1.setTextFill (Color.WHITE);
            score1.setStyle ("-fx-font: 20 rockwell;");

            Label score2=new Label ();
            score2.setText ("Player 2 : "+12);
            score2.setLayoutX (100);
            score2.setLayoutY (55);
            score2.setPrefSize (125, 1);
            score2.setTextFill (Color.WHITE);
            score2.setStyle ("-fx-font: 20 rockwell;");

            Label turn=new Label ();
            turn.setLayoutX (250);
            turn.setLayoutY (5);
            turn.setPrefSize (150, 5);
            turn.setText ("Turn : Player 1");
            turn.setTextFill (Color.WHITE);
            turn.setStyle ("-fx-font: 20 rockwell;");

            Label playerName=new Label ();
            playerName.setLayoutX (215);
            playerName.setLayoutY (530);
            playerName.setText ("Player "+id+" : "+name.getText ());
            playerName.setPrefSize (175, 1);
            playerName.setTextFill (Color.WHITE);
            playerName.setStyle ("-fx-font: 20 rockwell;");

            group=new Group (score, score1, score2, turn, playerName);
            gameControl ctrl=new gameControl (turn, score1, score2);
            GridPane grid=new GridPane ();
            ctrl.Board (group);
            ctrl.setId (id);
            scene=new Scene (group, 600, 600);
            scene.setFill (Color.rgb (19, 23, 30));
            grid=ctrl.getGrid ();
            TurnThread pawnTurn=new TurnThread (util, id, ctrl);

            grid.setOnMouseClicked ((MouseEvent e)->
            {
                int[] arr=new int[2];
                double x=e.getX (), y=e.getY ();
                arr=ctrl.getGridRowColumn (x, y);
                System.out.println (arr[0]+" "+arr[1]);
                boolean isChange=false;
                if (pawnTurn.getTurn ())
                {
                    isChange=ctrl.start (arr[0], arr[1]);
                }
                if (isChange)
                {
                    int cnt1=0, cnt2=0;
                    pawnTurn.setTurn (false);
                    info=ctrl.getInfo ();

                    for (int i=0; i<8; ++i)
                    {
                        for (int j=0; j<8; ++j)
                        {
                            util.write (info[i][j].circleIdx);
                            util.write (info[i][j].isX);
                            util.write (info[i][j].isY);
                            util.write (info[i][j].playerCheck);
                            util.write (info[i][j].check);
                            util.write (info[i][j].isKing);
                        }
                    }

                    for (int i=0; i<8; ++i)
                    {
                        for (int j=0; j<8; ++j)
                        {
                            if (info[i][j].playerCheck==1)
                            {
                                ++cnt1;
                            }
                            else if (info[i][j].playerCheck==2)
                            {
                                ++cnt2;
                            }
                        }
                    }

                    score1.setText ("Player 1 : "+cnt1);
                    score2.setText ("Player 2 : "+cnt2);

                    if (id==1)
                    {
                        turn.setText ("Turn : Player "+2);
                    }
                    else
                    {
                        turn.setText ("Turn : Player "+1);
                    }
                    if (cnt1==0||cnt2==0)
                    {
                        Alert alert=new Alert (Alert.AlertType.INFORMATION);
                        alert.setTitle ("Match Result");
                        if (id==1)
                        {
                            if (cnt1==0)
                            {
                                alert.setContentText ("OOPS! You Have Lost The Match :(");
                            }
                            else
                            {
                                alert.setContentText ("CONGRATULATIONS! You Have WON The Match :)");
                            }
                        }
                        else
                        {
                            if (cnt1==0)
                            {
                                alert.setContentText ("CONGRATULATIONS! You Have WON The Match :)");
                            }
                            else
                            {
                                alert.setContentText ("CONGRATULATIONS! You Have WON The Match :)");
                            }
                        }
                        alert.showAndWait ();
                        exit ();
                    }
                }
            });
            primaryStage.setScene (scene);
        });

        primaryStage.setResizable (false);
        primaryStage.setScene (start);
        primaryStage.show ();
    }
}
