package object;

import Entity.Entity;
import Main.GamePanel;

public class door extends Entity{



    public door(GamePanel gp){

    super(gp);
    name = "Door";
    down1 = setUp("/objects/door");

    collison = true;

    solidArea.x = 0;
    solidArea.y = 16;
    solidArea.width = 48;
    solidArea.height = 32;
    solidAreaDefaultX = solidArea.x;
    solidAreaDefaultY = solidArea.y;
}

}
