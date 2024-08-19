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

    public int checkObject(Entity entity, boolean player){

        int index = 999;

        for(int i = 0 ; i < gp.object.length; i++){

            if(gp.object[i] != null){

                //Get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //Get the object's solid area position
                gp.object[i].solidArea.x = gp.object[i].worldX + gp.object[i].solidArea.x;
                gp.object[i].solidArea.y = gp.object[i].worldY + gp.object[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(gp.object[i].solidArea)){
                       if(gp.object[i].collison == true){
                        entity.collisionOn = true;
                       }
                       if(player == true){
                        index = i;
                       }

                    }
                    break;
                    case "down":
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(gp.object[i].solidArea)){
                        if(gp.object[i].collison == true){
                            entity.collisionOn = true;
                           }
                           if(player == true){
                            index = i;
                           }
                    }
                    break;
                    case "left":
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(gp.object[i].solidArea)){
                        if(gp.object[i].collison == true){
                            entity.collisionOn = true;
                           }
                           if(player == true){
                            index = i;
                           }
                    }
                    break;
                    case "right":
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(gp.object[i].solidArea)){
                        if(gp.object[i].collison == true){
                            entity.collisionOn = true;
                           }
                           if(player == true){
                            index = i;
                           }
                    }
        
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.object[i].solidArea.x = gp.object[i].solidAreaDefaultX;
                gp.object[i].solidArea.y = gp.object[i].solidAreaDefaultY;
            }
        }
        return index;
    }

}
