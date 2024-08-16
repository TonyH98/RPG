package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class tileManager {

GamePanel gp;

tile[] tile;

public tileManager(GamePanel gp){

    this.gp = gp;

    tile = new tile[10];

    getTitleImage();
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

public void draw(Graphics2D g2){
    
    int col = 0; 
    int row = 0;
    int x = 0;
    int y = 0;

    while(col < gp.maxScreenCol && row < gp.maxScreenRow){
        g2.drawImage(tile[0].image, x, y, gp.titleSize, gp.titleSize, null);
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
