package Main;
import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
       JFrame window = new JFrame();

       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       window.setResizable(false);

       window.setTitle("Dragon Adventure");

       GamePanel gamePanel = new GamePanel();

       window.add(gamePanel);

       window.pack();

       window.setLocationRelativeTo(null);

       window.setVisible(true);

       gamePanel.setUpGame();

       gamePanel.startGameThread(); //Testing

    }
}