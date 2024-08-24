package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class key extends superObject{

    GamePanel gp; 

public key(GamePanel gp){
    name = "Key";

    try{
        image = ImageIO.read((getClass().getResourceAsStream("/objects/key.png")));
        utool.scaleImage(image, gp.titleSize, gp.titleSize);
    }
    catch(IOException e){
        e.printStackTrace();
    }
}

}
