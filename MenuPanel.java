
/**
 * Write a description of class AnimationPanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MenuPanel extends JPanel 
{
    private final int WIDTH = 1300, HEIGHT = 650;
    private final int LEFT_BORDER = 340;
    private static final Color c1 = new Color(255,255,255);      //white
    private static final Color c2 = new Color(255,255,51);       //yellow
    private static final Font f1 = new Font("Arial Black", Font.BOLD, 100);
    private static final Font f2 = new Font("Arial Black", Font.BOLD, 50);
    private static final Font f3 = new Font("Arial Black", Font.BOLD, 25);
    private static final Font f4 = new Font("Arial Black", Font.BOLD, 40);
    private static final String s1 = "BOO", s2 = "BOMBARDMENT", s3 = "Difficulty: ";
    private static final String [] S = {"Play", "Options", "Controls", "Close"};
    private static final String [] SO = {"Easy", "Medium", "Hard"};
    private static final String back = "<==";
    private static final String[] I = {"In Boo Bombardment, the goal is to survive as long as possible.",
                                       "However, this gets harder and harder as more Boos",
                                       "spawn in periodicaly. To avoid the Boos use the left and right",
                                       "arrow keys to move, the spacebar to jump, and the shift button", 
                                       "to sprint.      Have Fun!"};
    private boolean backSel = false;
    private boolean backPre = false;
    private boolean backCli = false;
    private boolean[] selected = {false, false, false, false};
    private boolean[] selectedO = {false, false, false};
    private boolean[] clicked = {false,false,false,false};
    private boolean[] clickedO = {false,false,false};
    private int page = 0;
    private final Rectangle[] R = {new Rectangle(LEFT_BORDER,310,300,50), new Rectangle(LEFT_BORDER,375,300,50), new Rectangle(LEFT_BORDER,440,300,50), new Rectangle(LEFT_BORDER,505,300,50)};
    private final Rectangle[] RO = {new Rectangle(LEFT_BORDER,310,300,50), new Rectangle(LEFT_BORDER,375,300,50), new Rectangle(LEFT_BORDER,440,300,50)};
    private final Rectangle backB = new Rectangle(25,535,300,50);
    private PicBoo b;
    private int count = 0;
    private Timer timer;
    private Point p;
    private int difficulty = 1;
    private boolean playtime = false;
    private boolean completlyDone = false;
    private int sc1 = 0;
    private int sc2 = 0;
    private int sc3 = 0;
    private int scR = 0;
    public MenuPanel(int delay)
    {
        timer = new Timer(delay,null);
        timer.addActionListener(new MenuListener());
        MenuMoListener myListener = new MenuMoListener();
        addMouseListener(myListener);
        addMouseMotionListener(myListener);
        setFocusable(true);
        b = new PicBoo(10,50,20);
        b.setState(1);
        timer.start();
        setBackground(Color.gray);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        b.drawChar(g);
        g.setColor(c1);
        g.setFont(f1);
        g.drawString(s1,LEFT_BORDER,120);
        g.drawString(s2,LEFT_BORDER,260);
        g.setFont(f2);
        
        if(page == 1)
        {
            drawOptions(g);
        }else if(page == 2){
            drawControls(g);
        }else{
            drawMain(g);
        }
    }
    public boolean getPlayTime()
    {
        return playtime;
    }
    public void setPlayTime(boolean p)
    {
        playtime = p;
    }
    public int getDiff()
    {
        return difficulty;
    }
    public void reset()
    {
        setFocusable(true);
        playtime = false;
        for(int i = 0; i < clickedO.length; i++)
        {
            clickedO[i] = false;
        }
        timer.start();
    }
    public boolean getComplete()
    {
        return completlyDone;
    }
    public void setScore(int s)
    {
        if(s > sc1)
        {
            sc3 = sc2;
            sc2 = sc1;
            sc1 = s;
        }else if(s > sc2 && s <= sc1)
        {
            sc3 = sc2;
            sc2 = s;
        }else if(s > sc3 && s <= sc2)
        {
            sc3 = s;
        }
    }
    public void setRecent(int s)
    {
        scR = s;
    }
    private class MenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            count++;
            if(count%50 == 0)
            {
               int i = b.getState();
               b.setState(-i); 
            }
            repaint();
        }
    }
    private class MenuMoListener extends MouseInputAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            p = e.getPoint();
            if(page == 1)
            {
                for(int i = 0; i < clickedO.length; i++)
                {
                    if(RO[i].contains(p.x,p.y))
                    {
                        clickedO[i] = true;
                        difficulty = i;
                        page = 0;
                    }else{
                        clickedO[i] = false;
                    }
                }
                if(backB.contains(p.x,p.y))
                {
                    page = 0;
                }
            }else if(page == 2)
            {
                if(backB.contains(p.x,p.y))
                {
                    page = 0;
                }
            }else{//main page
                for(int i = 0; i < clicked.length; i++)
                {
                    if(R[i].contains(p.x,p.y))
                    {
                        clicked[i] = true;
                        if(i == 0)
                        {
                            playtime = true;
                            timer.stop();
                        }
                        if(i == 1)
                        {
                            page = 1;
                        }
                        if(i == 2)
                        {
                            page = 2;
                        }
                    }else{
                        clicked[i] = false;
                    }
                }
            }
        }
        public void mouseMoved(MouseEvent e)
        {
            p = e.getPoint();
            if(page == 1)
            {
                if(backB.contains(p.x,p.y))
                {
                    backSel = true;
                }else{
                    for(int i = 0; i < selectedO.length; i++)
                    {
                        if(R[i].contains(p.x,p.y))
                        {
                            selectedO[i] = true;
                        }else{
                            selectedO[i] = false;
                        }
                    }
                    backSel = false;
                }
            }else if(page == 2)
            {
                if(backB.contains(p.x,p.y))
                {
                    backSel = true;
                }else{
                    backSel = false;
                }
            }else{
                for(int i = 0; i < selected.length; i++)
                {
                    if(R[i].contains(p.x,p.y))
                    {
                        selected[i] = true;
                    }else{
                        selected[i] = false;
                    }
                }
                backSel = false;
            }
        }
    }
    public void drawMain(Graphics g)
    {
        for(int i = 0; i < S.length; i++)
        {
            if(selected[i])
            {
                g.setColor(c2);  
            }else{
                g.setColor(c1);
            }
            g.drawString(S[i], LEFT_BORDER, 350 + (i*65));
        }
        g.setColor(c1);
        g.drawString(s3+SO[difficulty], LEFT_BORDER+350, 350);
        g.setFont(f4);
        g.drawString("Latest Score: " + scR, LEFT_BORDER+350, 400);
        if(sc1 == 0)
        {
            g.drawString("1st: --", LEFT_BORDER+350, 450);
        }else{
            g.drawString("1st: " + sc1, LEFT_BORDER+350, 450);
        }
        if(sc2 == 0)
        {
            g.drawString("2nd: --", LEFT_BORDER+350, 500);
        }else{
            g.drawString("2nd: " + sc2, LEFT_BORDER+350, 500); 
        }
        if(sc3 == 0)
        {
            g.drawString("3rd: --", LEFT_BORDER+350, 550);
        }else{
            g.drawString("3rd: " + sc3, LEFT_BORDER+350, 550);
        }
    }
    public void drawOptions(Graphics g)
    {
        for(int i = 0; i < SO.length; i++)
        {
            if(selectedO[i])
            {
                g.setColor(c2);
            }else{
                g.setColor(c1);
            }
            g.drawString(SO[i], LEFT_BORDER, 350 + (i*65));
        }
        if(backSel)
        {
            g.setColor(c2);  
        }else{
            g.setColor(c1);
        }
        g.drawString(back, 25, 575);
        g.setColor(c1);
    }
    public void drawControls(Graphics g)
    {
        g.setFont(f3);
        for(int i = 0; i < I.length;i++)
        {
            g.drawString(I[i],LEFT_BORDER,350 + (i*40));
        }
        if(backSel)
        {
            g.setColor(c2);  
        }else{
            g.setColor(c1);
        }
        g.setFont(f2);
        g.drawString(back, 25, 575);
        g.setColor(c1);
    }
}