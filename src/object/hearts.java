package object;

import Entity.Entity;
import Main.GamePanel;

public class hearts extends Entity{
    


    public hearts(GamePanel gp){
        super(gp);
        name = "Hearts";
        image = setUp("/objects/heart_full", gp.titleSize, gp.titleSize);
        image2 = setUp("/objects/heart_half", gp.titleSize, gp.titleSize);
        image3 = setUp("/objects/heart_blank", gp.titleSize, gp.titleSize);

    
    }

}
