package checkergame;

import javafx.application.Platform;

public class TurnThread implements Runnable
{
    GameUtil util;
    gameControl ctrl;
    gameInfo[][] info=new gameInfo[8][8];
    boolean isTurn=false;
    Thread thread=new Thread (this);
    int id;

    TurnThread (GameUtil util, int id, gameControl ctrl)
    {
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                this.info[i][j]=new gameInfo ();
            }
        }
        this.ctrl=ctrl;
        this.util=util;
        this.id=id;
        thread.start ();
    }

    boolean getTurn ()
    {
        return isTurn;
    }

    void setTurn (boolean turn)
    {
        isTurn=turn;
    }

    public void run ()
    {
        while (true)
        {
            isTurn=(boolean) util.read ();
            if (!isTurn)
            {
                for (int i=0; i<8; ++i)
                {
                    for (int j=0; j<8; ++j)
                    {
                        info[i][j].circleIdx=(int) util.read ();
                        info[i][j].isX=(int) util.read ();
                        info[i][j].isY=(int) util.read ();
                        info[i][j].playerCheck=(int) util.read ();
                        info[i][j].check=(int) util.read ();
                        info[i][j].isKing=(boolean) util.read ();
                    }
                }
                Platform.runLater (()->
                {
                    ctrl.updateBoard (info);
                });
            }
        }

    }
}
