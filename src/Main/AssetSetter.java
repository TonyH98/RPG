package Main;

import Monster.GreenSlime;
import object.healthDrop;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp = gp;

    }

    public void setObject(){

    //    gp.object[0] = new healthDrop(gp);
    //    gp.object[0].worldX = gp.titleSize * 20;
    //    gp.object[0].worldY = gp.titleSize * 20;

    }

    public void setNPC(){
        // gp.npc[0] = new NPC_OldMan(gp);
        // gp.npc[0].worldX = gp.titleSize * 21;
        // gp.npc[0].worldY = gp.titleSize * 21;

    }


    public void setMonster(){
        gp.monster[0] = new GreenSlime(gp);
        gp.monster[0].worldX = gp.titleSize * 21;
        gp.monster[0].worldY = gp.titleSize * 21;


        gp.monster[1] = new GreenSlime(gp);
        gp.monster[1].worldX = gp.titleSize * 20;
        gp.monster[1].worldY = gp.titleSize * 20;

        
    }

    public void setProjectile(){
        
    }


 public void dropItem(int monsterIndex) {
    if (gp.monster[monsterIndex].life <= 0 && gp.monster[monsterIndex].containItem == 1) {
        for (int i = 0; i < gp.object.length; i++) {
            if (gp.object[i] == null) { // Check for a free slot
                gp.object[i] = new healthDrop(gp);
                gp.object[i].worldX = gp.monster[monsterIndex].worldX;
                gp.object[i].worldY = gp.monster[monsterIndex].worldY;
                break; 
            }
        }
    }
}

}
