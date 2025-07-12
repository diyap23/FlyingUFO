import java.awt.Image;

public class Pipe {
    private int x;
    private int y;
    private int width;
    private int height;

    public int speed = 4;
    public String orientation;

    private Image image;

    public Pipe(String orientation) {
        this.orientation = orientation;
        reset();
    }

    public void reset() {
        width = 45;
        height = 400;
        x = GamePlayer.WIDTH;
        if (orientation.equals("down")) {
            y = -(int) (Math.random() * 175) - height / 2;
        }
    }

    public void update() {
        x -= speed;
    }

    public boolean collide(int _x, int _y, int _width, int _height){
        int margin = 2;

        if(_x + _width - margin > x && _x + margin < x + width) {

            if(orientation.equals("down") && _y < y + height) {
                return true;
            } else return orientation.equals("up") && _y + height > y;
        }

        return false;
    }

    public Render getRender(){
        Render r = new Render();

        r.updateX(x);
        r.updateY(y);

        if(image == null){
            image = Render.loadImage("FlyingUFO/lib/" + orientation + "Pipe.png");
        }
        r.image = image;

        return r;
    }

    public int getX(){
        return x;
    }

    public int getY(){
       return y;
    }

    public void updateY(int newY){
        y = newY;
    }

    public int getHeight(){
        return height;
    }

}
