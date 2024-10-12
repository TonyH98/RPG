package object;

import Entity.Entity;
import Main.GamePanel;

public class hearts extends Entity{
    


    public hearts(GamePanel gp){
        super(gp);
        name = "Hearts";
        image = setUp("/objects/heart_full");
        image2 = setUp("/objects/heart_half");
        image3 = setUp("/objects/heart_blank");

    
    }

}
