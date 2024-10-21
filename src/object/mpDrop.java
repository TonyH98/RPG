package object;

import Entity.Entity;
import Main.GamePanel;

public class mpDrop extends Entity{

public mpDrop(GamePanel gp){
    
    super(gp);

    name = "MPDrop";

    down1 = setUp("/objects/mpDrop", gp.titleSize, gp.titleSize);
}

}
