
/**
 * Write a description of class Boo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;
public class Boo
{
    private static final Color c1 = new Color(0,0,0);       //black
    private static final Color c2 = new Color(174,174,174); //Dark Gray
    private static final Color c3 = new Color(232,232,232); //Light Gray
    private static final Color c4 = new Color(255,255,255); //white
    private static final Color c5 = new Color(255,131,185); //pink
    private final int[][]REGULAR = {{0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0},
                                    {0,0,0,1,1,2,2,2,2,2,1,1,0,0,0,0},
                                    {0,0,1,2,2,3,3,3,3,3,2,2,1,0,0,0},
                                    {0,1,2,3,3,4,4,4,4,4,3,3,2,1,0,0},
                                    {0,1,2,1,4,1,4,4,4,4,4,4,3,2,1,0},
                                    {1,2,3,1,4,1,4,4,4,4,1,1,1,3,2,1},
                                    {1,2,3,1,4,1,4,4,4,1,4,4,1,3,2,1},
                                    {1,2,3,4,4,4,4,4,4,4,4,4,1,3,1,0},
                                    {1,2,5,4,5,4,5,4,4,4,4,1,4,3,2,1},
                                    {1,2,5,5,5,5,5,4,4,4,4,4,3,2,2,1},
                                    {1,2,5,5,5,5,5,4,4,4,4,4,4,3,2,1},
                                    {0,1,2,5,5,5,5,5,4,4,4,4,4,3,2,1},
                                    {0,1,2,5,4,5,4,5,4,4,4,3,3,2,1,0},
                                    {0,0,1,2,2,3,3,3,3,3,3,2,2,1,0,0},
                                    {0,0,0,1,1,2,2,2,2,2,2,1,1,0,0,0},
                                    {0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0}};
    private final int[][]SAD = {{0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0},
                                {0,0,0,1,1,2,2,2,2,2,1,1,0,0,0,0},
                                {0,0,1,2,2,3,3,3,3,3,2,2,1,0,0,0},
                                {0,1,2,3,3,4,4,4,4,4,3,3,2,1,0,0},
                                {0,1,2,1,4,1,4,4,4,1,1,4,3,1,0,0},
                                {1,2,3,1,4,1,4,4,1,4,4,1,3,2,1,0},
                                {1,2,3,1,4,1,4,4,1,4,4,1,4,3,2,1},
                                {1,2,3,4,4,4,4,4,4,4,4,1,4,3,2,1},
                                {1,2,3,4,4,4,4,4,4,4,4,1,4,3,1,0},
                                {1,2,5,4,5,4,5,4,4,4,4,4,3,2,2,1},
                                {1,2,3,5,4,5,4,5,4,4,4,4,4,3,2,1},
                                {0,1,2,3,3,4,4,4,4,4,4,4,3,2,1,0},
                                {0,1,2,2,2,3,3,3,4,4,4,3,2,2,1,0},
                                {0,0,1,2,2,3,3,3,3,3,2,2,1,1,0,0},
                                {0,0,0,1,1,2,2,2,2,2,1,1,0,0,0,0},
                                {0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0}};
    private int state = 1;
    private int [][] arr;
    private int x,y;
    private int pixSize =10;
    private boolean isFacingLeft = true;
    private boolean isHurt = false;
    private int xVelocity,yVelocity;
    private int health = 1000;
    private Emotions e;
    /**
     * Constructor for objects of class Boo
     */
    public Boo(int x, int y, int size)
    {
        this.x=x;
        this.y=y;
        pixSize = size;
        xVelocity = yVelocity = 2;
        e = new Emotions(x,y,pixSize);
    }

    public void drawChar(Graphics g)
    {
        setState(state);
        if(isFacingLeft == false)
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
    public int getState()
    {
        return state;
    }
    public void setState(int x)
    {
        state = x;
        if(state<0)
        {
            arr = SAD;
        }else{
            arr = REGULAR;
        }
    }
    public int getY()
    {
        return y;
    }
    public int getX()
    {
        return x;
    }
    public void move(int mX, int mY, boolean mDir)
    {
            if(mX - x > 0)//when boo is to the left of mario
            {
                if(isFacingLeft)//boo is facing left
                {
                    updateDir();
                    if(mDir)//mario  is facing left
                    {
                        setState(-1);
                    }else{
                        setXY(mX,mY);
                    }
                }else{//boo is facing right
                    if(mDir)//mario is facing left
                    {
                        setState(-1);
                    }else{
                        setXY(mX,mY);
                    }
                }
            }
            if(mX - x < 0)//when boo is to the right of mario
            {
                if(!isFacingLeft)//boo is facing right
                {
                    updateDir();
                    if(!mDir)//mario is facing right
                    {
                        setState(-1);
                    }else{
                        setXY(mX,mY);
                    }
                }else{//boo is facing left
                    if(!mDir)//mario is facing right
                    {
                        setState(-1);
                    }else{
                        setXY(mX,mY);
                    }
                }
            }
    }
    public void setXY(int mX, int mY)
    {
        xVelocity = (int)(Math.random()*2+1);
        yVelocity = (int)(Math.random()*2+1);
        if(mX - x > 0)//boo is to left of mario
        {
            x+=xVelocity;
        }else if(mX - x < 0)//boo is to right of mario
        {
            x-=xVelocity;
        }
        if(mY - y > 0)//boo is above mario
        {
            y+=yVelocity;
        }else if(mY - y < 0)//boo is below mario
        {
            y-=yVelocity;   
        }
        e.setXY(x-7,y-7);
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
    public void setHurt(boolean h)
    {
        isHurt = h;
    }
}
