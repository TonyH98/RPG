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

    mapTileNum = new int [gp.maxScreenCol] [gp.maxScreenRow];

    getTitleImage();
    loadMap("/map/map2.txt");
}

public void getTitleImage(){

    try{

        tile[0] = new tile();
        tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));
        tile[1] = new tile();
        tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Brick.png"));
        tile[2] = new tile();
        tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

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

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){ //Will stop once it reaches the maximum as there will no value to read 

            String line = br.readLine();

            while(col < gp.maxScreenCol){
                String numbers[] = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;
                col++;
            }
            if(col == gp.maxScreenCol){
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
    
    int col = 0; 
    int row = 0;
    int x = 0;
    int y = 0;

    while(col < gp.maxScreenCol && row < gp.maxScreenRow){

        int tileNum = mapTileNum[col][row];

        g2.drawImage(tile[tileNum].image, x, y, gp.titleSize, gp.titleSize, null);
        col++;
        x += gp.titleSize;

        if(col == gp.maxScreenCol){
            col = 0;
            x = 0;
            row++;
            y += gp.titleSize;
        }
    }

}

}
