import javax.swing.*;

public class App {
    public static int WIDTH = 600;
    public static int HEIGHT = 520;

    public static void main(String[] args){
        JFrame frame = new JFrame();

        frame.setVisible(true);
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Flying UFO!");

        Keyboard keyboard = Keyboard.getInstance();

        frame.addKeyListener(keyboard);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
