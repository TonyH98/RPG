package Main;

import java.awt.Rectangle;

public class EventHandler {

    GamePanel gp;

    Rectangle eventRect;

    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp){
        this.gp = gp; 

        eventRect = new Rectangle();

        eventRect.x = 23;

        eventRect.y = 23;

        eventRect.width = 2;

        eventRect.height = 2;

        eventRectDefaultX = eventRect.x;

        eventRectDefaultY = eventRect.y;


    }

    public void checkEvent(){

        if(hit(27 , 16, "right") == true){
            damagePit(gp.dialogeState);
        }

        if(hit(23, 12, "up")  == true){
            healingPool(gp.dialogeState);
        }
        if(hit(20, 20, "up") == true){
            changeMap(gp.dungeon1);
        }

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){

        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;

        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        eventRect.x = eventCol * gp.titleSize + eventRect.x;

        eventRect.y = eventRow * gp.titleSize + eventRect.y;

        if(gp.player.solidArea.intersects(eventRect)){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;

        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        eventRect.x = eventRectDefaultX;

        eventRect.y = eventRectDefaultY;

        return hit;
    }


    public void damagePit(int gameState){
        gp.gameState = gameState;

        gp.ui.currentDialogeString = "You fall into a pit";

        gp.player.life -= 1;
    }

    public void healingPool(int gameState){
        if(gp.keyH.objecInteraction == true){
            gp.gameState = gameState;
            gp.ui.currentDialogeString = "You drink the water. \nYour life has been recovered";
            gp.player.life = gp.player.maxLife;
        }

       
    }

  public void changeMap(int map) {
    gp.currMap = map;

    // Set player's starting position on the new map
    if (map == gp.dungeon1) {
        gp.player.worldX = 6;
        gp.player.worldY = 6;
    } else if (map == gp.overWorld) {
        gp.player.worldX = 23;
        gp.player.worldY = 21;
    }
}


}
