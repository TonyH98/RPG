package Main;

import object.boots;
import object.chest;
import object.door;
import object.key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp = gp;

    }

    public void setObject(){

        gp.object[0] = new key(gp);
        gp.object[0].worldX = 23 * gp.titleSize;
        gp.object[0].worldY = 7 * gp.titleSize;

        gp.object[1] = new key(gp);
        gp.object[1].worldX = 23 * gp.titleSize;
        gp.object[1].worldY = 40 * gp.titleSize;

        gp.object[2] = new door(gp);
        gp.object[2].worldX = 37 * gp.titleSize;
        gp.object[2].worldY = 7 * gp.titleSize;

        gp.object[3] = new door(gp);
        gp.object[3].worldX = 10 * gp.titleSize;
        gp.object[3].worldY = 9 * gp.titleSize;

        gp.object[4] = new door(gp);
        gp.object[4].worldX = 8 * gp.titleSize;
        gp.object[4].worldY = 28 * gp.titleSize;

        gp.object[5] = new door(gp);
        gp.object[5].worldX = 12 * gp.titleSize;
        gp.object[5].worldY = 22 * gp.titleSize;

        gp.object[6] = new chest(gp);
        gp.object[6].worldX = 10 * gp.titleSize;
        gp.object[6].worldY = 7 * gp.titleSize;

        gp.object[7] = new boots(gp);
        gp.object[7].worldX = 37 * gp.titleSize;
        gp.object[7].worldY = 42 * gp.titleSize;

    }

}
