import java.awt.event.KeyEvent;

public class Keyboard {
    private static Keyboard instance;
    private final boolean[] keys;

    private Keyboard() {
        keys = new boolean[256];
    }

    public void keyTyped(KeyEvent e) {
    }

    public static Keyboard getInstance() {
        if (instance == null) {
            instance = new Keyboard();
        }
        return instance;
    }

    public void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = true;
        }
    }

    public void KeyReleased(KeyEvent e) {
        if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = false;
        }
    }

    public boolean keyDown(int key) {
        if (key >= 0 && key < keys.length) {
            return keys[key];
        }

        return false;
    }
}
