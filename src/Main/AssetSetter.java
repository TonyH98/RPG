package Main;

import Monster.GreenSlime;
import java.util.Random;
import object.healthDrop;
import object.mpDrop;


public class AssetSetter {

    GamePanel gp;

    Random random;

    public AssetSetter(GamePanel gp){

        this.gp = gp;
        this.random = new Random();
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
            if (gp.object[i] == null) { 
                int checkLife = gp.player.maxLife - gp.player.life;
                int checkMp = gp.player.maxMp - gp.player.maxMp;
                String itemName = gp.randomItemDrop[this.random.nextInt(2)];
                if(itemName.equals("HealthDrop")){
                gp.object[i] = new healthDrop(gp);
                }
                else {
                gp.object[i] = new mpDrop(gp);
                }
                gp.object[i].worldX = gp.monster[monsterIndex].worldX;
                gp.object[i].worldY = gp.monster[monsterIndex].worldY;
            
                break; 
            }
        }
    }
}

}
