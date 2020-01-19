package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import javafx.application.Platform;
import sample.Objects.Car;
import sample.Objects.Client;
import sample.Objects.GasStation;
import sample.Objects.Place;

public class TakeCarWash extends BasicSimEvent<GasStation, Object> {
    public TakeCarWash(GasStation entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        GasStation gasStation = getSimObj();
        gasStation.freeCarWash = false;
        Car car = gasStation.queueToCarWash.popCar();

        Platform.runLater(()->gasStation.board.addCarToPlace(car,Place.CAR_WASH));
        Platform.runLater(()->gasStation.board.removeCarFromPlace(car,Place.QUEUE_TO_CAR_WASH));
        System.out.println(simTime() + " Car number: " +car.ID + " occupy car wash");

        double dt = gasStation.simGenerator.exponential(gasStation.lambdaForCarWash);
        new LeftCarWash(gasStation,dt,car);

    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}
