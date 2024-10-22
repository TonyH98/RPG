package Main;

import Entity.Entity;
import Projectile.HealSpell;
import Projectile.fireBall;
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
    public int subCommandNum = 0;
    BufferedImage heart_full, heart_half, heart_blank;
    BufferedImage healImage;
    BufferedImage fireImage;
    private long lastContactTime = 0;
    

    public UI(GamePanel gp) {
        this.gp = gp;
        ariel_40 = new Font("Ariel", Font.PLAIN, 40);
        ariel_80B = new Font("Ariel", Font.BOLD, 80);

        Entity heart = new hearts(gp);

        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        Entity healSpell = new HealSpell(gp);
        healImage = healSpell.up1;

        Entity fireSpell = new fireBall(gp);
        fireImage = fireSpell.up1;
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
    if(gp.gameState == gp.spellMenuState){
        drawPlayerLife();
        drawMenueScreen();
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

    // Window
    int x = gp.titleSize * 2;
    int y = gp.titleSize / 2;
    int width = gp.screenWidth - (gp.titleSize * 3);
    int height = gp.titleSize * 8;

    drawSubWidnow(x, y, width, height);

    // Adjust for vertical line
    int lineX = x + 250; // Position the line after the menu options
    int lineYStart = y + gp.titleSize; // Start point for the line (just below the title)
    int lineYEnd = y + height - gp.titleSize; // End point for the line

    drawLine(lineX, lineYStart, lineX, lineYEnd);

    x += gp.titleSize;
    y += gp.titleSize;

    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25F));
    String text;

    text = "Player Stats";
    g2.drawString(text, x, y);

    // Player sprite
    if (commandNum == 0) {
        g2.drawString(">", x - gp.titleSize, y);
        
        // Draw player character sprite
        int spriteX = (x * 2) + 150;
        int spriteY = y - 40;
        g2.drawImage(gp.player.down1, spriteX, spriteY, gp.titleSize + 30, gp.titleSize + 30, null);

        // Draw player life right under the sprite
        text = "HP :  " + gp.player.life + " / " + gp.player.maxLife;
        int lifeX = spriteX + 15; // Slightly offset to center the text under the sprite
        int lifeY = spriteY + gp.titleSize + 60; // Position right under the sprite
        g2.drawString(text, lifeX, lifeY);

        text = "MP :  " + gp.player.mp;
         lifeX = spriteX + 15; // Slightly offset to center the text under the sprite
         lifeY = spriteY + gp.titleSize + 100; // Position right under the sprite
        g2.drawString(text, lifeX, lifeY);

        // Additional player stats
        text = "Strength :  " + gp.player.strength;
        lifeX = spriteX + 15; 
        lifeY = spriteY + gp.titleSize + 140; 
        g2.drawString(text, lifeX, lifeY);

        text = "Def :  " + gp.player.def;
        lifeX = spriteX + 15; 
        lifeY = spriteY + gp.titleSize + 180; 
        g2.drawString(text, lifeX, lifeY);

        text = "Level :  " + gp.player.currLvl;
        lifeX = spriteX + 15; 
        lifeY = spriteY + gp.titleSize + 220; 
        g2.drawString(text, lifeX, lifeY);

        text = "Next Level :  " + gp.player.currentExp + " / " + gp.player.currLvl * 3;
        lifeX = spriteX + 15; 
        lifeY = spriteY + gp.titleSize + 260; 
        g2.drawString(text, lifeX, lifeY);
    }


    text = "Spells";
    y += gp.titleSize;
    g2.drawString(text, x, y);

if (commandNum == 1) {
    g2.drawString(">", x - gp.titleSize, y);
    int spriteX = (x * 2) + 130;
    int spriteY = y - 40;

 
    // Spell 1: Heal Image
    if (subCommandNum == 0) {
        // Draw a white border around the heal image
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3)); // Set the border thickness
        g2.drawRoundRect(spriteX - 5, spriteY - 5, gp.titleSize + 10, gp.titleSize + 10, 15, 15); // Adjust size and corners

        text = "A healing spell that restore 1 HP with each use.\n It costs 2 MP to use.";
        for(String line: text.split("\n")){
        g2.drawString(line, spriteX -20 , spriteY + 200);
            
        }
    }
    g2.drawImage(healImage, spriteX, spriteY, gp.titleSize, gp.titleSize, null);

    // Spell 2: Fireball Image
    if (subCommandNum == 1) {
        // Draw a white border around the fireball image
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3)); // Set the border thickness
        g2.drawRoundRect(spriteX + 95, spriteY - 5, gp.titleSize + 10, gp.titleSize + 10, 15, 15); // Adjust size and corners
    }
    g2.drawImage(fireImage, spriteX + 100, spriteY, gp.titleSize, gp.titleSize, null);
}


    // Key Items
    text = "Key Items";
    y += gp.titleSize;
    g2.drawString(text, x, y);

    if (commandNum == 2) {
        g2.drawString(">", x - gp.titleSize, y);
    }

    // Consumable
    text = "Consumable";
    y += gp.titleSize;
    g2.drawString(text, x, y);

    if (commandNum == 3) {
        g2.drawString(">", x - gp.titleSize, y);
    }

      text = "Exist";
    y += gp.titleSize;
    g2.drawString(text, x, y);

    if (commandNum == 4) {
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

  public void drawLine(int x, int y, int width, int height){

         g2.setColor(Color.white);

        // Draw a straight line (x1, y1) to (x2, y2)
        g2.drawLine(x , y, width, height); // Example: from (50, 50) to (200, 200)


  }


    public int getXForCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;

        return x;
    }


    public void descBreak(String text, int maxWidth, int height){

        

    }

   
}

