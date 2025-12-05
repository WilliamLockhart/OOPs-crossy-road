package graphics;
import sprite.HitBox;
import sprite.Sprite;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ImageRenderer {

    private final ObservableList<Node> children;

    
    public ImageRenderer(ObservableList<Node> children) {
        this.children = children;
    }

    public void drawSprite(Sprite sprite) {
        double[] position = sprite.getEntityPosition();      // [centerX, centerY]
        double[] size = sprite.getDimensions();       // [width, height]

        double centerX = position[0]; double centerY = position[1];
        double width = size[0]; double height = size[1];

        // convert center → top-left
        double x = centerX - (width / 2);
        double y = centerY - (height / 2);

        drawImageAt(sprite.getSpriteImage(), x, y, width, height, sprite.getRotationDeg());
    }

    public void drawHitbox(HitBox hitBox){
        if (hitBox == null) { return;}

        Rectangle rect = hitBox.getRectangle();
        if (rect == null) { return;}

        rect.setFill(Color.color(1, 0, 0, 0.4));
        rect.setStroke(Color.RED);
        children.add(rect);
    }


    private void drawImageAt(Image img, double x, double y, double w, double h, double deg) {
        if (img == null) return;

        Canvas canvas = new Canvas(w, h);
        canvas.getGraphicsContext2D().setImageSmoothing(false);
        canvas.getGraphicsContext2D().drawImage(img, 0, 0, w, h);
        canvas.setRotate(deg);
        children.add(canvas);
        canvas.setLayoutX(x);
        canvas.setLayoutY(y);
        return;
    }

}
