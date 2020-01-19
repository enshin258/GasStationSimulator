package sample.Objects;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Car {
    public int ID;
    public double timeOfArrival;
    public TypeOfCar typeOfCar;

    public StackPane stackPane;

    public Car(int id, double timeOfArrival,TypeOfCar typeOfCar)
    {
        this.ID = id;
        this.timeOfArrival = timeOfArrival;
        this.typeOfCar = typeOfCar;


        Rectangle r = new Rectangle(100,100);
        switch (typeOfCar)
        {
            case LPG:
                r.setFill(new ImagePattern(new Image("resources/LPG_car.png")));
                break;
            case ON:
                r.setFill(new ImagePattern(new Image("resources/ON_car.png")));
                break;
            case PB98:
                r.setFill(new ImagePattern(new Image("resources/PB98_car.png")));
                break;
            case DIRTY_LPG:
                r.setFill(new ImagePattern(new Image("resources/Dirty_LPG_car.png")));
                break;
            case DIRTY_ON:
                r.setFill(new ImagePattern(new Image("resources/Dirty_ON_car.png")));
                break;
            case DIRTY_PB98:
                r.setFill(new ImagePattern(new Image("resources/Dirty_PB98_car.png")));
                break;
            case DIRTY:
                r.setFill(new ImagePattern(new Image("resources/Dirty_car.png")));
                break;
        }

        Text t = new Text(id+"");
        t.setFill(Color.HOTPINK);
        t.setStyle("-fx-font: 48 arial;");
        this.stackPane = new StackPane();
        stackPane.getChildren().addAll(r,t);

    }
}
