
/**
 * Write a description of class Flashlight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;

public class Flashlight
{
    private static final Color c1 = new Color(0,0,255);//Blue
    private static final Color c2 = new Color(0,0,100);//Dark Blue
    private static final Color c3 = new Color(255,255,0);;//Yellow
    private static final int[][] SITTING = {{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                                            {0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0},
                                            {0,0,3,3,3,3,3,3,3,3,3,3,3,3,0,0},
                                            {0,0,0,3,3,3,3,3,3,3,3,3,3,0,0,0},
                                            {0,0,0,0,3,3,3,3,3,3,3,3,0,0,0,0},
                                            {0,0,0,0,0,3,3,3,3,3,3,0,0,0,0,0},
                                            {0,0,0,0,0,0,3,3,3,3,0,0,0,0,0,0},
                                            {0,0,0,0,0,2,2,2,2,2,2,0,0,0,0,0},
                                            {0,0,0,0,0,2,2,2,2,2,2,0,0,0,0,0},
                                            {0,0,0,0,0,0,2,1,1,2,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,2,1,1,2,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,2,1,1,2,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,2,2,2,2,0,0,0,0,0,0}};
    
    private static final int[][] HELD = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3},
                                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
                                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3},
                                         {0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3},
                                         {0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3},
                                         {0,0,0,0,2,2,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                                         {2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                                         {2,1,1,1,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                                         {2,1,1,1,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                                         {2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                                         {0,0,0,0,2,2,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
                                         {0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3},
                                         {0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3},
                                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3},
                                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3},
                                         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3}};
    
                                            
    private int battery = 300;
    private int state = 0;
    private int[][] arr;
    private int x,y;
    private int pixSize;
    private  boolean isFacingLeft = true;
    public Flashlight(int x, int y, int size)
    {
        this.x = x;
        this.y = y;
        this.pixSize = size;
    }
    public void drawChar(Graphics g)
    {
        setState(state);
        if(state != 0)
        {
           if(isFacingLeft == true)
           {
               drawCharReverse(g);
            }else{
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
                        }else if(arr[i][j] == 3)
                        {
                            g.setColor(c3);
                        }
                        if(arr[i][j] != 0)
                        {
                            g.fillRect(x+j*pixSize,y+i*pixSize,pixSize,pixSize);
                        }
                    }
                } 
            }   
        }
    }
    public void drawCharReverse(Graphics g)
    {
        setState(state);
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = arr[1].length-1; j >= 0; j--)
            {
                if(arr[i][j] == 1)
                {
                    g.setColor(c1);
                }else if(arr[i][j] == 2)
                {
                    g.setColor(c2);
                }else if(arr[i][j] == 3)
                {
                    g.setColor(c3);
                }
                if(arr[i][j] != 0)
                {
                    g.fillRect(x+((pixSize*16)-j*pixSize),y+i*pixSize,pixSize,pixSize);
                }
            }
        } 
    }
    public boolean getDir()
    {
        return isFacingLeft;
    }
    public void updateDir()
    {
        isFacingLeft = !isFacingLeft;
    }
    public void setDir(boolean dir)
    {
        isFacingLeft = dir;
    }
    public int getState()
    {
        return state;
    }
    public void setState(int x)
    {
        state = x;
        if(state == -1)
        {
            arr = HELD;
        }else{
            arr = SITTING;
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
    public int getBattery()
    {
        return battery;
    }
    public void replenish()
    {
        battery = 300;
    }
    public void setBattery(int x)
    {
        battery -= x;
    }
}
