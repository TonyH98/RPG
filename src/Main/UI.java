package Main;

import Entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import object.hearts;

public class UI{

    GamePanel gp;
    Graphics2D g2;
    Font ariel_40, ariel_80B;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    Timer timer = new Timer();
    public String message = "";
    public boolean gameFinished = false;
    public String currentDialogeString = "";
    public int commandNum = 0;
    BufferedImage heart_full, heart_half, heart_blank;

    private long lastContactTime = 0;
    

    public UI(GamePanel gp) {
        this.gp = gp;
        ariel_40 = new Font("Ariel", Font.PLAIN, 40);
        ariel_80B = new Font("Ariel", Font.BOLD, 80);

        Entity heart = new hearts(gp);

        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;

        // Schedule a task to hide the message after 3 seconds
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                messageOn = false;
            }
        }, 3000);
    }



   public void draw(Graphics2D g2) {

    this.g2 = g2;
    g2.setFont(ariel_40);
    g2.setColor(Color.white);
    
    if(gp.gameState == gp.titleState){
        drawTitleScreen();
    }

    if(gp.gameState == gp.playState){
        
        drawPlayerLife();
        
            drawEnemyLife();
            lastContactTime = System.currentTimeMillis();  
        
        
        if (System.currentTimeMillis() - lastContactTime > 5000) { 
            gp.player.enemyHitFlag = false;  
        }
    }

    if(gp.gameState == gp.pauseState){
        drawPlayerLife();
        drawMenueScreen();
    }

    if(gp.gameState == gp.dialogeState){
        drawPlayerLife();
        drawDialogueScreen();
    }
}
    public void drawPlayerLife(){
        int x = gp.titleSize/2;
        int y = gp.titleSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.titleSize;
        }

        x = gp.titleSize/2;
        y = gp.titleSize/2;
        i = 0;

        while(i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y , null);
            }
            i++;
            x += gp.titleSize;
        }
    }

public void drawEnemyLife(){
    int monsterIndex = gp.checker.checkEntity(gp.player, gp.monster);
    
    if(monsterIndex != 999 && gp.player.enemyHitFlag == true) {  // Only draw if a valid monster is detected
        int x = (gp.screenWidth - gp.titleSize) / 2;

        int y = gp.titleSize;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));

        g2.setColor(Color.RED);

        String monstLife = gp.monster[monsterIndex].life + "/" + gp.monster[monsterIndex].maxLife;
        g2.drawString(monstLife, x, y);
    }
}




    public void drawTitleScreen(){
        //Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "Dragon Adventure";

        int x = getXForCenterText(text);
        int y = gp.titleSize * 3;
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        x = gp.screenWidth/2 - (gp.titleSize*2)/2;
        y += gp.titleSize*2;
        g2.drawImage(gp.player.down1, x , y, gp.titleSize * 2, gp.titleSize * 2, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        text = "New Game";
        x =  getXForCenterText(text);
        y += gp.titleSize * 3.2;
        g2.drawString(text, x, y);

        if(commandNum == 0){
            g2.drawString(">", x - gp.titleSize, y);
        }
        text = "Load Game";
        x =  getXForCenterText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);

        if(commandNum == 1){
            g2.drawString(">", x - gp.titleSize, y);
        }

        text = "Quit";
        x =  getXForCenterText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);

        if(commandNum == 2){
            g2.drawString(">", x - gp.titleSize, y);
        }


    }
    // public void drawPauseScreen(){
    //     String text = "PAUSED";

    //     int x = getXForCenterText(text);

    //     int y = gp.screenHeight / 2;

    //     g2.drawString(text, x, y);
    // }

    public void drawDialogueScreen(){

        //Window
        int x = gp.titleSize * 2;;
        int y = gp.titleSize / 2;
        int width = gp.screenWidth - (gp.titleSize * 4);
        int height = gp.titleSize * 3;

        drawSubWidnow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.titleSize;
        y += gp.titleSize;

        for(String line: currentDialogeString.split("\n")){
            g2.drawString(line, x , y);
            y += 40;
        }

    }

       public void drawMenueScreen(){

        //Window
        int x = gp.titleSize * 2;;
        int y = gp.titleSize / 2;
        int width = gp.screenWidth - (gp.titleSize * 4);
        int height = gp.titleSize * 8;

        drawSubWidnow(x, y, width, height);
       
        x += gp.titleSize;
        y += gp.titleSize;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
        String text;

        text = "Player Stats";
        
        y += gp.titleSize;
        g2.drawString(text, x, y);

        if(commandNum == 0){
            g2.drawString(">", x - gp.titleSize, y);
        }
        text = "Key Items";
  
        y += gp.titleSize;
        g2.drawString(text, x, y);

        if(commandNum == 1){
            g2.drawString(">", x - gp.titleSize, y);
        }

        text = "Consumable";
        y += gp.titleSize;
        g2.drawString(text, x, y);

        if(commandNum == 2){
            g2.drawString(">", x - gp.titleSize, y);
        }


    }

    public void drawSubWidnow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0);
        g2.setColor(c);

        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);

        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));

        g2.drawRoundRect(x + 2, y + 2, width - 10, height - 10, 25, 25);
    }

    public int getXForCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;

        return x;
    }


   
}

