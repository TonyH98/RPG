import javax.swing.JPanel;

public class GamePanel extends JPanel{

//Screen Settings 

final int originalTitleSize = 16; //16*16 tile default size of characters, title, npcs etc 

final int scale = 3; //Scale the pixals 

final int titleSize = originalTitleSize * scale; //48 * 48 tiles 

//The max pixels shown on screen
final int maxScreenCol  = 16; 
final int maxScreenRow = 12;
final int screenWidth = titleSize * maxScreenCol; //768 pixels
final int screenHeight = titleSize * maxScreenRow; // 576 pixels;



}
