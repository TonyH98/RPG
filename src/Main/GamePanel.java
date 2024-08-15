package Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;


public class GamePanel extends JPanel implements Runnable{

//Screen Settings 

final int originalTitleSize = 16; //16*16 tile default size of characters, title, npcs etc 

final int scale = 3; //Scale the pixals 

final int titleSize = originalTitleSize * scale; //48 * 48 tiles 

//The max pixels shown on screen
final int maxScreenCol  = 16; 
final int maxScreenRow = 12;
final int screenWidth = titleSize * maxScreenCol; //768 pixels
final int screenHeight = titleSize * maxScreenRow; // 576 pixels;

//fps
int fps = 60;

KeyHandler keyH = new KeyHandler();

Thread gameThread;  

int plyayerX = 100; 
int playerY = 100; 
int playerSpeed = 4; 

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

    if(keyH.upPressed == true){
        playerY -= playerSpeed;
        playerY = playerY - playerSpeed;
    }
    else if(keyH.downPressed == true){
        playerY += playerSpeed;

    }
    else if(keyH.leftPressed == true){
        plyayerX -= playerSpeed;
    }
    else if(keyH.rightPressed == true){
        plyayerX += playerSpeed;
    }

}


public void paintComponent(Graphics g){

    super.paintComponent(g); 

    Graphics2D g2 = (Graphics2D)g;

    g2.setColor(Color.white);

    g2.fillRect(plyayerX, playerY, titleSize, titleSize);

    g2.dispose();

}

}
