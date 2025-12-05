package graphics;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextRenderer {

    private final ObservableList<Node> children;

    public TextRenderer(ObservableList<Node> children) {
        this.children = children;
    }

    public void drawText(String message, double x, double y, double fontSize, Color color) {
        Text text = new Text(message);
        text.setFont(new Font(fontSize));
        text.setFill(color);
        text.setX(x);
        text.setY(y);

        children.add(text);
    }
}
