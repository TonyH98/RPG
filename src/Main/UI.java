package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.image.BufferedImage;

import object.key;
public class UI {

    GamePanel gp;
    Font ariel_40, ariel_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    Timer timer = new Timer();
    public String message = "";
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        ariel_40 = new Font("Ariel", Font.PLAIN, 40);
        ariel_80B = new Font("Ariel", Font.BOLD, 80);

        key key = new key(gp);
        keyImage = key.image;
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

        if(gameFinished == true){
            g2.setFont(ariel_40);

            g2.setColor(Color.white);

            String text;

            int TextLength;
            int x;
            int y;

            text = "You found the treasure";

            TextLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

             x = gp.screenWidth/2 - TextLength/2;
             y = gp.screenHeight/2 - (gp.titleSize * 3);

             g2.drawString(text, x, y);


             g2.setFont(ariel_80B);

            g2.setColor(Color.yellow);

            text = "Congratulation you beat the game";

            TextLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

             x = gp.screenWidth/2 - TextLength/2;
             y = gp.screenHeight/2 - (gp.titleSize * 2);

             g2.drawString(text, x, y);

             gp.gameThread = null;

             
        }
        else{
            g2.setFont(ariel_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.titleSize/2, gp.titleSize/2, gp.titleSize, gp.titleSize, null);
            g2.drawString("X " + String.valueOf(gp.player.hashKey),74, 65);
    
            if(messageOn ==true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.titleSize/2, gp.titleSize * 5);
    
            }

        }
        
    }
}

