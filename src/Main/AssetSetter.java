package Main;

import Monster.GreenSlime;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp = gp;

    }

    public void setObject(){

       

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

        
    }

}
