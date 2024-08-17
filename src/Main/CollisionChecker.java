package Main;

import Entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){

        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;

        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;

        int entityTopWorldY = entity.worldY + entity.solidArea.y;

        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.titleSize;

        int entityRightCol = entityRightWorldX/gp.titleSize;

        int entityTopRow = entityTopWorldY/gp.titleSize;

        int entityBottomRow = entityBottomWorldY/gp.titleSize;

        int tileNum, tileNum2;

        switch(entity.direction){
            case "up":
            entityTopRow = (entityTopWorldY - entity.speed)/gp.titleSize;
            tileNum = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

            if(gp.tileM.tile[tileNum].collison == true || gp.tileM.tile[tileNum2].collison == true){
                entity.collisionOn = true;
            }
            break;
            case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.titleSize;
            tileNum = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

            if(gp.tileM.tile[tileNum].collison == true || gp.tileM.tile[tileNum2].collison == true){
                entity.collisionOn = true;
            }
            break;
            case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.titleSize;
            tileNum = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

            if(gp.tileM.tile[tileNum].collison == true || gp.tileM.tile[tileNum2].collison == true){
                entity.collisionOn = true;
            }
            break;
            case "right":
            entityRightCol = (entityRightWorldX + entity.speed)/gp.titleSize;
            tileNum = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

            if(gp.tileM.tile[tileNum].collison == true || gp.tileM.tile[tileNum2].collison == true){
                entity.collisionOn = true;
            }
            break;
        }
    }

}
