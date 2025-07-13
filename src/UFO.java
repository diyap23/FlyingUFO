import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class UFO {

    private final int x;
    private int y;
    private final int width;
    private final int height;

    private boolean dead;
    private double verticalVelocity;
    private final double gravity;

    private int taskDelay;
    private double rotation;

    private Image image;
    private final String imagePath;
    private final Keyboard keyboard;

    public UFO() {
        imagePath = "lib/UFO.png";

        x = 100;
        y = 150;
        verticalVelocity = 0;
        width = 45;
        height = 32;
        gravity = -0.5;
        taskDelay = 0;
        rotation = 0.0;
        dead = false;

        keyboard = Keyboard.getInstance();
    }

    public void update() {
        verticalVelocity += gravity;

        if (taskDelay > 0)
            taskDelay--;

        if (!dead && keyboard.keyDown(KeyEvent.VK_SPACE) && taskDelay == 0) {
            verticalVelocity = 8;
            taskDelay = 10;
        }

        if (dead && keyboard.keyDown(KeyEvent.VK_SPACE)) {
        }

        if (dead) {
            verticalVelocity = 6;
        }

        y += verticalVelocity;

    }

    public Render getRender() {
        Render r = new Render();
        r.updateX(x);
        r.updateY(y);

        if (image == null) {
            image = Render.loadImage(imagePath);
        }

        r.image = image;

        rotation = (90 * (verticalVelocity + 20) / 20) - 90;
        rotation = rotation * Math.PI / 180;

        if (rotation > Math.PI / 2)
            rotation = Math.PI / 2;

        r.transform = new AffineTransform();
        r.transform.translate(x + (double) width / 2, y + (double) height / 2);
        r.transform.rotate(rotation);
        r.transform.translate((double) - width / 2,(double) - height / 2);

        return r;

    }

    
    // access methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void updateDead(boolean isDead) {
        dead = isDead;
    }
}

