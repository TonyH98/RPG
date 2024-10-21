package Projectile;

import Entity.Entity;
import Main.GamePanel;

public class HealSpell extends Entity{

GamePanel gp;

public HealSpell(GamePanel gp){
    super(gp);

    name = "Healing";

    mp = 2; 

    up1 = setUp("/objects/healSpell" , gp.titleSize, gp.titleSize);
  
}


}