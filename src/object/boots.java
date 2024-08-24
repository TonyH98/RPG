package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class boots extends superObject {

GamePanel gp; 

public boots(GamePanel gp){
    name = "Boots";

    try{
        image = ImageIO.read((getClass().getResourceAsStream("/objects/boots.png")));
        utool.scaleImage(image, gp.titleSize, gp.titleSize);
    }
    catch(IOException e){
        e.printStackTrace();
    }
}


}
