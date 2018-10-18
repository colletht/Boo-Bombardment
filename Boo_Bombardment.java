
/**
 * Write a description of class test2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Boo_Bombardment
{
    public static void main(String[] args)
    {
        MenuPanel menu = new MenuPanel(20);
        AnimationPanel game = new AnimationPanel(20,4,500);
        JFrame gameFrame = new JFrame();
        JFrame menuFrame = new JFrame();
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.getContentPane().add(menu);
        gameFrame.setSize(1300,700);
        menuFrame.setSize(1300,700);
        gameFrame.setVisible(false);
        menuFrame.setVisible(true);
        while(!menu.getComplete())
        {
            if(menu.getPlayTime())
            {
                menu.setPlayTime(false);
                menuFrame.setVisible(false);
                if(menu.getDiff()== 1)
                {
                    game = new AnimationPanel(20,6,400);
                    gameFrame.getContentPane().add(game);
                }else if(menu.getDiff()== 2)
                {
                    game = new AnimationPanel(10,10,200);
                    gameFrame.getContentPane().add(game);
                }else{
                    game = new AnimationPanel(20,4,500);
                    gameFrame.getContentPane().add(game);
                }
                gameFrame.setVisible(true);
                game.startGame();
            }
            if(game.getOver())
            {
                game.setOver(false);
                menu.setScore(game.getScore());
                menu.setRecent(game.getScore());
                menu.reset();
                menuFrame.setVisible(true);
                gameFrame.setVisible(false);
                gameFrame.getContentPane().remove(game);
            }
        }
        
    }
}
