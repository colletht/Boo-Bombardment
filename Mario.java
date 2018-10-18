
/**
 * Write a description of class Boo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
public class Mario 
{
    private static final Color c1 = new Color(0,0,0);       //black
    private static final Color c2 = new Color(230,0,0);     //red
    private static final Color c3 = new Color(0,0,190);     //Blue
    private static final Color c4 = new Color(255,225,170); //tan
    private static final Color c5 = new Color(255,255,0);   //yellow
    private static final Color c6 = new Color(95,60,10);    //brown
    private final int[][]REGULAR = {{0,0,0,0,0,2,2,2,2,2,2,0,0,0,0,0},
                                    {0,0,0,0,2,2,2,2,2,2,2,2,2,2,0,0},
                                    {0,0,0,0,6,6,6,4,4,4,1,4,0,0,0,0},
                                    {0,0,0,6,4,6,4,4,4,4,1,4,4,4,0,0},
                                    {0,0,0,6,4,6,6,4,4,4,4,1,4,4,4,0},
                                    {0,0,0,6,6,4,4,4,4,4,1,1,1,1,0,0},
                                    {0,0,0,0,0,4,4,4,4,4,4,4,4,0,0,0},
                                    {0,0,0,0,2,2,3,2,2,2,3,0,0,0,0,0},
                                    {0,0,0,2,2,2,3,2,2,2,3,2,2,2,0,0},
                                    {0,0,2,2,2,2,3,3,3,3,3,2,2,2,2,0},
                                    {0,0,4,4,2,3,5,3,3,3,5,3,2,4,4,0},
                                    {0,0,4,4,4,3,3,3,3,3,3,3,4,4,4,0},
                                    {0,0,4,4,3,3,3,3,3,3,3,3,3,4,4,0},
                                    {0,0,0,0,3,3,3,0,0,0,3,3,3,0,0,0},
                                    {0,0,0,6,6,6,0,0,0,0,0,6,6,6,0,0},
                                    {0,0,6,6,6,6,0,0,0,0,0,6,6,6,6,0}};
             
    private final int[][]JUMP =    {{0,0,0,0,0,2,2,2,2,2,0,0,4,4,4,0},
                                    {0,0,0,0,2,2,2,2,2,2,2,2,2,4,4,0},
                                    {0,0,0,0,6,6,6,4,4,1,4,0,2,2,2,0},
                                    {0,0,0,6,4,6,4,4,4,1,4,4,4,2,2,0},
                                    {0,0,0,6,4,6,6,4,4,4,1,4,4,4,2,0},
                                    {0,0,0,6,6,4,4,4,4,1,1,1,1,2,0,0},
                                    {0,0,0,0,4,4,4,4,4,4,4,4,2,2,0,0},
                                    {4,4,2,2,2,2,3,2,2,2,3,2,2,0,0,6},
                                    {4,4,2,2,2,2,2,3,2,2,2,3,0,0,6,6},
                                    {4,4,4,2,2,2,2,3,3,3,3,5,3,3,6,6},
                                    {0,0,0,3,3,3,3,5,3,3,3,3,3,3,6,6},
                                    {0,6,6,3,3,3,3,3,3,3,3,3,3,3,6,6},
                                    {6,6,6,3,3,3,3,3,3,0,0,0,0,0,0,0},
                                    {6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                    {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    private int state = 1;
    private int [][] arr;
    private int x,y;
    private int pixSize = 10;
    private  boolean isFacingLeft = true;
    private boolean isTouchingGround = true;
    private boolean isHurt = false;
    private boolean curious = false;
    private int health = 100;
    private Emotions e;
    /**
     * Constructor for objects of class Mario
     */
    public Mario(int x, int y, int size)
    {
        this.x=x;
        this.y=y;
        pixSize = size;
        e = new Emotions(x,y,pixSize);
    }

    public void drawChar(Graphics g)
    {
        setState(state);
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
                   }else if(arr[i][j] == 5)
                   {
                       g.setColor(c5);
                   }else if(arr[i][j]==4)
                   {
                       g.setColor(c4);
                   }else if(arr[i][j] == 6)
                   {
                       g.setColor(c6);
                   }
                   if(arr[i][j] != 0)
                   {
                       g.fillRect(x+j*pixSize,y+i*pixSize,pixSize,pixSize);
                   }
                }
            } 
            if(isHurt)
            {
               
               e.setState(1);
               e.setXY(x+(pixSize*7),y-(pixSize*8));
               e.drawChar(g);
            }
            if(curious)
            {
                e.setState(-1);
                e.setXY(x+(pixSize*7),y-(pixSize*8));
               e.drawChar(g);
            }
            curious(false);
            setHurt(false);
        }
    }
    public void drawCharReverse(Graphics g)
    {
        setState(state);
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = arr.length-1; j >= 0; j--)
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
                }else if(arr[i][j] == 5)
                {
                    g.setColor(c5);
                }else if(arr[i][j]==4)
                {
                    g.setColor(c4);
                }else if(arr[i][j] == 6)
                {
                    g.setColor(c6);
                }
                if(arr[i][j] != 0)
                {
                    g.fillRect(x+((pixSize*16)-j*pixSize),y+i*pixSize,pixSize,pixSize);
                }
            }
        } 
        if(isHurt)
            {
               
               e.setState(1);
               e.setXY(x+(pixSize*7),y-(pixSize*8));
               e.drawChar(g);
            }
            if(curious)
            {
                e.setState(-1);
                e.setXY(x+(pixSize*7),y-(pixSize*8));
               e.drawChar(g);
            }
            curious(false);
            setHurt(false);
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
    public boolean getGround()
    {
        return isTouchingGround;
    }
    public void setGround(boolean x)
    {
        isTouchingGround = x;
    }
    public int getState()
    {
        return state;
    }
    public void setState(int x)
    {
        state = x;
        if(state<1)
        {
            arr = JUMP;
        }else{
            arr = REGULAR;
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
        e.setXY(x,y);
    }
    public int getHealth()
    {
        return health;
    }
    public void setHealth(int x)
    {
        this.health -= x;
        e.setState(1);
        setHurt(true);
    }
    public void replenish()
    {
        health = 100;
    }
    public void setHurt(boolean h)
    {
        isHurt = h;
    }
    public void curious(boolean x)
    {
        curious = x;
    }
}
