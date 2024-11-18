package tile;

import Main.GamePanel;
import Main.UtitltyTool;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class tileManager {

GamePanel gp;

public tile[] tile;

public int mapTileNum[][];

public tileManager(GamePanel gp){

    this.gp = gp;

    tile = new tile[10];

    mapTileNum = new int [gp.maxWorldCol] [gp.maxWorldRow];

    getTitleImage();

 
    

    if(gp.currMap == 0){
        loadMap("/map/world01.txt");
    }
    if(gp.currMap == 1){
        loadMap("/map/map.txt");

    }
    
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

public void loadMap(String map) {
    try {
        InputStream is = getClass().getResourceAsStream(map);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Count the rows and columns in the map file
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        gp.maxWorldRow = lines.size();
        gp.maxWorldCol = lines.get(0).split(" ").length;

        // Initialize mapTileNum with the correct dimensions
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        // Parse the map data
        for (int row = 0; row < gp.maxWorldRow; row++) {
            String[] numbers = lines.get(row).split(" ");
            for (int col = 0; col < gp.maxWorldCol; col++) {
                mapTileNum[col][row] = Integer.parseInt(numbers[col]);
            }
        }

        br.close();
    } catch (Exception e) {
        e.printStackTrace();
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
