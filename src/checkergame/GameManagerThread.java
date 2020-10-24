package checkergame;

import java.util.HashMap;

public class GameManagerThread implements Runnable
{
    int turn=1;
    Thread thread;
    GameUtil first;
    GameUtil second;
    HashMap<GameUtil, GameUtil> map=new HashMap<> ();
    gameInfo[][] info=new gameInfo[8][8];

    GameManagerThread (GameUtil first, GameUtil second, HashMap map)
    {
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                info[i][j]=new gameInfo ();
            }
        }
        this.first=first;
        this.second=second;
        this.map=map;
        thread=new Thread (this);
        thread.start ();
    }

    public void run ()
    {
        first.write (1);
        second.write (2);
        while (true)
        {
            if (turn==1)
            {
                first.write (true);
                second.write (false);
                for (int i=0; i<8; ++i)
                {
                    for (int j=0; j<8; ++j)
                    {
                        info[i][j].circleIdx=(int) first.read ();
                        info[i][j].isX=(int) first.read ();
                        info[i][j].isY=(int) first.read ();
                        info[i][j].playerCheck=(int) first.read ();
                        info[i][j].check=(int) first.read ();
                        info[i][j].isKing=(boolean) first.read ();
                    }
                }
                for (int i=0; i<8; ++i)
                {
                    for (int j=0; j<8; ++j)
                    {
                        second.write (info[i][j].circleIdx);
                        second.write (info[i][j].isX);
                        second.write (info[i][j].isY);
                        second.write (info[i][j].playerCheck);
                        second.write (info[i][j].check);
                        second.write (info[i][j].isKing);
                    }
                }
                turn=2;
            }
            else if (turn==2)
            {
                first.write (false);
                second.write (true);
                for (int i=0; i<8; ++i)
                {
                    for (int j=0; j<8; ++j)
                    {
                        info[i][j].circleIdx=(int) second.read ();
                        info[i][j].isX=(int) second.read ();
                        info[i][j].isY=(int) second.read ();
                        info[i][j].playerCheck=(int) second.read ();
                        info[i][j].check=(int) second.read ();
                        info[i][j].isKing=(boolean) second.read ();
                    }
                }
                for (int i=0; i<8; ++i)
                {
                    for (int j=0; j<8; ++j)
                    {
                        first.write (info[i][j].circleIdx);
                        first.write (info[i][j].isX);
                        first.write (info[i][j].isY);
                        first.write (info[i][j].playerCheck);
                        first.write (info[i][j].check);
                        first.write (info[i][j].isKing);
                    }
                }
                turn=1;
            }
        }
    }
}
