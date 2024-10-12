package object;

import Entity.Entity;
import Main.GamePanel;

public class key extends Entity{

    

public key(GamePanel gp){
    super(gp);
    name = "Key";
    down1 = setUp("/objects/key");

}

}
