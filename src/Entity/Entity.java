package Entity;

import Main.GamePanel;
import Main.UtitltyTool;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity {

    public int worldX, worldY;

    GamePanel gp;

    public int speed; 

    public boolean enemyHitFlag = false;

    public boolean invincible = false;

    public int containItem;

    public int invincibleCounter = 0;

    public int currentExp;

    public int currLvl;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public BufferedImage atkU1, atkU2, atkD1, atkD2, atkL1, atkL2, atkR1, atkR2;

    public String eleType;

    public String eleWeakness;

    public Rectangle attackArea = new Rectangle(0 , 0 , 0 , 0);

    public String direction = "down";

    public int spriteCounter = 0;

    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0 , 0 , 48, 48); 

    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;

    public int actionLockCounter = 0; 

    public BufferedImage image, image2, image3;

    public String name;

    public boolean collison = false;
    
    public int expPoints;
    String dialogues[] = new String[20];

    int dialogueIndex = 0;

    public int maxLife;

    public int life;

    public int strength;

    public int def;

    public int type;

    public boolean attacking = false;

    public boolean projectileAtk = false;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){

    }

    public void speak(){

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogeString = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.player.direction){
            case "up":
            direction = "down";
            break;
            case "down":
            direction = "up";
            break;
            case "left":
            direction = "right";
            break;
            case "right":
            direction = "left";
            break;
        }
        
    }

    public void update(){
        int monsterIndex = gp.checker.checkEntity(this, gp.monster);
        setAction();
        collisionOn = false;
        gp.checker.checkTile(this);
        gp.checker.checkObject(this, false);
        gp.checker.checkEntity(this, gp.npc);
        // gp.checker.checkEntity(this, gp.projectile);
        gp.checker.checkEntity(this, gp.monster);
        boolean checkPlayerContact = gp.checker.checkPlayer(this);

        if(this.type == 2 && checkPlayerContact == true){
            if(gp.player.invincible == false && monsterIndex != 999){
                int damage = Math.max(1, (int)Math.round(gp.player.strength * 1.2) - (int)Math.round(gp.monster[monsterIndex].def * 1.2));
                gp.player.life -= damage;
                gp.player.invincible = true;
            }
        }

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

           if (invincible) {
        invincibleCounter++;
        if (invincibleCounter > 40) {
            invincible = false;
            invincibleCounter = 0;
        }
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
            
            if(invincible == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

       public BufferedImage setUp(String imageName, int width, int height){
        
        UtitltyTool uTool = new UtitltyTool();

        BufferedImage scaledImage = null;

        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, width, height);
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return scaledImage;
        
    }

}
