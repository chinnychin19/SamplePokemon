/*
 * Began 12/14/2012
 * @Chinmay Patwardhan
 */

	import javax.swing.*; 
   import java.awt.*; 
   import java.awt.event.*;     
import java.awt.image.*; 

    public class Panel extends JPanel
   {
      private BufferedImage myImage;
      private Graphics myBuffer;
      private KeyListener myKeys;
      private Timer timer;
      
      private Player player;
      private World world;
      double[][] gridX, gridY;
            
       public Panel()
      {
         setFocusable(true); 
         myImage =  new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
         myBuffer = myImage.getGraphics();
         myKeys = new KeyListener();
         addKeyListener(myKeys);
         timer = new Timer(10, new Listener());
         
         player = new Player("chinnychin19");
         world = new World(player);
	   	 gridX = world.getGridX();
	   	 gridY = world.getGridY();
         
         timer.start();
      }
       private class Listener implements ActionListener
      {
          public void actionPerformed(ActionEvent e)
          {
        	  drawBackground();
        	  drawFrame();
        	  
        	  world.drawObstacles(myBuffer);
        	  world.drawPlayer(myBuffer);
        	  act();
        	  
        	  repaint();
          }
      }
       
	   	
       public void drawFrame() {
           myBuffer.setColor(Color.black);
           myBuffer.fillRect(0, 0, Constants.WIDTH, Constants.BORDER);
           myBuffer.fillRect(0, Constants.HEIGHT-Constants.BORDER, Constants.WIDTH, Constants.HEIGHT);
           myBuffer.fillRect(0, Constants.BORDER, Constants.BORDER, Constants.HEIGHT);
           myBuffer.fillRect(Constants.WIDTH-Constants.BORDER, 0, Constants.WIDTH, Constants.HEIGHT);
       }
       
       private Image normalBackground = new ImageIcon("img/background/normal.png").getImage();
       private int moveCounter = Constants.MOVE_FRAMES;
       public void drawBackground() {
    	   //draws normal grass (no wild pokemon)
    	   
    	   int cellW = (int)world.getCellWidth();
    	   int cellH = (int)world.getCellHeight();
    	   int offX = 0;
    	   int offY = 0;
    	   if(moveCounter != Constants.MOVE_FRAMES) { //then we are in the middle of a move
    		   moveCounter++;
    		   if(player.getDir().equals(Constants.UP)) {
    			   offY = (int)(moveCounter*world.getCellHeight()/Constants.MOVE_FRAMES);
    		   }
    		   else if(player.getDir().equals(Constants.DOWN)) {
    			   offY = -1 * (int)(moveCounter*world.getCellHeight()/Constants.MOVE_FRAMES);
    		   }
    		   else if(player.getDir().equals(Constants.LEFT)) {
    			   offX = (int)(moveCounter*world.getCellWidth()/Constants.MOVE_FRAMES);
    		   }
    		   else if(player.getDir().equals(Constants.RIGHT)) {
    			   offX = -1 * (int)(moveCounter*world.getCellWidth()/Constants.MOVE_FRAMES);
    		   }
    		   else {
    			   System.out.println("Failed in drawing background animation");
    			   System.exit(0); 
    		   }
    		   offX-=cellW;//prevents blurring on edge of frame
    		   offY-=cellH;//prevents blurring on edge of frame
    	   }
    	   myBuffer.drawImage(normalBackground, offX, offY, Constants.WIDTH+2*cellW, Constants.HEIGHT+2*cellH, null);
    	   
    	   //draws white space
//         myBuffer.setColor(Color.white);
//         myBuffer.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
       }
       
       //determine action to be taken based on settings
       public void act() {
    	   move();
	   }
       
       public void print(String s) {
    	   System.out.println(s);
       }
       
       //if menu not open, not in a battle
       public void move() {
    	   if(moveCounter != Constants.MOVE_FRAMES) return; //don't move if already in middle of a move
    	   if(myKeys.LEFT) {
    		   if(world.moveLeft())
    			   moveCounter = 0;
    	   }
    	   else if(myKeys.RIGHT) {
    		   if(world.moveRight()) {
    			   moveCounter = 0;
    		   }
    	   }
    	   else if(myKeys.UP) {
    		   if(world.moveUp()) {
    			   moveCounter = 0;
    		   }
    	   }
    	   else if(myKeys.DOWN) {
    		   if(world.moveDown())
    			   moveCounter = 0;
    	   }
//    	   player.printLocation();
       }
   	
       private class KeyListener extends KeyAdapter
      {
    	   private boolean LEFT=false, RIGHT=false, UP=false, DOWN=false, START=false, SELECT=false;
    	   
          public void keyPressed(KeyEvent e) 
         {
            int x = e.getKeyCode();
            if(x == KeyEvent.VK_LEFT) {
               LEFT = true;
            }
            if(x == KeyEvent.VK_UP) {
            	UP = true;
            }
            if(x == KeyEvent.VK_RIGHT) {
            	RIGHT = true;
            }
            if(x == KeyEvent.VK_DOWN) {
            	DOWN = true;
            }
            
        	if(x == KeyEvent.VK_Q)
        		START = true;
        	if(x == KeyEvent.VK_W)
        		SELECT = true;
         }
          
          public void keyReleased(KeyEvent e) 
         {
            int x = e.getKeyCode();
            if(x == KeyEvent.VK_LEFT)
                LEFT = false;
             if(x == KeyEvent.VK_UP)
             	UP = false;
             if(x == KeyEvent.VK_RIGHT)
             	RIGHT = false;
             if(x == KeyEvent.VK_DOWN)
             	DOWN = false;
             
         	if(x == KeyEvent.VK_Q)
         		START = false;
         	if(x == KeyEvent.VK_W)
         		SELECT = false;
         }
      }
       public void paintComponent(Graphics g)
      {
         g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
   
   }