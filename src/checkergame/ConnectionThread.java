package checkergame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import javafx.scene.control.TextArea;

public class ConnectionThread implements Runnable
{
    Thread thread;
    ServerSocket serversocket;
    GameUtil preUtil=null;
    HashMap<GameUtil, GameUtil> map=new HashMap<> ();
    TextArea showplayer, showmatch;
    int turn=1, num=1;

    ConnectionThread (ServerSocket serversocket, TextArea showplayer, TextArea showmatch)
    {
        this.showmatch=showmatch;
        this.showplayer=showplayer;
        this.serversocket=serversocket;
        thread=new Thread (this);
        thread.start ();
    }

    public void run ()
    {
        while (true)
        {
            Socket socket=null;
            try
            {
                socket=serversocket.accept ();
            }
            catch (IOException ex)
            {

            }
            GameUtil util=new GameUtil (socket);

            if (turn==1)
            {
                showplayer.appendText ("Intruder "+num+" is now Online .....\n");
                preUtil=util;
                turn=2;
                ++num;
            }
            else
            {
                showplayer.appendText ("Intuder "+num+" is now Online .....\n");
                showmatch.appendText ("A match between Intruder "+(num-1)+" and Intruder "+num+"\n");
                map.put (preUtil, util);
                map.put (util, preUtil);
                turn=1;
                new GameManagerThread (preUtil, util, map);
                ++num;
            }
        }
    }
}
