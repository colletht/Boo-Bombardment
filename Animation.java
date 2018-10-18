
/**
 * Write a description of class Animation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Animation extends JApplet
{
    // instance variables - replace the example below with your own
    private final int DELAY = 20;
    private final int WIDTH = 1300, HEIGHT = 650;
    AnimationPanel p;
    private boolean b = false;
    /**
     * Constructor for objects of class Animation
     */
    public void init()
    {
        p = new AnimationPanel(20,3,500);
        getContentPane().add(p);
        p.requestFocusInWindow();
        setSize(WIDTH, HEIGHT);
    }
}
