package checkergame;

import java.io.Serializable;
import javafx.scene.shape.*;

public class gameInfo implements Serializable
{
    public Rectangle rect;
    public int circleIdx;
    public int playerCheck;
    public int check;
    public int isX;
    public int isY;
    public boolean isKing;

    gameInfo ()
    {
        rect = new Rectangle ();
        circleIdx = 100;
        isX = 100;
        isY = 100;
        playerCheck = 0;
        check = 0;
        isKing = false;
    }
}
