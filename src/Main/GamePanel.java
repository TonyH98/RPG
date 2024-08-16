package Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Entity.Player;
import tile.tileManager;


public class GamePanel extends JPanel implements Runnable{

//Screen Settings 

final int originalTitleSize = 16; //16*16 tile default size of characters, title, npcs etc 

final int scale = 3; //Scale the pixals 

public final int titleSize = originalTitleSize * scale; //48 * 48 tiles 

//The max pixels shown on screen
public final int maxScreenCol  = 16; 
public final int maxScreenRow = 12;
public final int screenWidth = titleSize * maxScreenCol; //768 pixels
public final int screenHeight = titleSize * maxScreenRow; // 576 pixels;

//World Settings
public final int maxWorldCol = 50;
public final int maxWorldRow = 50;
public final int worldWidth = titleSize * maxWorldCol;
public final int worldHeight = titleSize * maxScreenRow;


//fps
int fps = 60;

KeyHandler keyH = new KeyHandler();

Thread gameThread;  

public Player player = new Player(this, keyH);

tileManager tileM = new tileManager(this);

public GamePanel(){

    this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
}

public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
}

@Override
public void run(){

    double drawInterval = 1000000000/fps; //draw the graphics every 0.0166 seconds

    double nextDrawTime = System.nanoTime() + drawInterval;

    while(gameThread != null){

        //Update information such as character position 
        update();

        //Draw the screen with the udapted information
        repaint();

        
        try{
            double remainingTime = nextDrawTime - System.nanoTime(); //Check the next update 

            remainingTime = remainingTime/1000000;

            if(remainingTime < 0){

                remainingTime = 0;
            }

            Thread.sleep((long)remainingTime); //pause the game loop until the sleep time is over

            nextDrawTime += drawInterval;
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}

public void update(){

   player.update();

}


public void paintComponent(Graphics g){

    super.paintComponent(g); 

    Graphics2D g2 = (Graphics2D)g;
    
    tileM.draw(g2);

    player.draw(g2);
    
    g2.dispose();

}

}
