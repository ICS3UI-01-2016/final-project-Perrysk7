package finalgameproject_parry.katodia;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Parry Katodia
 */


public class FinalProject_ParryKatodia extends JComponent implements KeyListener{

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000)/desiredFPS;
    
    // game variables 
    // draw my ship on the screen
    Rectangle myship = new Rectangle(400,525,30,22);
    int speed = 5;
    // left and right key variable 
    boolean left = false; 
    boolean right = false; 
    // draw the asteroid on the screen
    Random randGen = new Random();
    int asteroidX = randGen.nextInt();
    int asteroidY = randGen.nextInt();
    Rectangle[] asteroids = new Rectangle[25];
    int asteroidWidth = 25;
    int asteroidLength = 25; 
    int asteroidSpacing = 75;
    
    
    Rectangle asteroid0 = new Rectangle(782, 400, asteroidX, asteroidY); 
    Rectangle asteroid1 = new Rectangle(672, 300, 50, 50);
    Rectangle asteroid2 = new Rectangle(300, 500, 50, 50);
    Rectangle asteroid3 = new Rectangle(425, 100, 50, 50); 
    Rectangle asteroid4 = new Rectangle(525, 275, 50, 50);
    Rectangle asteroid5 = new Rectangle(601, 300, 50, 50);
    
     

    
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g)
    {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // set a color for the background
        g.setColor(Color.BLACK);
        // draw the space background 
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // draw the player's space ship
        g.setColor(Color.white);
        g.fillRect(myship.x, myship.y, myship.height, myship.width);
        // draw the astriods
        g.fillRect(asteroid0.x, asteroid0.y, asteroid0.height, asteroid0.width);
        g.fillRect(asteroid1.x, asteroid1.y, asteroid1.height, asteroid1.width);
        g.fillRect(asteroid2.x, asteroid2.y, asteroid2.height, asteroid2.width);
        g.fillRect(asteroid3.x, asteroid3.y, asteroid3.height, asteroid3.width);
        g.fillRect(asteroid4.x, asteroid4.y, asteroid4.height, asteroid4.width);
        g.fillRect(asteroid5.x, asteroid5.y, asteroid5.height, asteroid5.width);
        // GAME DRAWING ENDS HERE
    }
    
    
    // The main game loop
    // In here is where all the logic for my game will go
    public void run(){
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
        
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false; 
        while(!done)
        {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();
            
            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            if(myship.x >= 800 - myship.width){
                right = false; 
            }  
            if(left == true){
                myship.x = myship.x - speed;    
            }
            if(right == true){
                myship.x = myship.x + speed;
            }
            
            
            

            // GAME LOGIC ENDS HERE 
            
            // update the drawing (calls paintComponent)
            repaint();
            
            
            
            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try
            {
               if(deltaTime > desiredTime)
               {
                   //took too much time, don't wait
                   Thread.sleep(1);
               }else{
                  // sleep to make up the extra time
                 Thread.sleep(desiredTime - deltaTime);
               }
            }catch(Exception e){};
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
       
        // creates an instance of my game
        FinalProject_ParryKatodia game = new FinalProject_ParryKatodia();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        // adds the game to the window
        frame.add(game);
        frame.addKeyListener(game);
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        
        // starts my game loop
        game.run();
    }
     @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getExtendedKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            left = true;
        }
        if(key == KeyEvent.VK_RIGHT){
            right = true; 
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getExtendedKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            left = false;
        }
        if(key == KeyEvent.VK_RIGHT){
            right = false; 
        } 
    }
}