package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class hearts extends superObject{
    
    GamePanel gp; 

    public hearts(GamePanel gp){
        name = "Hearts";
    
        try{
            image = ImageIO.read((getClass().getResourceAsStream("/objects/heart_full.png")));
            image2 = ImageIO.read((getClass().getResourceAsStream("/objects/heart_half.png")));
            image3 = ImageIO.read((getClass().getResourceAsStream("/objects/heart_blank.png")));
            image = utool.scaleImage(image, gp.titleSize, gp.titleSize);
            image2 = utool.scaleImage(image2, gp.titleSize, gp.titleSize);
            image3 = utool.scaleImage(image3, gp.titleSize, gp.titleSize);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
