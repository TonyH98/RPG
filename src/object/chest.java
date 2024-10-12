package object;

import Entity.Entity;
import Main.GamePanel;

public class chest extends Entity{


public chest(GamePanel gp){

    super(gp);

    name = "Chest";

    down1 = setUp("/objects/key");


}

}
