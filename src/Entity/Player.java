package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.UI;
import Projectile.HealSpell;
import Projectile.fireBall;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;





public class Player extends Entity{

    UI ui;

    KeyHandler keyH;

    private int hashKey;

    public final int screenX;
    
    public final int screenY;

    public int healingMp;
  

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

        attackArea.width = 36;
        attackArea.height = 36; 
        setDefaultValues();
        getPlayerImage();
        getPlayerAtkImage();
        Entity healing = new HealSpell(gp);
        
        healingMp = healing.mp;
    }

    public void drawCollisionBox(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }
    

    public void setDefaultValues(){
        gp.currMap = gp.overWorld;
        worldX = gp.titleSize * 23;
        worldY = gp.titleSize * 21;
        currentExp = 0;
        currLvl = 1;
        speed = 4;
        strength = 2;
        def = 2;
        direction = "down";
        currSpell = "Fire Ball";
        maxLife = 6;
        life = maxLife;
        mp = 8;
        maxMp = mp;
    }

    public void getPlayerImage(){
        up1 = setUp("/player/boy_up_1", gp.titleSize, gp.titleSize);
        up2 = setUp("/player/boy_up_2" , gp.titleSize, gp.titleSize);
        down1 = setUp("/player/boy_down_1" , gp.titleSize, gp.titleSize);
        down2 = setUp("/player/boy_down_2" , gp.titleSize, gp.titleSize);
        left1 = setUp("/player/boy_left_1", gp.titleSize, gp.titleSize);
        left2 = setUp("/player/boy_left_2" , gp.titleSize, gp.titleSize);
        right1 = setUp("/player/boy_right_1" , gp.titleSize, gp.titleSize);
        right2 = setUp("/player/boy_right_2" , gp.titleSize, gp.titleSize);
    }

    public void getPlayerAtkImage(){
        atkU1 = setUp("/player/boy_attack_up_1", gp.titleSize, gp.titleSize * 2);
        atkU2 = setUp("/player/boy_attack_up_2" , gp.titleSize, gp.titleSize * 2);
        atkD1 = setUp("/player/boy_attack_down_1" , gp.titleSize, gp.titleSize * 2);
        atkD2 = setUp("/player/boy_attack_down_2", gp.titleSize, gp.titleSize * 2);
        atkL1 = setUp("/player/boy_attack_left_1", gp.titleSize * 2, gp.titleSize);
        atkL2 = setUp("/player/boy_attack_left_2", gp.titleSize * 2, gp.titleSize);
        atkR1 = setUp("/player/boy_attack_right_1", gp.titleSize * 2, gp.titleSize);
        atkR2 = setUp("/player/boy_attack_right_2", gp.titleSize * 2, gp.titleSize);
    }
    


