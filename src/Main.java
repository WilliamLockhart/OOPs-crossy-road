import window.WindowManager;

import graphics.ImageRenderer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import player.Player;
import sprite.SpriteFactory;
import player.Input;
import gameManager.*;
import java.util.List;
import sprite.Sprite;

public class Main extends Application {
    private static final boolean DISPLAY_HITBOXES = true;

    private WindowManager windowManager;
    private ImageRenderer imageRenderer;
    private GameManager gameManager;
    private Input input;

    @Override
    public void start(Stage stage) {
        //creates the stuff for the window and rendering
        windowManager = new WindowManager();
        windowManager.start(stage);
        imageRenderer = new ImageRenderer(windowManager.getWindowSceneObject().getRootPane().getChildren());

        //input handling and game logic
        input = new Input(windowManager.getWindowSceneObject().getScene());
        gameManager = new GameManager(new Player(SpriteFactory.generateSprite("Player", "duck.png", 100, 100, 50, 50)));
        gameLoop();
    }

    private void remderGame(){
        var root = windowManager.getWindowSceneObject().getRootPane();
        root.getChildren().clear();

        List<Sprite> gameSprites = gameManager.getAllSprites();
        for(Sprite sprite : gameSprites){
            imageRenderer.drawSprite(sprite);
            if(DISPLAY_HITBOXES)
                imageRenderer.drawHitbox(sprite.getHitBox());
        }
    }

    private void gameLoop() {
        new AnimationTimer() {
            long last = 0;
            final long targetDelta = 1_000_000_000L / 60; // 60 FPS

            @Override
            public void handle(long now) {
                if (last == 0) {
                    last = now;
                    return;}

                if (now - last >= targetDelta) {
                    double delta = (now - last) / 1_000_000_000.0;
                    last = now;

                    //takes user inputs and deals with game decisions based on them
                    gameManager.update(delta, input);
                    remderGame();
                }
            }
        }.start();
    }
}