package sample.Objects;

import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Board {

    public HBox queue_to_LPG_box;
    public HBox queue_to_PB98_box;
    public HBox queue_to_ON_box;
    public VBox LPG_box;
    public VBox PB98_box;
    public VBox ON_box;
    public HBox queue_to_cash_box;
    public VBox cash_box;
    public HBox queue_to_car_wash_box;
    public HBox car_wash_box;

    public Board(HBox queue_to_LPG_box, HBox queue_to_PB98_box, HBox queue_to_ON_box, VBox LPG_box, VBox PB98_box, VBox ON_box, HBox queue_to_cash_box, VBox cash_box, HBox queue_to_car_wash_box, HBox car_wash_box) {
        this.queue_to_LPG_box = queue_to_LPG_box;
        this.queue_to_PB98_box = queue_to_PB98_box;
        this.queue_to_ON_box = queue_to_ON_box;
        this.LPG_box = LPG_box;
        this.PB98_box = PB98_box;
        this.ON_box = ON_box;
        this.queue_to_cash_box = queue_to_cash_box;
        this.cash_box = cash_box;
        this.queue_to_car_wash_box = queue_to_car_wash_box;
        this.car_wash_box = car_wash_box;
    }

    public void addCarToPlace(Car car, Place place)
    {
        switch (place)
        {
            case QUEUE_TO_LPG:
                this.queue_to_LPG_box.getChildren().add(car.stackPane);
                break;
            case QUEUE_TO_PB98:
                this.queue_to_PB98_box.getChildren().add(car.stackPane);
                break;
            case QUEUE_TO_ON:
                this.queue_to_ON_box.getChildren().add(car.stackPane);
                break;
            case LPG_DISTRIBUTOR:
                this.LPG_box.getChildren().add(car.stackPane);
                break;
            case ON_DISTRIBUTOR:
                this.ON_box.getChildren().add(car.stackPane);
                break;
            case PB98_DISTRIBUTOR:
                this.PB98_box.getChildren().add(car.stackPane);
                break;
            case CAR_WASH:
                this.car_wash_box.getChildren().add(car.stackPane);
                break;
            case QUEUE_TO_CAR_WASH:
                this.queue_to_car_wash_box.getChildren().add(car.stackPane);
                break;
        }
    }
    public void removeCarFromPlace(Car car, Place place)
    {
        switch (place)
        {

            case QUEUE_TO_LPG:
                this.queue_to_LPG_box.getChildren().remove(car.stackPane);
                break;
            case QUEUE_TO_PB98:
                this.queue_to_PB98_box.getChildren().remove(car.stackPane);
                break;
            case QUEUE_TO_ON:
                this.queue_to_ON_box.getChildren().remove(car.stackPane);
                break;
            case LPG_DISTRIBUTOR:
                this.LPG_box.getChildren().remove(car.stackPane);
                break;
            case ON_DISTRIBUTOR:
                this.ON_box.getChildren().remove(car.stackPane);
                break;
            case PB98_DISTRIBUTOR:
                this.PB98_box.getChildren().remove(car.stackPane);
                break;
            case CAR_WASH:
                this.car_wash_box.getChildren().remove(car.stackPane);
                break;
            case QUEUE_TO_CAR_WASH:
                this.queue_to_car_wash_box.getChildren().remove(car.stackPane);
                break;
        }
    }
    public void addClientToPlace(Client client,Place place)
    {
        switch (place)
        {
            case QUEUE_TO_CASH:
            {
                this.queue_to_cash_box.getChildren().add(client.stackPane);
                break;
            }
            case CASH:
            {
                this.cash_box.getChildren().add(client.stackPane);
                break;
            }
        }
    }
    public void removeClientFromPlace(Client client,Place place)
    {
        switch (place)
        {
            case QUEUE_TO_CASH:
            {
                this.queue_to_cash_box.getChildren().remove(client.stackPane);
                break;
            }
            case CASH:
            {
                this.cash_box.getChildren().remove(client.stackPane);
                break;
            }
        }
    }



}
