package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtitltyTool;

public class Entity {

    public int worldX, worldY;

    GamePanel gp;

    public int speed; 

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public String direction;

    public int spriteCounter = 0;

    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0 , 0 , 48, 48); 

    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;

    public int actionLockCounter = 0; 

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){

    }

    public void update(){

        setAction();
        collisionOn = false;
        gp.checker.checkTile(this);
        gp.checker.checkObject(this, false);
        gp.checker.checkPlayer(this);

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        // Handle sprite animation
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = spriteNum == 1 ? 2 : 1;
            spriteCounter = 0;
        }
    
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;

        int screenY = worldY - gp.player.worldY + gp.player.screenY;


        if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && worldX - gp.titleSize < gp.player.worldX + gp.player.screenX 
        && worldY + gp.titleSize > gp.player.worldY - gp.player.screenY && worldY - gp.titleSize < gp.player.worldY + gp.player.screenY){ //Create a boundry to draw what is necessary

            switch(direction){
                case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                    break;
                case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                    break;
                case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                    break;
                case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
    
                }
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
        }
    }

       public BufferedImage setUp(String imageName){
        
        UtitltyTool uTool = new UtitltyTool();

        BufferedImage scaledImage = null;

        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.titleSize, gp.titleSize);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return scaledImage;
        
    }

}
