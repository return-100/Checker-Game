package checkergame;

import java.io.File;
import java.io.Serializable;
import static javafx.application.Platform.exit;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;

public class gameControl implements Serializable
{
    public gameInfo[][] info=new gameInfo[8][8];
    public tempGameInfo[][] preInfo=new tempGameInfo[8][8];
    public tempGameInfo tempInfo=new tempGameInfo ();
    public Circle[] black_circle=new Circle[12];
    public Circle[] choco_circle=new Circle[12];
    GridPane grid=new GridPane ();
    int id;
    boolean isKill=false;
    GameUtil util;
    Label turn, score1, score2;

    gameControl (Label turn, Label score1, Label score2)
    {
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                info[i][j]=new gameInfo ();
                preInfo[i][j]=new tempGameInfo ();
            }
        }

        this.turn=turn;
        this.score1=score1;
        this.score2=score2;
    }

    public GridPane getGrid ()
    {
        return grid;
    }

    public gameInfo[][] getInfo ()
    {
        return info;
    }

    public int[] getGridRowColumn (double x, double y)
    {
        int[] arr=new int[2];
        boolean temp=false;
        for (int i=0; i<8; i++)
        {
            for (int j=0; j<8; ++j)
            {
                if (i==0)
                {
                    if (j==0)
                    {
                        double p=info[i][j].rect.getLayoutX (), q=info[i][j].rect.getLayoutY ();
                        double r=info[i][j+1].rect.getLayoutX (), s=info[i+1][j].rect.getLayoutY ();
                        if (p<=x&&q<=y&&r>x&&s>y)
                        {
                            arr[0]=i;
                            arr[1]=j;
                            temp=true;
                            break;
                        }
                    }
                    else if (j==7)
                    {
                        double p=info[i][j].rect.getLayoutX (), q=info[i][j].rect.getLayoutY ();
                        double r=info[i][j-1].rect.getLayoutX (), s=info[i+1][j].rect.getLayoutY ();
                        if (p<=x&&q<=y&&r<x&&s>y)
                        {
                            arr[0]=i;
                            arr[1]=j;
                            temp=true;
                            break;
                        }
                    }
                    else
                    {
                        double p=info[i][j].rect.getLayoutX (), q=info[i][j].rect.getLayoutY ();
                        double r=info[i][j-1].rect.getLayoutX (), s=info[i+1][j].rect.getLayoutY (), t=info[i][j+1].rect.getLayoutX ();
                        if (p<=x&&q<=y&&r<x&&t>x&&s>y)
                        {
                            arr[0]=i;
                            arr[1]=j;
                            temp=true;
                            break;
                        }
                    }
                }
                else if (i>0&&i<7)
                {
                    if (j==0)
                    {
                        double p=info[i][j].rect.getLayoutX (), q=info[i][j].rect.getLayoutY ();
                        double r=info[i][j+1].rect.getLayoutX (), s=info[i-1][j].rect.getLayoutY (), t=info[i+1][j].rect.getLayoutY ();
                        if (p<=x&&q<=y&&r>x&&t>y&&s<y)
                        {
                            arr[0]=i;
                            arr[1]=j;
                            temp=true;
                            break;
                        }
                    }
                    else if (j==7)
                    {
                        double p=info[i][j].rect.getLayoutX (), q=info[i][j].rect.getLayoutY ();
                        double r=info[i][j-1].rect.getLayoutX (), s=info[i-1][j].rect.getLayoutY (), t=info[i+1][j].rect.getLayoutY ();
                        if (p<=x&&q<=y&&r<x&&t>y&&s<y)
                        {
                            arr[0]=i;
                            arr[1]=j;
                            temp=true;
                            break;
                        }
                    }
                    else
                    {
                        double p=info[i][j].rect.getLayoutX (), q=info[i][j].rect.getLayoutY ();
                        double r=info[i][j-1].rect.getLayoutX (), s=info[i+1][j].rect.getLayoutY ();
                        double t=info[i][j+1].rect.getLayoutX (), u=info[i-1][j].rect.getLayoutY ();
                        if (p<=x&&q<=y&&r<x&&t>x&&s>y&&u<y)
                        {
                            arr[0]=i;
                            arr[1]=j;
                            temp=true;
                            break;
                        }
                    }
                }
                else if (i==7)
                {
                    if (j==0)
                    {
                        double p=info[i][j].rect.getLayoutX (), q=info[i][j].rect.getLayoutY ();
                        double r=info[i][j+1].rect.getLayoutX (), s=info[i-1][j].rect.getLayoutY ();
                        if (p<=x&&q<=y&&r>x&&s<y)
                        {
                            arr[0]=i;
                            arr[1]=j;
                            temp=true;
                            break;
                        }
                    }
                    else if (j==7)
                    {
                        double p=info[i][j].rect.getLayoutX (), q=info[i][j].rect.getLayoutY ();
                        double r=info[i][j-1].rect.getLayoutX (), s=info[i-1][j].rect.getLayoutY ();
                        if (p<=x&&q<=y&&r<x&&s<y)
                        {
                            arr[0]=i;
                            arr[1]=j;
                            temp=true;
                            break;
                        }
                    }
                    else
                    {
                        double p=info[i][j].rect.getLayoutX (), q=info[i][j].rect.getLayoutY ();
                        double r=info[i][j-1].rect.getLayoutX (), s=info[i-1][j].rect.getLayoutY (), t=info[i][j+1].rect.getLayoutX ();
                        if (p<=x&&q<=y&&r<x&&t>x&&s<y)
                        {
                            arr[0]=i;
                            arr[1]=j;
                            temp=true;
                            break;
                        }
                    }
                }
            }
            if (temp)
            {
                break;
            }
        }
        return arr;
    }

    public void setId (int playerId)
    {
        id=playerId;
    }

    public int getId ()
    {
        return id;
    }

    public void Board (Group group)
    {
        DropShadow dropShadow=new DropShadow ();
        dropShadow.setRadius (5.0);
        dropShadow.setOffsetX (6.0);
        dropShadow.setOffsetY (4.0);

        grid.setVgap (.5);
        grid.setHgap (.5);
        int choco_idx=0, black_idx=0;
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                info[i][j].rect=new Rectangle ();
                info[i][j].rect.setHeight (50);
                info[i][j].rect.setWidth (50);
                info[i][j].rect.setFill (Color.BURLYWOOD);

                Light.Point light=new Light.Point ();
                light.setX ((info[i][j].rect.getLayoutX ()+50)/2);
                light.setY ((info[i][j].rect.getLayoutY ()+50)/2);
                light.setZ (100);

                Lighting lighting=new Lighting ();
                lighting.setLight (light);
                lighting.setSurfaceScale (2.5);

                info[i][j].rect.setEffect (lighting);
                grid.add (info[i][j].rect, j, i);
                if ((i+j)%2==0&&i<3&&choco_idx<12)
                {
                    choco_circle[choco_idx]=new Circle ();
                    choco_circle[choco_idx].setRadius (20);
                    choco_circle[choco_idx].setFill (Color.rgb (123, 63, 50));
                    choco_circle[choco_idx].setEffect (dropShadow);
                    grid.add (choco_circle[choco_idx], j, i);
                    GridPane.setHalignment (choco_circle[choco_idx], HPos.CENTER);
                    info[i][j].check=1;
                    info[i][j].circleIdx=choco_idx;
                    info[i][j].playerCheck=1;
                    ++choco_idx;
                }
                if ((i+j)%2==0&&i>4&&black_idx<12)
                {
                    black_circle[black_idx]=new Circle ();
                    black_circle[black_idx].setRadius (20);
                    black_circle[black_idx].setFill (Color.BLACK);
                    black_circle[black_idx].setEffect (dropShadow);
                    grid.add (black_circle[black_idx], j, i);
                    GridPane.setHalignment (black_circle[black_idx], HPos.CENTER);
                    info[i][j].check=1;
                    info[i][j].circleIdx=black_idx;
                    info[i][j].playerCheck=2;
                    ++black_idx;
                }
            }
        }
        grid.setLayoutX (100);
        grid.setLayoutY (100);
        group.getChildren ().add (grid);
    }

    public void updateBoard (gameInfo[][] data)
    {
        for (int idx=0; idx<2; ++idx)
        {
            for (int i=0; i<8; ++i)
            {
                for (int j=0; j<8; ++j)
                {
                    if (data[i][j].check==0&&info[i][j].check==1&&idx==0)
                    {
                        info[i][j].isX=data[i][j].isX;
                        info[i][j].isY=data[i][j].isY;
                        info[i][j].isKing=data[i][j].isKing;
                        if (info[i][j].playerCheck==1)
                        {
                            grid.getChildren ().remove (choco_circle[info[i][j].circleIdx]);
                        }
                        else
                        {
                            grid.getChildren ().remove (black_circle[info[i][j].circleIdx]);
                        }
                        info[i][j].circleIdx=data[i][j].circleIdx;
                        info[i][j].check=data[i][j].check;
                        info[i][j].playerCheck=data[i][j].playerCheck;
                    }
                    else if (data[i][j].check==1&&info[i][j].check==0&&idx==1)
                    {
                        info[i][j].isX=data[i][j].isX;
                        info[i][j].isY=data[i][j].isY;
                        info[i][j].isKing=data[i][j].isKing;
                        if (data[i][j].playerCheck==1)
                        {
                            grid.add (choco_circle[data[i][j].circleIdx], j, i);
                        }
                        else
                        {
                            grid.add (black_circle[data[i][j].circleIdx], j, i);
                        }
                        info[i][j].circleIdx=data[i][j].circleIdx;
                        info[i][j].check=data[i][j].check;
                        info[i][j].playerCheck=data[i][j].playerCheck;
                    }
                    else if ((data[i][j].playerCheck==2&&info[i][j].playerCheck==1&&idx==1)||(data[i][j].playerCheck==1&&info[i][j].playerCheck==2&&idx==1))
                    {
                        info[i][j].isX=data[i][j].isX;
                        info[i][j].isY=data[i][j].isY;
                        info[i][j].isKing=data[i][j].isKing;
                        if (data[i][j].playerCheck==2)
                        {
                            grid.getChildren ().remove (choco_circle[info[i][j].circleIdx]);
                            grid.add (black_circle[data[i][j].circleIdx], j, i);
                        }
                        else
                        {
                            grid.getChildren ().remove (black_circle[info[i][j].circleIdx]);
                            grid.add (choco_circle[data[i][j].circleIdx], j, i);
                        }
                        info[i][j].circleIdx=data[i][j].circleIdx;
                        info[i][j].check=data[i][j].check;
                        info[i][j].playerCheck=data[i][j].playerCheck;
                    }
                }
            }
        }

        int cnt1=0, cnt2=0;
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
                    alert.setContentText ("OOPS! You Have Lost The Match :(");
                }
            }
            alert.showAndWait ();
            exit ();
        }
        turn.setText ("Turn : Player "+id);
        kingCheck ();
        kingImage ();
        isKillable ();
    }

    public int[][] getDiagonal (int row, int col, boolean isTrue)
    {
        int[][] arr=new int[2][4];
        for (int i=0; i<2; ++i)
        {
            for (int j=0; j<4; ++j)
            {
                arr[i][j]=-1;
            }
        }

        if (info[row][col].playerCheck==1||isTrue)
        {
            if (row+1<8&&col+1<8)
            {
                arr[0][0]=row+1;
                arr[0][1]=col+1;
            }
            if (row+1<8&&col-1>=0)
            {
                arr[0][2]=row+1;
                arr[0][3]=col-1;
            }
        }

        if (info[row][col].playerCheck==2||isTrue)
        {
            if (row-1>=0&&col-1>=0)
            {
                arr[1][2]=row-1;
                arr[1][3]=col-1;
            }
            if (row-1>=0&&col+1<8)
            {
                arr[1][0]=row-1;
                arr[1][1]=col+1;
            }
        }
        return arr;
    }

    public void dfs_pawn (int x, int y, int preX, int preY)
    {
        int[][] arr=new int[2][4];
        arr=getDiagonal (x, y, true);

        if (info[x][y].check==3)
        {
            for (int i=0; i<8; ++i)
            {
                for (int j=0; j<8; ++j)
                {
                    if (info[i][j].check==2)
                    {
                        info[i][j].check=0;
                    }
                }
            }
            int p=arr[0][2], q=arr[0][3];
            int r=arr[0][0], s=arr[0][1];
            int t=arr[1][2], u=arr[1][3];
            int v=arr[1][0], w=arr[1][1];

            if (p!=-1)
            {
                int px=p+(p-x), qy=q+(q-y);
                if (px>=0&&px<8&&qy>=0&&qy<8&&info[p][q].playerCheck!=id&&info[p][q].playerCheck!=0)
                {
                    if (info[px][qy].check==0)
                    {
                        changeColor (p, q, x, y, 2, true);
                        dfs_pawn (px, qy, p, q);
                    }
                }
            }
            if (r!=-1)
            {
                int rx=r+(r-x), sy=s+(s-y);
                if (rx>=0&&rx<8&&sy>=0&&sy<8&&info[r][s].playerCheck!=id&&info[r][s].playerCheck!=0)
                {
                    if (info[rx][sy].check==0)
                    {
                        changeColor (r, s, x, y, 2, true);
                        dfs_pawn (rx, sy, r, s);
                    }
                }
            }
            if (t!=-1)
            {
                int tx=t+(t-x), uy=u+(u-y);
                if (tx>=0&&tx<8&&uy>=0&&uy<8&&info[t][u].playerCheck!=id&&info[t][u].playerCheck!=0)
                {
                    if (info[tx][uy].check==0)
                    {
                        changeColor (t, u, x, y, 2, true);
                        dfs_pawn (tx, uy, t, u);
                    }
                }
            }
            if (v!=-1)
            {
                int vx=v+(v-x), wy=w+(w-y);
                if (vx>=0&&vx<8&&wy>=0&&wy<8&&info[v][w].playerCheck!=id&&info[v][w].playerCheck!=0)
                {
                    if (info[vx][wy].check==0)
                    {
                        changeColor (v, w, x, y, 2, true);
                        dfs_pawn (vx, wy, v, w);
                    }
                }
            }
        }

        else if (info[x][y].check==0)
        {
            int cnt=0;
            int a=arr[0][0], b=arr[0][1], c=arr[0][2], d=arr[0][3];
            int e=arr[1][0], f=arr[1][1], g=arr[1][2], h=arr[1][3];
            if (a!=-1&&a>0&&b>0&&a<7&&b<7&&((a!=preX&&b==preY)||(a==preX&&b!=preY)||(a!=preX&&b!=preY)))
            {
                int p=2*arr[0][0]-x, q=2*arr[0][1]-y;
                if (info[a][b].playerCheck!=id&&info[a][b].check==1&&info[p][q].check==0)
                {
                    changeColor (a, b, x, y, 2, true);
                    changeColor (x, y, preX, preY, 2, true);
                    dfs_pawn (p, q, a, b);
                }
                else
                {
                    ++cnt;
                }
            }
            else
            {
                ++cnt;
            }
            if (c!=-1&&c>0&&d>0&&c<7&&d<7&&((c!=preX&&d==preY)||(c==preX&&d!=preY)||(c!=preX&&d!=preY)))
            {
                int p=2*arr[0][2]-x, q=2*arr[0][3]-y;
                if (info[c][d].playerCheck!=id&&info[c][d].check==1&&info[p][q].check==0)
                {
                    changeColor (c, d, x, y, 2, true);
                    changeColor (x, y, preX, preY, 2, true);
                    dfs_pawn (p, q, c, d);
                }
                else
                {
                    ++cnt;
                }
            }
            else
            {
                ++cnt;
            }
            if (e!=-1&&e>0&&f>0&&e<7&&f<7&&((e!=preX&&f==preY)||(e==preX||f!=preY)||(e!=preX&&f!=preY)))
            {
                int p=2*arr[1][0]-x, q=2*arr[1][1]-y;
                if (info[e][f].playerCheck!=id&&info[e][f].check==1&&info[p][q].check==0)
                {
                    changeColor (e, f, x, y, 2, true);
                    changeColor (x, y, preX, preY, 2, true);
                    dfs_pawn (p, q, e, f);
                }
                else
                {
                    ++cnt;
                }
            }
            else
            {
                ++cnt;
            }
            if (g!=-1&&g>0&&h>0&&g<7&&h<7&&((g!=preX&&h==preY)||(g==preX&&h!=preY)||(g!=preX&&h!=preY)))
            {
                int p=2*arr[1][2]-x, q=2*arr[1][3]-y;
                if (info[g][h].playerCheck!=id&&info[g][h].check==1&&info[p][q].check==0)
                {
                    changeColor (g, h, x, y, 2, true);
                    changeColor (x, y, preX, preY, 2, true);
                    dfs_pawn (p, q, g, h);
                }
                else
                {
                    ++cnt;
                }
            }
            else
            {
                ++cnt;
            }
            if (cnt==4)
            {
                changeColor (x, y, preX, preY, 1, true);
            }
        }
    }

    public void dfs_king (int x, int y, int preX, int preY)
    {
        int[][] arr=new int[2][4];
        arr=getDiagonal (x, y, true);

        if (!isKill)
        {
            info[x][y].isX=preX;
            info[x][y].isY=preY;
            for (int i=0; i<2; ++i)
            {
                for (int j=0; j<3; j+=2)
                {
                    int p=arr[i][j], q=arr[i][j+1];
                    int dx=p-x, dy=q-y;
                    if (p>=0&&q>=0&&p<=7&&q<=7)
                    {
                        while (info[p][q].check==0)
                        {
                            if (p>=0&&q>=0&&p<=7&&q<=7)
                            {
                                changeColor (p, q, x, y, 1, false);
                                p+=dx;
                                q+=dy;
                            }
                            if (p<0||q<0||p>7||q>7)
                            {
                                break;
                            }
                        }
                    }
                }
            }
        }

        else if (isKill)
        {
            info[x][y].isX=preX;
            info[x][y].isY=preY;
            for (int i=0; i<2; ++i)
            {
                for (int j=0; j<3; j+=2)
                {
                    boolean temp=false;
                    int p=arr[i][j], q=arr[i][j+1];
                    int dx=p-x, dy=q-y;
                    if (p>=0&&q>=0&&p<=7&&q<=7)
                    {
                        while (info[p][q].check==0)
                        {
                            if (p>0&&q>0&&p<7&&q<7)
                            {
                                p+=dx;
                                q+=dy;
                            }
                            else
                            {
                                break;
                            }
                        }
                        if (p>0&&q>0&&p<7&&q<7)
                        {
                            if (info[p][q].playerCheck!=id&&info[p][q].check==1&&info[p+dx][q+dy].check==0)
                            {
                                temp=true;
                            }
                        }
                    }
                    if (temp)
                    {
                        p=arr[i][j];
                        q=arr[i][j+1];
                        boolean isTrue=true;
                        while (p>0&&q>0&&p<7&&q<7)
                        {
                            if (info[p][q].check==0&&isTrue)
                            {
                                changeColor (p, q, p-dx, q-dy, 2, true);
                            }
                            else if (info[p][q].playerCheck!=id&&info[p][q].check==1&&info[p+dx][q+dy].check==0)
                            {
                                isTrue=false;
                                changeColor (p, q, p-dx, q-dy, 2, true);
                            }
                            else if (info[p][q].check==0&&info[p+dx][q+dy].playerCheck!=id&&info[p+dx][q+dy].check==1&&!isTrue)
                            {
                                changeColor (p, q, p-dx, q-dy, 2, true);
                            }
                            else
                            {
                                break;
                            }
                            p+=dx;
                            q+=dy;
                        }
                        changeColor (p, q, p-dx, q-dy, 1, true);
                    }
                }
            }
        }
    }

    public void kingCheck ()
    {
        for (int i=0; i<8; ++i)
        {
            if (info[0][i].playerCheck==2)
            {
                info[0][i].isKing=true;
            }
            if (info[7][i].playerCheck==1)
            {
                info[7][i].isKing=true;
            }
        }
    }

    public void isKillable ()
    {
        int[][] arr=new int[2][4];
        boolean temp;
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                arr=getDiagonal (i, j, true);
                temp=false;
                if (info[i][j].playerCheck==id)
                {
                    for (int k=0; k<2; ++k)
                    {
                        for (int x=0; x<3; x+=2)
                        {
                            if (arr[k][x]!=-1&&!info[i][j].isKing)
                            {
                                int p=arr[k][x], q=arr[k][x+1];
                                int px=p+(p-i), qy=q+(q-j);
                                if (info[p][q].check==1&&info[p][q].playerCheck!=id&&px>=0&&qy>=0&&px<8&&qy<8)
                                {
                                    if (info[px][qy].check==0)
                                    {
                                        System.out.println ("works");
                                        temp=true;
                                    }
                                }
                            }

                            else if (arr[k][x]!=-1&&info[i][j].isKing)
                            {
                                int p=arr[k][x], q=arr[k][x+1];
                                int dx=p-i, dy=q-j;
                                if (p>0&&q>0&&p<7&&q<7)
                                {
                                    while (info[p][q].check==0)
                                    {
                                        if (p>0&&q>0&&p<7&&q<7)
                                        {
                                            p+=dx;
                                            q+=dy;
                                        }
                                        else
                                        {
                                            break;
                                        }
                                    }
                                    if (p>0&&q>0&&p<7&&q<7)
                                    {
                                        if (info[p][q].playerCheck!=id&&info[p][q].check!=0&&info[p+dx][q+dy].check==0)
                                        {
                                            temp=true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (temp)
                {
                    info[i][j].rect.setFill (Color.YELLOW);
                    info[i][j].check=3;
                    isKill=true;
                }
            }
        }
    }

    public void changeColor (int row, int col, int preX, int preY, int colorId, boolean isTrue)
    {
        if ((row!=-1&&info[row][col].check==0)||isTrue)
        {
            if (colorId==1)
            {
                info[row][col].rect.setFill (Color.GREEN);
            }
            else if (colorId==2)
            {
                info[row][col].rect.setFill (Color.STEELBLUE);
            }
            info[row][col].check=2;
            info[row][col].isX=preX;
            info[row][col].isY=preY;
        }
    }

    public void isReachable (int row, int col)
    {
        if (!isKill)
        {
            int[][] arr=new int[2][4];
            arr=getDiagonal (row, col, false);
            if (info[row][col].isKing)
            {
                dfs_king (row, col, row, col);
            }
            else
            {
                for (int i=0; i<2; ++i)
                {
                    changeColor (arr[i][0], arr[i][1], row, col, 1, false);
                    changeColor (arr[i][2], arr[i][3], row, col, 1, false);
                }
            }
        }

        else if (isKill&&info[row][col].check==3)
        {
            if (info[row][col].isKing)
            {
                dfs_king (row, col, row, col);
            }
            else
            {
                dfs_pawn (row, col, row, col);
            }
        }
    }

    public void setColor ()
    {
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                if (info[i][j].check==2&&!isKill&&info[i][j].playerCheck==0)
                {
                    info[i][j].check=0;
                    info[i][j].rect.setFill (Color.BURLYWOOD);
                }
                else if (info[i][j].check==2&&!isKill&&info[i][j].playerCheck!=0)
                {
                    info[i][j].check=1;
                    info[i][j].rect.setFill (Color.BURLYWOOD);
                    int idx=info[i][j].circleIdx;
                    if (id==1)
                    {
                        grid.getChildren ().remove (black_circle[idx]);
                        black_circle[idx].setFill (Color.BLACK);
                        grid.add (black_circle[idx], j, i);
                    }
                    else if (id==2)
                    {
                        grid.getChildren ().remove (choco_circle[idx]);
                        choco_circle[idx].setFill (Color.rgb (123, 63, 50));
                        grid.add (choco_circle[idx], j, i);
                    }
                }
                else if (info[i][j].check==3&&!isKill)
                {
                    info[i][j].rect.setFill (Color.BURLYWOOD);
                }
                else if (info[i][j].check==4)
                {
                    info[i][j].check=0;
                    info[i][j].rect.setFill (Color.BURLYWOOD);
                }
            }
        }
    }

    public boolean changeChecker (int row, int col, boolean isSetColor)
    {
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                if (info[i][j].check==0&&info[i][j].isKing)
                {
                    info[i][j].isKing=false;
                }
            }
        }

        boolean check=false;
        if ((info[row][col].check==2&&!isKill)||isSetColor)
        {
            int p=info[row][col].isX, q=info[row][col].isY;
            int idx=info[p][q].circleIdx;
            info[p][q].check=0;
            info[row][col].check=1;
            info[row][col].rect.setFill (Color.BURLYWOOD);
            if (info[p][q].playerCheck==1||(isSetColor&&id==1))
            {
                grid.getChildren ().remove (choco_circle[idx]);
                info[row][col].circleIdx=idx;
                info[row][col].playerCheck=1;
                choco_circle[idx].setRadius (20);
                choco_circle[idx].setFill (Color.rgb (123, 63, 50));
                grid.add (choco_circle[idx], col, row);
                info[p][q].playerCheck=0;
                if (info[p][q].isKing)
                {
                    info[row][col].isKing=true;
                    info[p][q].isKing=false;
                }
                if (isSetColor)
                {
                    info[p][q].check=4;
                    setColor ();
                }
                return true;
            }

            if (info[p][q].playerCheck==2||(isSetColor&&id==2))
            {
                grid.getChildren ().remove (black_circle[idx]);
                info[row][col].circleIdx=idx;
                info[row][col].playerCheck=2;
                black_circle[idx].setRadius (20);
                black_circle[idx].setFill (Color.BLACK);
                grid.add (black_circle[idx], col, row);
                info[p][q].playerCheck=0;
                if (info[p][q].isKing)
                {
                    info[row][col].isKing=true;
                    info[p][q].isKing=false;
                }
                if (isSetColor)
                {
                    info[p][q].check=4;
                    setColor ();
                }
                return true;
            }
        }

        else if (isKill)
        {
            int x=row, y=col;
            while (info[row][col].check!=3&&info[row][col].check==2)
            {
                int p=info[row][col].isX, q=info[row][col].isY;
                int idx=info[p][q].circleIdx;
                if (id==1&&info[p][q].check!=3)
                {
                    if (idx!=100)
                    {
                        grid.getChildren ().remove (black_circle[idx]);
                    }
                    info[p][q].playerCheck=0;
                    check=true;
                }
                else if (id==2&&info[p][q].check!=3)
                {
                    if (idx!=100)
                    {
                        grid.getChildren ().remove (choco_circle[idx]);
                    }
                    info[p][q].playerCheck=0;
                    check=true;
                }
                row=p;
                col=q;
            }

            if (check)
            {
                info[x][y].isX=row;
                info[x][y].isY=col;
                info[x][y].circleIdx=info[row][col].circleIdx;
                String uriString=new File ("C:\\Users\\AL IMRAN\\Desktop\\CheckerGame\\src\\checkergame\\beep.mp3").toURI ().toString ();
                MediaPlayer player=new MediaPlayer (new Media (uriString));
                player.play ();
                isKill=false;
                changeChecker (x, y, true);
            }
        }
        return check;
    }

    public void isKillRefresh ()
    {
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                if (info[i][j].check==2)
                {
                    info[i][j].rect.setFill (Color.BURLYWOOD);
                }
            }
        }
    }

    public void kingImage ()
    {
        ImagePattern img_choco=new ImagePattern (new Image ("checkergame/crown_choco.png"));
        ImagePattern img_black=new ImagePattern (new Image ("checkergame/crown_black.png"));
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                if (info[i][j].isKing)
                {
                    if (info[i][j].playerCheck==2)
                    {
                        black_circle[info[i][j].circleIdx].setFill (img_black);
                    }
                    else if (info[i][j].playerCheck==1)
                    {
                        choco_circle[info[i][j].circleIdx].setFill (img_choco);
                    }
                }
            }
        }
    }

    public void printValue (String s)
    {
        System.out.println (s);
        for (int i=0; i<8; ++i)
        {
            for (int j=0; j<8; ++j)
            {
                System.out.print ("("+info[i][j].check+","+info[i][j].playerCheck+") ");
            }
            System.out.println ("");
        }
    }

    public boolean start (int x, int y)
    {
        if ((info[x][y].playerCheck==id||info[x][y].check==0||info[x][y].check==2))
        {
            if (isKill)
            {
                isKillRefresh ();
            }
            boolean temp=changeChecker (x, y, false);
            setColor ();
            if (!temp)
            {
                isReachable (x, y);
                return false;
            }
            else
            {
                kingCheck ();
                kingImage ();
                return true;
            }
        }
        else
        {
            setColor ();
        }
        isKillable ();
        return false;
    }
}
