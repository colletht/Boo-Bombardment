
/**
 * Write a description of class AnimationPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class AnimationPanel extends JPanel 
{
    private final int WIDTH = 1300, HEIGHT = 650;
    private final int SIZE = 5;
    private Timer timer;
    private int x, y, moveL, moveU = 0,moveR,moveD,moveX,moveY;
    private Boo t;
    private Mario m;
    private Flashlight f;
    private int count = 0;
    private ArrayList<Boo> b = new ArrayList<Boo>();
    private Platform plat = new Platform(0,HEIGHT-(SIZE*2*16)-13*SIZE,SIZE,5,1);
    private Platform plat2 = new Platform(WIDTH-(SIZE*13*5),HEIGHT-(SIZE*2*16)-13*SIZE,SIZE,5,1);
    private Platform plat3 = new Platform((WIDTH/2)-3*SIZE*13,(SIZE*3*16),SIZE,5,1);
    private int s = 1;
    private int gravity = 2;
    private int begin = 0, end = 50;
    private int start = 0, finish = 1, jumptime = 10;
    private int lowerB = 0; // 0 is normal, 1 is first level platforms 2 is top platform
    private boolean isDown = false, isOver = false;
    private int interval;
    private int score = 0;
    private int finalscore;
    private Font f1 = new Font("Arial Black", Font.BOLD, 150);
    private Font f2 = new Font("Arial Black", Font.PLAIN,20);
    private Font f3 = new Font("Arial Black", Font.BOLD, 40);
    public AnimationPanel(int delay, int bstartcount, int inter)
    {
        timer = new Timer(delay,null);
        timer.addActionListener(new AnimationListener());
        MoveListener listener = new MoveListener();
        addKeyListener(listener);
        setFocusable(true);
        x = 0;
        y = HEIGHT-SIZE*16;
        moveL = moveU = moveR = moveD = 0;
        interval = inter;
        m = new Mario(x,y,SIZE);
        for(int i = 0; i < bstartcount;i++)
        {
            b.add(new Boo((int)(Math.random()*WIDTH-100),(int)(Math.random()*200),SIZE));
        }
        f = new Flashlight(0,0,SIZE);
        setBackground(Color.gray);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        m.drawChar(g);
        if(f.getState() != 0)
        {
            f.drawChar(g);
        }
        plat.drawCharLen(g);
        plat2.drawCharLen(g);
        plat3.drawCharLen(g);
        for(int i = 0; i < b.size(); i++)
        {
            t = b.get(i);
            t.drawChar(g);
        }
        drawHealth(g);
        drawScore(g);
        if(m.getHealth() <= 0)
        {
            if(begin == 0)
            {
                begin = count;
                finalscore = score;
            }
            endG(g);
        }
    }
    public void clear(Graphics g) 
    { 
        Dimension d = getSize(); 

        Color c = getBackground(); 

        g.setColor(c); 

        g.fillRect(0,0,d.width,d.height); 

        repaint(); 
    }
    public void endG(Graphics g)
    {
        clear(g);
        g.setColor(Color.red);
        g.setFont(f1);
        g.drawString("GAME OVER",100,360);
        g.setFont(f3);
        g.setColor(Color.white);
        g.drawString("Your Final Score is: " + finalscore, 320,460);
        if(count - begin > end)
        {
            timer.stop();
            isOver = true;
        }
    }
    public int getScore()
    {
        return finalscore;
    }
    public boolean getOver()
    {
        return isOver;
    }
    public void setOver(boolean o)
    {
        isOver = o;
    }
    public void reset()
    {
        m = new Mario(x,y,SIZE);
        b.add(new Boo((int)(Math.random()*WIDTH-100),(int)(Math.random()*200),SIZE));
        b.add(new Boo((int)(Math.random()*WIDTH-100),(int)(Math.random()*200),SIZE));
        b.add(new Boo((int)(Math.random()*WIDTH-100),(int)(Math.random()*200),SIZE));
        f = new Flashlight(0,0,SIZE);
        setBackground(Color.gray);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        timer.start();
    }
    public void startGame()
    {
        timer.start();
    }
    private class AnimationListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            count++;
            score++;
            if(!m.getGround())
            {
                moveU += gravity;
            }
            //adjusts positions
            moveX = (moveL + moveR)*s;
            moveY = (moveU + moveD)+gravity;
            x+=moveX;
            y+=moveY;
            
            boundsHandling();
            
            m.setXY(x,y);
            for(int i = 0; i < b.size(); i++)
            {
                t = b.get(i);
                t.setState(1);
                t.move(m.getX(),m.getY(),m.getDir());
                if(Math.abs(t.getX() - m.getX()) < 16*SIZE && Math.abs(t.getY() - m.getY()) < 16*SIZE)
                {
                    m.setHealth(1);
                }
                if(f.getState() != 0)
                {
                    if((Math.abs(t.getX() - f.getX()) < 22*SIZE && Math.abs(t.getY() - f.getY()) < 16*SIZE) && f.getState() < 0)
                    {
                        t.setHealth(10);
                    }
                    if(t.getHealth() < 0)
                    {
                        b.remove(i);
                        score+=100;
                    } 
                }
            }
            if(count%interval == 0)
            {
                b.add(new Boo((int)(Math.random()*WIDTH-100),(int)(Math.random()*200),SIZE));
            }
            
            flashlightHandling();
            
            repaint();
        }
    }
    private class MoveListener implements KeyListener
    {
        public void keyPressed(KeyEvent e)
        {
            int id = e.getKeyCode();
            
            if(e.getKeyCode() == 16)
            {
                s = 2;
            }
            if(m.getGround())
            {
                if(id == 32 )
                {
                    start = count;
                    moveU = -35;
                    m.setGround(false);
                } 
            }
            if(id == 37)
            {
                moveL = -10;
                m.setDir(true);
            }
            if(id == 39)
            {
                moveR = 10;
                m.setDir(false);
            }
            if(id == 40)
            {
                moveD = 5;
                isDown = true;
            }
        }
        public void keyReleased(KeyEvent e)
        {
            int id = e.getKeyCode();
            
            if(e.getKeyCode() == 16)
            {
                s = 1;
            }
            if(id == 32)
            {
                moveU = 0;
                start = 0;
            }
            if(id == 37)
            {
                moveL = 0;
            }
            if(id == 39)
            {
                moveR = 0;
            }
            if(id == 40)
            {
                moveD = 0;
                isDown = false;
            }
        }
        public void keyTyped(KeyEvent e){}
    }
    public void flashlightHandling()
    {
        if(count%1500 == 0 && f.getState() == 0)//places new flashlight if there are none there and a time has passed
        {
            f.setXY((int)(Math.random()*(WIDTH-(SIZE*16))),HEIGHT-80);
            f.setState(1);
        }
        if(f.getState() != 0)
        {
            if(Math.abs(m.getX() - f.getX()) < 16*SIZE && Math.abs(m.getY() - f.getY()) < 16*SIZE)
            {
                if(m.getDir())
                {
                    f.setXY(x-(5*SIZE),y+(3*SIZE));
                }else
                {
                    f.setXY(x+(5*SIZE),y+(3*SIZE));
                }
                f.setState(-1);
                f.setDir(m.getDir());
            }
            if(f.getState() < 0)
            {
                f.setBattery(1);
                score++;
            }
            if(f.getBattery() < 0)
            {
                f.setState(0);
                f.replenish();
            }
            if(f.getState() > 0)//makes mario curious if there is a flashlight out and he has not touched it
            {
                m.curious(true);
            }
        }      
    }
    public void boundsHandling()
    {
        //sets lower bounds for mario
        if(x < plat.getX()+13*5*SIZE || x + 16*SIZE > plat2.getX())
        {
            if(y+SIZE*16 <= plat.getY())
            {
                lowerB = 1;
            }
        }else if(x < plat3.getX()+13*5*SIZE && x + 16*SIZE > plat3.getX())
        {
            if(y+SIZE*16 <= plat3.getY())
            {
                lowerB = 2;
            }
        }else{
            lowerB = 0;
        }
        if(isDown)
        {
            lowerB = 0;
        }
        if(x<=0)
        {
            x = 0;
        }
        if(y<=0)
        {
            y = 0;
        }
        if(x>=WIDTH-SIZE*16)
        {
            x = WIDTH-SIZE*16;
        }
        if(y>=HEIGHT-SIZE*16 )
        {
            y = HEIGHT-SIZE*16;
            m.setGround(true);
            m.setState(1);
        }else{
            m.setState(-1);
        }
        if(y+SIZE*16 >= plat.getY() && lowerB == 1)
        {
            y = plat.getY()-SIZE*16;
            m.setGround(true);
            m.setState(1);
        }
        if(y+SIZE*16 >= plat3.getY() && lowerB == 2)
        {
            y = plat3.getY()-SIZE*16;
            m.setGround(true);
            m.setState(1);
        }     
    }
    public void drawHealth(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(WIDTH-135,2,110,25);
        g.setColor(Color.gray);
        g.drawRect(WIDTH-130,7,100,15);
        g.setColor(Color.yellow);
        g.fillRect(WIDTH-130,7,m.getHealth(),15);
    }
    public void drawScore(Graphics g)
    {
        g.setFont(f2);
        g.setColor(Color.white);
        String t = "" + score;
        g.drawString(t,5,20);
    }
}
