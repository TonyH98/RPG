package tile;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;


import Main.GamePanel;
import Main.UtitltyTool;

public class tileManager {

GamePanel gp;

public tile[] tile;

public int mapTileNum[][];

public tileManager(GamePanel gp){

    this.gp = gp;

    tile = new tile[10];

    mapTileNum = new int [gp.maxWorldCol] [gp.maxWorldRow];

    getTitleImage();
    loadMap("/map/world01.txt");
}

public void getTitleImage(){


        setUp(0, "/grass", false);

        setUp(1, "/Brick", false);

        setUp(2, "/water", true);

        setUp(3, "/sand", false);

        setUp(4, "/tree", true);

        setUp(5, "/earth", false);

}

public void setUp(int index, String imagePath, boolean collison){

    UtitltyTool uTool = new UtitltyTool();

    try{
        tile[index] = new tile();
        tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles" + imagePath + ".png"));
        tile[index].image = uTool.scaleImage(tile[index].image, gp.titleSize, gp.titleSize);
        tile[index].collison = collison;
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

            g2.drawImage(tile[tileNum].image, screenX, screenY, null);
        }
        worldCol++;


        if(worldCol == gp.maxWorldCol){
            worldCol = 0;

            worldRow++;
  
        }
    }

}

}
