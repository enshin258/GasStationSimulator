package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import javafx.application.Platform;
import sample.Objects.Car;
import sample.Objects.Client;
import sample.Objects.GasStation;
import sample.Objects.Place;

public class TakePB98Distributor extends BasicSimEvent<GasStation , Object> {
    public TakePB98Distributor(GasStation entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        GasStation gasStation = getSimObj();
        Car car = gasStation.queueToPB98.popCar();
        gasStation.numberOfFreePB98Distributors--;
        System.out.println(simTime() + " Car number: " + car.ID + " refuel PB98, the number of seats available at the PB98 dispenser:  " + gasStation.numberOfFreePB98Distributors);


        Platform.runLater(()->gasStation.board.removeCarFromPlace(car, Place.QUEUE_TO_PB98));
        Platform.runLater(()->gasStation.board.addCarToPlace(car,Place.PB98_DISTRIBUTOR));


        double dt = gasStation.simGenerator.exponential(gasStation.lambdaForPB98Refuel);
        new EnterQueueToCash(gasStation,dt,car);


    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}
