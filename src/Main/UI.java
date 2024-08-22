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
    Font ariel_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    Timer timer = new Timer();
    public String message = "";

    public UI(GamePanel gp) {
        this.gp = gp;
        ariel_40 = new Font("Ariel", Font.PLAIN, 40);

        key key = new key();
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