public void update() {
    boolean isMoving = keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed;
    
    // Check if attacking and handle attack logic
    if (attacking) {
        attacking();  // Handle attack if in attack state
    }
    else if(projectileAtk && gp.player.currSpell == "Fire Ball"){
        fireBall();
        
    }
    else if(projectileAtk && gp.player.currSpell == "Healing"){
        healMethod();
    }
    // Only handle movement if not attacking
    else if (isMoving) {
        int actualSpeed = speed;

        // Check if sprinting
        if (keyH.sprintPressed) {
            actualSpeed += 3;  // Increase the speed when sprinting
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

        // Check for collisions
        collisionOn = false;
        gp.checker.checkTile(this);

        // Check object collisions and interactions
        int objIndex = gp.checker.checkObject(this, true);
        pickUpObject(objIndex);

        int npcIndex = gp.checker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        int monsterIndex = gp.checker.checkEntity(this, gp.monster);
        contactMonster(monsterIndex);

        gp.eHandler.checkEvent();

        // Move the player if no collision occurs
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

    // Handle invincibility frames
    if (invincible) {
        invincibleCounter++;
        if (invincibleCounter > 60) {
            invincible = false;
            invincibleCounter = 0;
        }
    }

    // Handle attack if 'J' key is pressed
    if (keyH.objecInteraction && !attacking) {
        attacking = true;
    }

    else if(keyH.projectileK && !attacking){
        projectileAtk = true;
    }
}

    

    public void attacking(){
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;


            //Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust players worldX/Y for the attackArea
            switch(direction){
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            //Attack Hitbox becomes solid
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //Check monster collison with the updated worldX, worldY, and solid Area
            int monsterIndex = gp.checker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
             int damage = Math.max(1, (int)Math.round(gp.player.strength * 1.2) - (int)Math.round(gp.monster[monsterIndex].def * 1.2));
            damageMonster(monsterIndex, damage);
            System.out.println("Damage: " + damage);
            }
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
            
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }

        
    }

public void fireBall() {

    // Check if player has enough MP for the spell
    if(gp.player.currSpell.equals("Fire Ball") && gp.player.mp >= 2) {
        spriteCounter++;

        // Check if there's already an active projectile
        if (gp.projectile[0] == null) {
            // Create the fireball projectile
            gp.projectile[0] = new fireBall(gp);
            gp.projectile[0].worldX = this.worldX;
            gp.projectile[0].worldY = this.worldY;
            gp.projectile[0].direction = this.direction;
            gp.player.mp -= gp.projectile[0].mp;  // Deduct MP cost
        }
        gp.projectile[0].setAction();
        // Check for collision with monsters
        int monsterIndex = gp.checker.checkEntity(gp.projectile[0], gp.monster);
        if (monsterIndex != 999) {
            int damage = Math.max(1, (int) Math.round(gp.player.strength * 1.2) - (int) Math.round(gp.monster[monsterIndex].def * 1.2));

            // Handle elemental weakness for fire spells
            if (gp.monster[monsterIndex].eleWeakness.equals("fire") && gp.projectile[0].eleType.equals("fire")) {
                damageMonster(monsterIndex, damage + 2);
                System.out.println("Damage: " + (damage + 2));
            } else {
                damageMonster(monsterIndex, damage);
                System.out.println("Damage: " + damage);
            }
            gp.projectile[0] = null;  // Remove projectile after hitting the monster
        }

        // Reset projectile attack after a short delay
        if (spriteCounter > 30) {
            spriteNum = 1;
            spriteCounter = 0;
            gp.projectile[0] = null;
            projectileAtk = false;
        }
    } else {
        // If MP is less than 2, reset the attack and ensure no projectile is fired
        projectileAtk = false;
        gp.projectile[0] = null;
        
    }
}

public void healMethod(){
    if(gp.player.currSpell.equals("Healing") && gp.player.mp >= 2){
        gp.player.life++;
        gp.player.mp -= healingMp;
    }
    else{
        projectileAtk = false;
    }

}


    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int damage){
        if(i != 999){
            if(gp.monster[i].invincible == false){
                
                //Damage Formula

                enemyHitFlag = true;
                gp.monster[i].life -= damage;
                gp.monster[i].invincible = true;

                if(gp.monster[i].life <= 0){
                
                
                // Add experience points to the player
                gp.player.currentExp += gp.monster[i].expPoints;
                
                
                // Handle level-up logic (if applicable)
                checkLevel();
                
            }

            }
        }
        else{
            System.out.print("Miss");
            enemyHitFlag = false;
        }
    }

    public void pickUpObject(int index){
         if(index != 999){
        String objectName = gp.object[index].name;
        switch(objectName){
            case "Key":
                hashKey++;
                gp.object[index] = null;
                break;
            case "Door":
                if (hashKey > 0) {
                    gp.object[index] = null;
                    hashKey--;  
                } else {
                    gp.ui.showMessage("You need a key to open this door!");
                }
                break;
            case "Chest":
            break;

            case "HealthDrop":
                gp.player.life += 1;
                gp.object[index] = null;
            break;

            case "MPDrop":
                gp.player.mp += 1;
                gp.object[index] = null;
            break;
        }
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
      
        else if(keyH.objecInteraction){
          attacking = true;

        
        }
    }
    

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction){
            case "up":
            if(attacking == false ){
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }

            }
             if(attacking == true ){
                tempScreenY = screenY - gp.titleSize;
                if(spriteNum == 1){
                    image = atkU1;
                }
                if(spriteNum == 2){
                    image = atkU2;
                }
            }
            break;
            case "down":
           if(attacking == false){
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
            }
             if(attacking == true ){
                 if(spriteNum == 1){
                    image = atkD1;
                }
                if(spriteNum == 2){
                    image = atkD2;
                }
            }
                break;
            case "left":
            if(attacking == false){
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
            }
            if(attacking == true){
                tempScreenX = screenX - gp.titleSize;
                 if(spriteNum == 1){
                    image = atkL1;
                }
                if(spriteNum == 2){
                    image = atkL2;
                }
            }
                break;
            case "right":
            if(attacking == false){
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;

                }
            }
             if(attacking == true){
                
                  if(spriteNum == 1){
                    image = atkR1;
                }
                if(spriteNum == 2){
                    image = atkR2;
                }
            }
                break;
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

    }

    public void checkLevel(){
        
     int nextLvl = gp.player.currLvl * 3;
        if(gp.player.currentExp >= nextLvl){
            int remainingExp = gp.player.currentExp - nextLvl;
            gp.player.currLvl++;
            gp.player.maxLife++;
            gp.player.strength += 2;
            gp.player.def += 2;
            gp.player.mp += 2;
            gp.player.life = gp.player.maxLife;
            gp.player.mp = gp.player.maxMp;
            gp.player.currentExp = remainingExp;
        }
    }
}
