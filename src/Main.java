import window.WindowManager;

import graphics.ImageRenderer;
import graphics.TextRenderer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import sprite.SpriteFactory;
import gameManager.*;
import java.util.List;

import entity.player.Input;
import entity.player.Player;
import sprite.Sprite;

public class Main extends Application {
    private static final boolean DISPLAY_HITBOXES = false;

    private WindowManager windowManager;
    private ImageRenderer imageRenderer;
    private TextRenderer textRenderer;
    private GameManager gameManager;
    private Input input;

    @Override
    public void start(Stage stage) {
        //creates the stuff for the window and rendering
        windowManager = new WindowManager();
        windowManager.start(stage);

        ObservableList<Node> children = windowManager.getWindowSceneObject().getRootPane().getChildren();
        imageRenderer = new ImageRenderer(children);
        textRenderer = new TextRenderer(children);

        //input handling and game logic
        input = new Input(windowManager.getWindowSceneObject().getScene());
        gameManager = new GameManager(new Player(SpriteFactory.generateSprite("duck.png", 100, 100, 50, 50)));
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

                    if (gameManager.gameOver()) {
                        textRenderer.drawText("GAME OVER", 400, 400, 48, Color.RED);
                        this.stop();
                    }


                }
            }
        }.start();
    }
}