package sample.Objects;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Client {
    public int ID;
    public Car car;

    public StackPane stackPane;
    public Client(Car car)
    {
        this.car = car;
        this.ID = car.ID;

        Rectangle r = new Rectangle(100,100);
        r.setFill(new ImagePattern(new Image("resources/client.png")));
        Text t = new Text(ID+"");
        t.setFill(Color.HOTPINK);
        t.setStyle("-fx-font: 64 arial;");
        this.stackPane = new StackPane();
        stackPane.getChildren().addAll(r,t);

    }
}
