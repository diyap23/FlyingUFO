import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private final Game game;

    public GamePanel() {
        game = new Game();
        new Thread(this).start();
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(25);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        game.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        for (Render r : game.getRenders()) {
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.getX(), r.getY(), null);
        }

        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Purisa", Font.BOLD, 18));
        g2D.drawString("High Score: " + game.getHighScore(), 350, 30);

        if (!game.gameStarted && (game.roundCounter == 0)) {
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Purisa", Font.BOLD, 28));
            g2D.drawString("Welcome to Flying UFO!", 80, 210);
            g2D.drawString("Press SPACE to fly", 110, 250);
        } else if (!game.gameStarted && (game.roundCounter != 0)) {
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Purisa", Font.BOLD, 28));
            g2D.drawString("Press SPACE to fly", 110, 250);
        } else {
            g2D.setColor(Color.BLACK);
            g2D.setFont(new Font("Purisa", Font.BOLD, 18));
            g2D.drawString("Current Score: " + game.getScore(), 7, 482);
        }

        if (game.getDisplayLevelTime() >= 1) {
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Purisa", Font.BOLD, 20));
            g2D.drawString("~ level " + game.getLevel() + " complete ~", 140, 215);
        }

        if (game.getGameOver()) {
            g2D.setColor(Color.WHITE);
            g2D.setFont(new Font("Purisa", Font.BOLD, 28));
            g2D.drawString("Press R to restart", 128, 250);
        }
    }
}
