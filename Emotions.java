
/**
 * Write a description of class Emotions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;

public class Emotions
{
    private static final Color c1 = new Color(0,0,255);//Blue
    private static final Color c2 = new Color(255,0,0);//Red
    private static final int[][] HURT = {{0,2,0},
                                         {0,2,0},
                                         {0,2,0},
                                         {0,2,0},
                                         {0,0,0},
                                         {0,2,0}};
    private static final int[][] CURIOUS = {{1,1,1},
                                            {1,0,1},
                                            {0,0,1},
                                            {0,1,1},
                                            {0,0,0},
                                            {0,1,0}};
    private int x;
    private int y;
    private int state = 1;
    private int [][] arr;
    private int pixSize;
    public Emotions(int x, int y, int size)
    {
        this.x = x;
        this.y = y;
        pixSize = size;
    }
    public void drawChar(Graphics g)
    {
        setState(state);
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[1].length; j++)
            {
                if(arr[i][j] == 1)
                {
                    g.setColor(c1);
                }else if(arr[i][j] == 2)
                {
                    g.setColor(c2);
                }
                if(arr[i][j] != 0)
                {
                    g.fillRect(x+j*pixSize,y+i*pixSize,pixSize,pixSize);
                }
            }
        }
    }
    public int getState()
    {
        return state;
    }
    public void setState(int z)
    {
        state = z;
        if(state < 0)
        {
            arr = CURIOUS;
        }else{
            arr = HURT;
        }
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setXY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
