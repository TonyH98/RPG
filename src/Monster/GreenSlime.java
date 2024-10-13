package  Monster;

import Entity.Entity;
import Main.GamePanel;
import java.util.Random;

public class GreenSlime extends Entity{

public GreenSlime(GamePanel gp){
    super(gp);

    name = "Green Slime";
    speed = 1;
    maxLife = 4; 
    life = maxLife;

    solidArea.x = 3;
    solidArea.y = 18;
    solidArea.width = 42; 
    solidArea.height = 30;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;

    type = 2;

    getImage();
}

public void getImage(){
    up1 = setUp("/Monsters/greenslime_down_1");
    up2 = setUp("/Monsters/greenslime_down_2");
    left1 = setUp("/Monsters/greenslime_down_1");
    left2 = setUp("/Monsters/greenslime_down_2");
    right1 = setUp("/Monsters/greenslime_down_1");
    right2 = setUp("/Monsters/greenslime_down_2");
    down1 = setUp("/Monsters/greenslime_down_1");
    down2 = setUp("/Monsters/greenslime_down_2");
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