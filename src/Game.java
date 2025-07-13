import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {
    private int restartDelay;
    private int pipeDelay;
    private int pipeDelayInc = 0;
    private UFO ufo;
    private ArrayList<Pipe> pipes;
    private final Keyboard keyboard;
    private boolean scoreChanged = false;
    private int level = 0;
    private boolean levelUp = false;
    private int displayLevelTime = 0;
    private int score;
    private int highScore = 0;
    private Boolean gameOver;
    public int roundCounter = 0;
    public Boolean gameStarted;

    public Game() {
        keyboard = Keyboard.getInstance();
        startOrRestart();
    }

    /**
     * Starts or restarts the game, resets everything
     */
    public void startOrRestart() {
        scoreChanged = false;
        gameStarted = false;
        gameOver = false;

        displayLevelTime = 0;
        score = 0;
        level = 0;
        pipeDelayInc = 0;
        restartDelay = 0;
        pipeDelay = 0;

        ufo = new UFO();
        pipes = new ArrayList<>();
    }

    public void update() {
        listenStart();

        if (!gameStarted) {
            return;
        }

        listenReset();

        ufo.update();

        if (gameOver) {
            ufo.update();
            return;
        }

        if (score % 10 == 0 && score != 0 && scoreChanged && pipeDelayInc <= 40) {
            scoreChanged = false;
            levelUp = true;
            displayLevelTime = 50;
            level += 1;
            pipeDelayInc += 10;
        }

        if (levelUp) {
            displayLevelTime--;
        }

        movePipes(pipeDelayInc);
        checkCollisions();
    }

    /**
     * Returns the renders to be painted
     */
    public ArrayList<Render> getRenders() {
    ArrayList<Render> renders = new ArrayList<>();
    renders.add(new Render(0, 0, "lib/background.png"));  // background image
    for (Pipe pipe : pipes) {
        renders.add(pipe.getRender());
    }
    renders.add(ufo.getRender());
    return renders;
}


    /**
     * Starts if SPACE is pressed
     */
    private void listenStart() {
        if (!gameStarted && keyboard.keyDown(KeyEvent.VK_SPACE)) {
            gameStarted = true;
            roundCounter++;
        }
    }

    /**
     * Starts again if R is pressed
     */
    private void listenReset() {

        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.keyDown(KeyEvent.VK_R) && restartDelay == 0) {
            startOrRestart();
            restartDelay = 10;
        }
    } 
    /**
     * Moves the pipes
     */ 
    private void movePipes(int inc) {
    pipeDelay--;

    if (pipeDelay < 0) {
        pipeDelay = 80 - inc;

        Pipe topPipe = new Pipe("top");
        Pipe bottomPipe = new Pipe("bottom");

        // Position the top pipe randomly (already set in Pipe.reset())
        // Position the bottom pipe based on top pipe's position plus a fixed gap
        int gap = 150;
        bottomPipe.updateY(topPipe.getY() + topPipe.getHeight() + gap);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    for (Pipe pipe : pipes) {
        pipe.update();
    }
}


    /**
     * Checks if the fish has collided with the surface, the
     * ground, or any pipes
     */
    private void checkCollisions() {
        // Pipe Collision
        for (Pipe pipe : pipes) {
            if (pipe.collides(ufo.getX(), ufo.getY(), ufo.getWidth(), ufo.getHeight())) {
                gameOver = true;
                ufo.updateDead(true);
            } else if (pipe.getX() == ufo.getX() && pipe.orientation.equalsIgnoreCase("bottom")) {
                score++;
                scoreChanged = true;
                if (score > highScore) {
                    highScore = score;
                }
            }
        }    

        // Ground and Surface Collision
        if ((ufo.getY() + ufo.getHeight() < 5) || (ufo.getY() + ufo.getHeight() > App.HEIGHT)) { // changed
            gameOver = true;
            ufo.updateDead(true);
        }
    }

    // access methods
    public int getLevel() {
        return level;
    }

    public int getDisplayLevelTime() {
        return displayLevelTime;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getScore() {
        return score;
    }

    public Boolean getGameOver() {
        return gameOver;
    }
}
