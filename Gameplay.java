import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener
{
	public int[] snakexlength = new int[750];
	public int[] snakeylength = new int[750];
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int Score = 0;
	
	private int lengthofsnake = 3;
	
	private int moves =0;
	
	private Timer timer;
	private int delay = 100;
	private ImageIcon snakeimage;
	
	private ImageIcon titleImage;
	
	private int [] enemyxpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,
								400,425,450,475,500,525,550,575,600,625,650,675,700,725,
								750,775,800,825,850};
	private int [] enemyypos = {111175,100,125,150,175,200,225,250,275,300,325,350,375,
								400,425,450,475,500,525,550};
	
	private ImageIcon enemyimage;

	private Random random = new Random();
	
	private int xpos = random.nextInt(33);
	private int ypos = random.nextInt(20);
			
	
	public Gameplay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{
		if(moves == 0)
		{
			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;
			
			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
		}
		
		//draw image border
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		//draw image
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		
		//drwa gameplay border
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);
		
		//draw gameplay
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw score
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Score:" +Score, 780, 30);
		
		//draw length
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Length:" +lengthofsnake, 780, 50);
		
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a = 0; a<lengthofsnake; a++)
		{
			if(a==0 && right)
			{
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && left)
			{
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && down)
			{
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && up)
			{
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			
			if(a!=0)
			{
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
		}
		
		enemyimage = new ImageIcon("enemy.png");
		
		if((enemyxpos[xpos] == snakexlength[0] && enemyypos[ypos] == snakeylength[0]))
		{
			Score++;
			lengthofsnake++;
			xpos = random.nextInt(33);
			ypos = random.nextInt(20);
		}
		
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		for(int b = 1; b<lengthofsnake; b++)
		{
			if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0])
			{
				up = false;
				down = false;
				left = false;
				right = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("GameOver", 300, 300);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Press Space To Restart", 320, 340);
				
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(right)
		{
			 moves++;
			 for(int r = lengthofsnake-1; r>=0; r--)
			 {
				 snakeylength[r+1] = snakeylength[r];
			 }
			 for(int r = lengthofsnake; r>=0; r--)
			 {
			     if(r == 0)
			     {
			     snakexlength[r] = snakexlength[r] + 25;
			     }
			     else
			     {
			    	 snakexlength[r] = snakexlength[r-1];     
			     }
			     if(snakexlength[r]>850)
			     {
			    	 snakexlength[r] = 25;
			     }

			 }
			   repaint();
		}
		if(left)
		{
			//moves++;
			 for(int r = lengthofsnake-1; r>=0; r--)
			 {
				 snakeylength[r+1] = snakeylength[r];
			 }
			 for(int r = lengthofsnake; r>=0; r--)
			 {
			     if(r == 0)
			     {
			     snakexlength[r] = snakexlength[r] - 25;
			     }
			     else
			     {
			    	 snakexlength[r] = snakexlength[r-1];     
			     }
			     if(snakexlength[r]<25)
			     {
			    	 snakexlength[r] = 850;
			     }

			 }
			   repaint();
		}
		
		if(up)
		{
			//moves++;
			 for(int r = lengthofsnake-1; r>=0; r--)
			 {
				 snakexlength[r+1] = snakexlength[r];
			 }
			 for(int r = lengthofsnake; r>=0; r--)
			 {
			     if(r == 0)
			     {
			     snakeylength[r] = snakeylength[r] - 25;
			     }
			     else
			     {
			    	 snakeylength[r] = snakeylength[r-1];     
			     }
			     if(snakeylength[r]<75)
			     {
			    	 snakeylength[r] = 625;
			     }

			 }
			   repaint();
		}
		if(down)
		{
			//moves++;
			 for(int r = lengthofsnake-1; r>=0; r--)
			 {
				 snakexlength[r+1] = snakexlength[r];
			 }
			 for(int r = lengthofsnake; r>=0; r--)
			 {
			     if(r == 0)
			     {
			     snakeylength[r] = snakeylength[r] + 25;
			     }
			     else
			     {
			    	 snakeylength[r] = snakeylength[r-1];     
			     }
			     if(snakeylength[r]>625)
			     {
			    	 snakeylength[r] = 75;
			     }

			 }
			   repaint();
		}
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			Score = 0;
			lengthofsnake = 3;
			moves = 0;
			repaint();
			
		}
		
		 if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		 {
			 moves++;
			 right=true;
			 if(!left)
			 {
				 right = true ;
				 
			 }
			 else
			 {
				 right = false;
				 left = true;
			 }
			 up = false;
			 down = false;
		 }
		 if(e.getKeyCode()==KeyEvent.VK_LEFT)
		 {
			 moves++;
			 left=true;
			 if(!right)
			 {
				 left = true ;
				 
			 }
			 else
			 {
				 right = true;
				 left = false;
			 }
			 up = false;
			 down = false;
		 }
		 if(e.getKeyCode()==KeyEvent.VK_UP)
		 {
			 moves++;
			 up = true;
			 if(!down)
			 {
				 up = true ;
				 
			 }
			 else
			 {
				 up = false;
				 down = true;
			 }
			 left = false;
			 right = false;
		 }
		 if(e.getKeyCode()==KeyEvent.VK_DOWN)
		 {
			 moves++;
			 down=true;
			 if(!up)
			 {
				 down = true ;
				 
			 }
			 else
			 {
				 down = false;
				 up = true;
			 }
			 left = false;
			 right = false;
		 }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
// Coded by Rv
