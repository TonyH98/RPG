package  Monster;

import Entity.Entity;
import Main.GamePanel;
import java.util.Random;

public class GreenSlime extends Entity{

GamePanel gp;

Random random;

public GreenSlime(GamePanel gp){
    super(gp);

    this.gp = gp;

    this.random = new Random();
    

    name = "Green Slime";
    eleWeakness = "fire";
    speed = 1;
    maxLife = 4; 
    life = maxLife;

    solidArea.x = 3;
    solidArea.y = 18;
    solidArea.width = 42; 
    solidArea.height = 30;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
    expPoints = 10;
    strength = 1;
    def = 1;
    type = 2;
    containItem = this.random.nextInt(2) + 1;
    System.out.println("Contain Item: " + containItem);
    getImage();
}

public void getImage(){
    up1 = setUp("/Monsters/greenslime_down_1" , gp.titleSize, gp.titleSize);
    up2 = setUp("/Monsters/greenslime_down_2" , gp.titleSize, gp.titleSize);
    left1 = setUp("/Monsters/greenslime_down_1", gp.titleSize, gp.titleSize);
    left2 = setUp("/Monsters/greenslime_down_2", gp.titleSize, gp.titleSize);
    right1 = setUp("/Monsters/greenslime_down_1", gp.titleSize, gp.titleSize);
    right2 = setUp("/Monsters/greenslime_down_2", gp.titleSize, gp.titleSize);
    down1 = setUp("/Monsters/greenslime_down_1", gp.titleSize, gp.titleSize);
    down2 = setUp("/Monsters/greenslime_down_2", gp.titleSize, gp.titleSize);

    
}


public void setAction(){
    actionLockCounter++;

        Random random = new Random();
        if(actionLockCounter == 120){
            int i = random.nextInt(100) + 1; //Pick up a number from 1 to 100;
    
            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
}




}