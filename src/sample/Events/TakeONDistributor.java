package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import javafx.application.Platform;
import sample.Objects.Car;
import sample.Objects.Client;
import sample.Objects.GasStation;
import sample.Objects.Place;

public class TakeONDistributor extends BasicSimEvent<GasStation,Object> {

    public TakeONDistributor(GasStation entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        GasStation gasStation = getSimObj();
        Car car = gasStation.queueToON.popCar();
        gasStation.numberOfFreeONDistributors--;
        System.out.println(simTime() + " Car number: " + car.ID + " refuel ON, the number of seats available at the ON dispenser:  " + gasStation.numberOfFreeONDistributors);


        Platform.runLater(()->gasStation.board.removeCarFromPlace(car, Place.QUEUE_TO_ON));
        Platform.runLater(()->gasStation.board.addCarToPlace(car,Place.ON_DISTRIBUTOR));


        double dt = gasStation.simGenerator.exponential(gasStation.lambdaForONRefuel);
        new EnterQueueToCash(gasStation,dt,car);



    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}
