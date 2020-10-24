package checkergame;

import java.io.*;
import java.net.*;

public class GameUtil implements Serializable
{
    Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    GameUtil (String ip, int port)
    {
        try
        {
            this.socket=new Socket (ip, port);
            oos=new ObjectOutputStream (socket.getOutputStream ());
            ois=new ObjectInputStream (socket.getInputStream ());
        }
        catch (IOException ex)
        {

        }
    }

    GameUtil (Socket s)
    {
        try
        {
            this.socket=s;
            oos=new ObjectOutputStream (socket.getOutputStream ());
            ois=new ObjectInputStream (socket.getInputStream ());
        }
        catch (IOException ex)
        {

        }
    }

    public Object read ()
    {
        Object ob=null;

        try
        {
            ob=ois.readObject ();
        }
        catch (IOException|ClassNotFoundException ex)
        {

        }
        return ob;
    }

    public void write (Object ob)
    {
        try
        {
            oos.writeObject (ob);
        }
        catch (IOException ex)
        {

        }
    }
}
