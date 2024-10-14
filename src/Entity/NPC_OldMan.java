package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import java.util.Random;


public class NPC_OldMan extends Entity {

        KeyHandler keyH;
      

public NPC_OldMan(GamePanel gp){
    super(gp);

    direction = "down";
    speed = 1;
    getImage();
    setDiaglogue();
}


   public void getImage(){
        up1 = setUp("/npc/oldman_up_1" , gp.titleSize, gp.titleSize);
        up2 = setUp("/npc/oldman_up_2", gp.titleSize, gp.titleSize);
        down1 = setUp("/npc/oldman_down_1", gp.titleSize, gp.titleSize);
        down2 = setUp("/npc/oldman_down_2", gp.titleSize, gp.titleSize);
        left1 = setUp("/npc/oldman_left_1", gp.titleSize, gp.titleSize);
        left2 = setUp("/npc/oldman_left_2", gp.titleSize, gp.titleSize);
        right1 = setUp("/npc/oldman_right_1", gp.titleSize, gp.titleSize);
        right2 = setUp("/npc/oldman_right_2", gp.titleSize, gp.titleSize);
    }

    public void setDiaglogue(){

        dialogues[0] = "Welcome to the game";
        dialogues[1] = "My name is Jared \nand this is world x";
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

    public void speak(){

       super.speak();

    
    }

}
