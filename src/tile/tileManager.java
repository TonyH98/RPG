package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import Main.GamePanel;

public class tileManager {

GamePanel gp;

tile[] tile;

int mapTileNum[][];

public tileManager(GamePanel gp){

    this.gp = gp;

    tile = new tile[10];

    mapTileNum = new int [gp.maxWorldCol] [gp.maxWorldRow];

    getTitleImage();
    loadMap("/map/world01.txt");
}

public void getTitleImage(){

    try{

        tile[0] = new tile();
        tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

        tile[1] = new tile();
        tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Brick.png"));

        tile[2] = new tile();
        tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

        tile[3] = new tile();
        tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));

        tile[4] = new tile();
        tile[4].image = ImageIO.read(getClass().getResource("/tiles/sand.png"));
        
        tile[5] = new tile();
        tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

    }
    catch(IOException e){
        e.printStackTrace();
    }

}

public void loadMap(String map){
    try{

        InputStream is = getClass().getResourceAsStream(map); //Grabbing the text file

        BufferedReader br = new BufferedReader(new InputStreamReader(is)); //Reading the text file

        int col = 0; //represent the col within the text file
        int row = 0; //represents the row within the text file

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){ //Will stop once it reaches the maximum as there will no value to read 

            String line = br.readLine();

            while(col < gp.maxWorldCol){
                String numbers[] = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;
                col++;
            }
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }

        br.close();
    }
    catch(Exception e){

    }
}

public void draw(Graphics2D g2){
    
    int worldCol = 0; 
    int worldRow = 0;

    while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

        int tileNum = mapTileNum[worldCol][worldRow];

        int worldX = worldCol * gp.titleSize;

        int worldY = worldRow * gp.titleSize;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;

        int screenY = worldY - gp.player.worldY + gp.player.screenY;


        if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && worldX - gp.titleSize < gp.player.worldX + gp.player.screenX 
        && worldY + gp.titleSize > gp.player.worldY - gp.player.screenY && worldY - gp.titleSize < gp.player.worldY + gp.player.screenY){ //Create a boundry to draw what is necessary

            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
        }
        worldCol++;


        if(worldCol == gp.maxWorldCol){
            worldCol = 0;

            worldRow++;
  
        }
    }

}

}
