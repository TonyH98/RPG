package object;

import Entity.Entity;
import Main.GamePanel;

public class healthDrop extends Entity{

public healthDrop(GamePanel gp){
    
    super(gp);

    name = "HealthDrop";

    down1 = setUp("/objects/healthDrop", gp.titleSize, gp.titleSize);
}

}
