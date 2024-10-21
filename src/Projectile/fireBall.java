package Projectile;

import Entity.Entity;
import Main.GamePanel;

public class fireBall extends Entity{

GamePanel gp;

public fireBall(GamePanel gp){
    super(gp);

    speed = 3;

    name = "Fire Ball";

    solidArea.x = 5;

    solidArea.y = 20;

    solidArea.width = 42;

    mp = 2; 

    solidArea.height = 30;

    solidAreaDefaultX = solidArea.x;

    solidAreaDefaultY = solidArea.y;

    eleType = "fire";

    up1 = setUp("/objects/fireBall" , gp.titleSize, gp.titleSize);
    up2 = setUp("/objects/fireBall" , gp.titleSize, gp.titleSize);
    left1 = setUp("/objects/fireBall" , gp.titleSize, gp.titleSize);
    left2 = setUp("/objects/fireBall" , gp.titleSize, gp.titleSize);
    right1 = setUp("/objects/fireBall" , gp.titleSize, gp.titleSize);
    right2 = setUp("/objects/fireBall" , gp.titleSize, gp.titleSize);
    down1 = setUp("/objects/fireBall" , gp.titleSize, gp.titleSize);
    down2 = setUp("/objects/fireBall" , gp.titleSize, gp.titleSize);
   
}





public void setAction() {
    // Move the fireball based on its direction
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




}