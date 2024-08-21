package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class boots extends superObject {


public boots(){
    name = "Boots";

    try{
        image = ImageIO.read((getClass().getResourceAsStream("/objects/boots.png")));
    }
    catch(IOException e){
        e.printStackTrace();
    }
}


}
