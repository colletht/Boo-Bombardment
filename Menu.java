
/**
 * Write a description of class Animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JApplet
{
    // instance variables - replace the example below with your own
    private final int DELAY = 20;
    private final int WIDTH = 1300, HEIGHT = 650;
    private Timer timer;
    MenuPanel p;
    /**
     * Constructor for objects of class Animation
     */
    public void init()
    {
        timer = new Timer(DELAY,null);
        p = new MenuPanel(20);
        getContentPane().add(p);
        p.requestFocusInWindow();
        setSize(WIDTH, HEIGHT);
    }
    
    public void start()
    {
        timer.start();
    }
    
    public void stop()
    {
        timer.stop();
    }
}
