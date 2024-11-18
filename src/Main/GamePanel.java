package Main;
import Entity.Entity;
import Entity.Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import java.util.HashMap;
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
public  int maxWorldCol;
public  int maxWorldRow;
public final int worldWidth = titleSize * maxWorldCol;
public final int worldHeight = titleSize * maxScreenRow;

//Game State
public int gameState;
public final int titleState = 0;
public final int playState = 1;
public final int pauseState = 2;
public final int dialogeState = 3;
public final int spellMenuState = 4;


//Current Map
public int currMap;
public final int overWorld = 0;
public final int dungeon1 = 1;

//fps
int fps = 60;

KeyHandler keyH = new KeyHandler(this);

Thread gameThread;  

public CollisionChecker checker = new CollisionChecker(this);

public Player player = new Player(this, keyH);

public Entity npc[] = new Entity[10];

public Entity monster[] = new Entity[20];

public Entity projectile[] = new Entity[2];

public String[] randomItemDrop = {"MPDrop", "HealthDrop"};

public UI ui = new UI(this);

public tileManager tileM = new tileManager(this);

Sound music = new Sound();
Sound se = new Sound ();

public AssetSetter assetSetter = new AssetSetter(this);


public Entity object[] = new Entity[10]; //Display only 10 objects at a time;



ArrayList <Entity> entityList = new ArrayList<>();

public  HashMap <String, Entity> mapObject = new HashMap<>();

public EventHandler eHandler = new EventHandler(this);

public GamePanel(){

    this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
}

public void setUpGame(){
    assetSetter.setObject();
    assetSetter.setNPC();
    assetSetter.setMonster();
    assetSetter.setProjectile();
    playMusic(0);
    gameState = titleState;
    currMap = overWorld;

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
   
    if(gameState == playState){
        player.update();

        for(int i = 0 ; i < npc.length; i++){
            if(npc[i] != null){
                npc[i].update();
            }
        }
        for(int i = 0 ; i < monster.length ; i++){
            if(monster[i] != null){
                monster[i].update();
                
                 if(monster[i].life <= 0 && monster[i].containItem == 1) {
                    System.out.println("Monster Index " + monster[i]);
                    assetSetter.dropItem(i);
                    monster[i] = null;
                }
                else if(monster[i].life <= 0 && monster[i].containItem == 2){
                    monster[i] = null;
                }
            }
        }
     for(int i = 0 ; i < projectile.length; i++){
            if(projectile[i] != null){
                projectile[i].update();
            }
        }
    }
    if(gameState == pauseState){

    }

}


public void paintComponent(Graphics g){

    super.paintComponent(g); 

    Graphics2D g2 = (Graphics2D)g;
    
    //Debug
    long drawStart = 0;
    if(keyH.debugK == true){

        drawStart = System.nanoTime();
    }

    //title screen

    if(gameState == titleState){
        ui.draw(g2);
    }
    
    else{
        //tile
        tileM.draw(g2);

        //Add entities to the list
        entityList.add(player);
        for(int i = 0 ; i < npc.length; i++){
            if(npc[i] != null){
                entityList.add(npc[i]);
            }
        }

        for(int i = 0 ; i < object.length; i++){
            if(object[i] != null){
                entityList.add(object[i]);
            }
        }

         for(int i = 0 ; i < monster.length; i++){
            if(monster[i] != null){
                entityList.add(monster[i]);
            }
        }

        for(int i = 0 ; i < projectile.length; i++){
            if(projectile[i] != null){
                entityList.add(projectile[i]);
            }
        }
        
       Collections.sort(entityList, new Comparator<Entity>(){

        public int compare (Entity e1, Entity e2){

            int result = Integer.compare(e1.worldY, e2.worldY);
            return result;

        }
       });


        for(int i = 0 ; i < entityList.size(); i++){
            entityList.get(i).draw(g2);
        }

        entityList.clear();
        
        //UI
        ui.draw(g2);

    }

    



    if(keyH.debugK == true){
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        g2.setColor(Color.white);
        g2.drawString("Draw Time: " + passed, 10 , 400);
        System.out.println("Draw Time " + passed);

    }

    g2.dispose();

}

public void playMusic(int i){

music.setFile(i);
music.play();
music.loop();
}

public void stopMusic()
{
    music.stop();
}

public void playSE(int i){
    se.setFile(i);
    se.play();
}

}
