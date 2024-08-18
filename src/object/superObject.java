package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.GamePanel;

public class superObject {

    public BufferedImage image;

    public String name;

    public boolean collison = false;

    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = worldX - gp.player.worldX + gp.player.screenX;

        int screenY = worldY - gp.player.worldY + gp.player.screenY;


        if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && worldX - gp.titleSize < gp.player.worldX + gp.player.screenX 
        && worldY + gp.titleSize > gp.player.worldY - gp.player.screenY && worldY - gp.titleSize < gp.player.worldY + gp.player.screenY){ //Create a boundry to draw what is necessary

            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
        }

    }
}
