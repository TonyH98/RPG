package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font ariel_40, ariel_80B;
    // BufferedImage keyImage;
    public boolean messageOn = false;
    Timer timer = new Timer();
    public String message = "";
    public boolean gameFinished = false;
    public String currentDialogeString = "";
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        ariel_40 = new Font("Ariel", Font.PLAIN, 40);
        ariel_80B = new Font("Ariel", Font.BOLD, 80);


    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;

        // Schedule a task to hide the message after 3 seconds
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                messageOn = false;
            }
        }, 3000);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        
        g2.setFont(ariel_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState){
            //Do playState stuff
        }
        if(gp.gameState == gp.pauseState){

            drawPauseScreen();
        }

        //Dialogue State
        if(gp.gameState == gp.dialogeState){
            drawDialogueScreen();
        }
    }


    public void drawTitleScreen(){
        //Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "Dragon Adventure";

        int x = getXForCenterText(text);
        int y = gp.titleSize * 3;
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        x = gp.screenWidth/2 - (gp.titleSize*2)/2;
        y += gp.titleSize*2;
        g2.drawImage(gp.player.down1, x , y, gp.titleSize * 2, gp.titleSize * 2, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        text = "New Game";
        x =  getXForCenterText(text);
        y += gp.titleSize * 3.2;
        g2.drawString(text, x, y);

        if(commandNum == 0){
            g2.drawString(">", x - gp.titleSize, y);
        }
        text = "Load Game";
        x =  getXForCenterText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);

        if(commandNum == 1){
            g2.drawString(">", x - gp.titleSize, y);
        }

        text = "Quit";
        x =  getXForCenterText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);

        if(commandNum == 2){
            g2.drawString(">", x - gp.titleSize, y);
        }


    }
    public void drawPauseScreen(){
        String text = "PAUSED";

        int x = getXForCenterText(text);

        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){

        //Window
        int x = gp.titleSize * 2;;
        int y = gp.titleSize / 2;
        int width = gp.screenWidth - (gp.titleSize * 4);
        int height = gp.titleSize * 3;

        drawSubWidnow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.titleSize;
        y += gp.titleSize;

        for(String line: currentDialogeString.split("\n")){
            g2.drawString(line, x , y);
            y += 40;
        }

    }

    public void drawSubWidnow(int x, int y, int width, int height){

        Color c = new Color(0, 0, 0);
        g2.setColor(c);

        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);

        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));

        g2.drawRoundRect(x + 2, y + 2, width - 10, height - 10, 25, 25);
    }

    public int getXForCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;

        return x;
    }
}

