package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class chest extends superObject{

GamePanel gp;

public chest(GamePanel gp){
    name = "Chest";

    try{
        image = ImageIO.read((getClass().getResourceAsStream("/objects/key.png")));
        utool.scaleImage(image, gp.titleSize, gp.titleSize);
    }
    catch(IOException e){
        e.printStackTrace();
    }
}

}
