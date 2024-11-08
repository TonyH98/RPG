package object;

import Entity.Entity;
import Main.GamePanel;

public class stairs extends Entity{

    

public stairs(GamePanel gp){
    super(gp);
    name = "stairs";
    down1 = setUp("/objects/stairs", gp.titleSize, gp.titleSize);

}

}
