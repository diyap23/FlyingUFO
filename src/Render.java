import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Render {
    private int x;
    private int y;
    public Image image;
    public AffineTransform transform;
    private static final HashMap<String, Image> cache = new HashMap<>();

    public Render() {
    }

    public Render(int _x, int _y, String imagePath) {
        Toolkit.getDefaultToolkit().sync();

        x = _x;
        y = _y;

        image = loadImage(imagePath);
    }

    public static Image loadImage(String path) {
        Image image = null;

        if (cache.containsKey(path)) {
            return cache.get(path);
        }

        try {
            image = ImageIO.read(new File(path));
            if (!cache.containsKey(path)) {
                cache.put(path, image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image != null) {
            int desiredWidth = 45; // or whatever size fits your game
            int desiredHeight = 32; // match UFO width/height

            Image scaledImage = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
            return scaledImage;
        }
        return image;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updateX(int newX) {
        x = newX;
    }

    public void updateY(int newY) {
        y = newY;
    }

}
