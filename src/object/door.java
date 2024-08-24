package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class door extends superObject{

    GamePanel gp; 

    public door(GamePanel gp){
    name = "Door";

    try{
        image = ImageIO.read((getClass().getResourceAsStream("/objects/door.png")));

        utool.scaleImage(image, gp.titleSize, gp.titleSize);
    }
    catch(IOException e){
        e.printStackTrace();
    }
    collison = true;
}

}
