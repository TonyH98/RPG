package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

import Main.KeyHandler;


public class Player extends Entity{

    GamePanel gp;

    KeyHandler keyH;

    public final int screenX;
    
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.titleSize /2);

        screenY = gp.screenHeight / 2 - (gp.titleSize /2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.titleSize * 23;
        worldY = gp.titleSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    

    public void update() {
        boolean isMoving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
        
        if (isMoving) {
            int actualSpeed = speed;
    
            // Check if sprinting
            if (keyH.sprintPressed) {
                actualSpeed += 5; // Increase the speed when sprinting
            }
    
            // Movement based on direction
            if (keyH.upPressed) {
                direction = "up";
                worldY -= actualSpeed;
            } else if (keyH.downPressed) {
                direction = "down";
                worldY += actualSpeed;
            } else if (keyH.leftPressed) {
                direction = "left";
                worldX -= actualSpeed;
            } else if (keyH.rightPressed) {
                direction = "right";
                worldX += actualSpeed;
            }
            
            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //if collision is flase player can move
            if(collisionOn == false){
                switch(direction){
                    case "up":
                    worldY -= actualSpeed;
                    break;
                    case "down":
                    worldY += actualSpeed;
                    break;
                    case "left":
                    worldX -= actualSpeed;
                    break;
                    case "right":
                    worldX += actualSpeed;
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
    }
    

    public void draw(Graphics2D g2){

        BufferedImage image = null;

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

        g2.drawImage(image, screenX, screenY , gp.titleSize, gp.titleSize, null);
    }
}
