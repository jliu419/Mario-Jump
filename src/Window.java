import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

// this is a class that connects mario and image together
public class Window extends JPanel implements KeyListener
{
  public static final int DRAWING_WIDTH = 800;
  public static final int DRAWING_HEIGHT = 600;
  private boolean left, right, jump, quit, fall;
  private Character mario;
  private MovingImage platform;
  private Hittingobject a;
  private int size, xlocal;
  
  public Window () {
	  super();
	  size = 50;
	  xlocal = (int)(Math.random()*700);
	  mario = new Character(380,0);
	  a = new Hittingobject("Asteroid.PNG",xlocal,0,size, size);
	  platform = new MovingImage("bricks.png",0,500,800,50);
	  setBackground(Color.CYAN);
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

    int width = getWidth();
    int height = getHeight();
    
    double ratioX = (double)width/DRAWING_WIDTH;
    double ratioY = (double)height/DRAWING_HEIGHT;
    
    Graphics2D g2 = (Graphics2D)g;
    AffineTransform at = g2.getTransform();
    g2.scale(ratioX,ratioY);
    
    platform.draw(g,this);
    mario.draw(g,this);  
    a.draw(g,this); 
    
    g2.setTransform(at);
  }

  
  public void keyPressed(KeyEvent e) {
  	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
  		//System.out.println("left true");
		left = true;
  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
  		right = true;
  	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
  		jump = true;
  	}
  	else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
  		fall = true;
  	}
  	else if (e.getKeyCode() == KeyEvent.VK_Q) {
  		quit = true;
  	}
  }
    
  public void keyReleased(KeyEvent e) {
  	if (e.getKeyCode() == KeyEvent.VK_LEFT) {
  		//System.out.println("left false");
  		left = false;
  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
  		right = false;
  	}
  	else if (e.getKeyCode() == KeyEvent.VK_UP) {
  		jump = false;
  	}
  	else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
  		fall = false;
  	}
  	else if (e.getKeyCode() == KeyEvent.VK_Q) {
  		quit = true;
  	}
  }

  public void keyTyped(KeyEvent e) {
  	
  }
  
  
  public void run() {
  	while(true) {
  		mario.fall(platform);
  		a.fall(platform);
  		// MAKE A CHANGE
  		if (jump == true)
  		{
  			mario.jump();
  		}
  		if (left == true)
  		{
  			mario.walk(-1);
  		}
  		if (right == true)
  		{
  			mario.walk(1);
  		}
  		if (fall == true)
  		{
  			mario.falldown();
  		}
  		if (quit == true)
  		{
  			mario.quit();
  			quit = false;
  		}
  		// SHOW THE CHANGE
  		repaint();
  		
  		// WAIT
  		try
  		{
  			Thread.sleep(10);
  		}
  		catch(InterruptedException e)
  		{
  			e.printStackTrace();
  		}
  		checkMario();
  		
  		checkAsteroid();
    	size = 50;
    	xlocal = (int)(Math.random()*700);
    	
    	if (a.istouch(mario))
    	{
    		break;
    	}
  	 }		
    JOptionPane.showMessageDialog(null,
            "you are dead",
            "Service", JOptionPane.INFORMATION_MESSAGE);
   
  }
  
  public void checkAsteroid() {
	  	int x = a.getX() + a.getWidth()/2;
	  	int y = a.getY() + a.getHeight()/2;
	  	if (x < 0 || x > DRAWING_WIDTH || y < 0 || y > DRAWING_HEIGHT)
	  		a = new Hittingobject("Asteroid.PNG", xlocal, 0, size, size);
	  }

  
  public void checkMario() {
  	int x = mario.getX() + mario.getWidth()/2;
  	int y = mario.getY() + mario.getHeight()/2;
  	if (x < 0 || x > DRAWING_WIDTH || y < 0 || y > DRAWING_HEIGHT)
  		mario = new Character(380,0);
  }


  // As your program grows, you may want to...
  //   1) Move this main method into its own 'main' class
  //   2) Customize the JFrame by writing a class that extends it, then creating that type of object in your main method instead
  public static void main(String[] args)
  {
    JFrame w = new JFrame("Simple Window");
    w.setBounds(100, 100, 640, 480);
    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Window panel = new Window();
    w.addKeyListener(panel);
    w.add(panel);
    w.setResizable(true);
    w.setVisible(true);
    
    panel.run();
  }
}