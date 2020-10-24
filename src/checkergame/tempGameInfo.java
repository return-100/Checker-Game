package checkergame;

import java.io.Serializable;
import javafx.scene.shape.*;

public class tempGameInfo implements Serializable
{
    public Rectangle rect;
    public int circleIdx;
    public int playerCheck;
    public int check;
    public int isX;
    public int isY;
    public boolean isKing;

    tempGameInfo ()
    {
        rect=new Rectangle ();
        circleIdx=100;
        isX=100;
        isY=100;
        playerCheck=0;
        check=0;
        isKing=false;
    }

    public void setValue (gameInfo[][] info, tempGameInfo[][] temp)
    {
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                temp[i][j].circleIdx=info[i][j].circleIdx;
                temp[i][j].isX=info[i][j].isX;
                temp[i][j].isY=info[i][j].isY;
                temp[i][j].playerCheck=info[i][j].playerCheck;
                temp[i][j].check=info[i][j].check;
                temp[i][j].isKing=info[i][j].isKing;
            }
        }
    }

    public gameInfo[][] resetValue (gameInfo[][] info, tempGameInfo[][] temp)
    {
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                info[i][j].circleIdx=temp[i][j].circleIdx;
                info[i][j].isX=temp[i][j].isX;
                info[i][j].isY=temp[i][j].isY;
                info[i][j].playerCheck=temp[i][j].playerCheck;
                info[i][j].check=temp[i][j].check;
                info[i][j].isKing=temp[i][j].isKing;
            }
        }
        return info;
    }
}
