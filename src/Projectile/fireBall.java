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

    solidArea.height = 30;

    solidAreaDefaultX = solidArea.x;

    solidAreaDefaultY = solidArea.y;

    getImage();
}

public void getImage(){
    up1 = setUp("/objects/fireBall" , gp.titleSize, gp.titleSize);
}


   public void setAction(){

        if(gp.player.direction == "up"){
            direction = "up";
        }
       if(gp.player.direction == "down"){
            direction = "down";
        }
        if(gp.player.direction == "left"){
            direction = "left";
        }
        if(gp.player.direction == "right"){
            direction = "right";
        }

    }


}