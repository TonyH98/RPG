package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class key extends superObject{

public key(){
    name = "Key";

    try{
        image = ImageIO.read((getClass().getResourceAsStream("/objects/key.png")));
    }
    catch(IOException e){
        e.printStackTrace();
    }
}

}