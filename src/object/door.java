package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class door extends superObject{

    public door(){
    name = "Door";

    try{
        image = ImageIO.read((getClass().getResourceAsStream("/objects/door.png")));
    }
    catch(IOException e){
        e.printStackTrace();
    }
}

}
