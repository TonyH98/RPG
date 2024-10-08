package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;




import Main.GamePanel;

import Main.KeyHandler;
import Main.UI;





public class Player extends Entity{

    UI ui;
    KeyHandler keyH;

    public final int screenX;
    
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.titleSize /2);

        screenY = gp.screenHeight / 2 - (gp.titleSize /2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 26;
        solidArea.height = 28;

        setDefaultValues();
        getPlayerImage();
    }

    public void drawCollisionBox(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
    

    public void setDefaultValues(){

        worldX = gp.titleSize * 23;
        worldY = gp.titleSize * 21;
        speed = 4;
        direction = "down";
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage(){
        up1 = setUp("/player/boy_up_1");
        up2 = setUp("/player/boy_up_2");
        down1 = setUp("/player/boy_down_1");
        down2 = setUp("/player/boy_down_2");
        left1 = setUp("/player/boy_left_1");
        left2 = setUp("/player/boy_left_2");
        right1 = setUp("/player/boy_right_1");
        right2 = setUp("/player/boy_right_2");
    }
    


    public void update() {
        boolean isMoving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
        
        if (isMoving) {
            int actualSpeed = speed;
        
            // Check if sprinting
            if (keyH.sprintPressed) {
                actualSpeed += 3; // Increase the speed when sprinting
            }
        
            // Set the direction based on key input
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }
    
            // Check tile collision
            collisionOn = false;
            gp.checker.checkTile(this);

            //Check object collision
            int objIndex = gp.checker.checkObject(this, true);
            pickUpObject(objIndex);
            
            int npcIndex = gp.checker.checkEntity(this, gp.npc);

            interactNPC(npcIndex);

            //Check event

            gp.eHandler.checkEvent();

            // If collision is false, move the player
            if (!collisionOn) {
                switch (direction) {
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
    
    public void pickUpObject(int index){
        if(index != 999){
            
        }
    }

    public void interactNPC(int i) {
        if (i != 999 && keyH.objecInteraction ) {
                gp.gameState = gp.dialogeState;
                gp.npc[i].speak();      
        }
        else if(gp.gameState == gp.dialogeState){
            gp.gameState = gp.playState;
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

        g2.drawImage(image, screenX, screenY, null);
    }
}
