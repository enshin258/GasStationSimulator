package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import javafx.application.Platform;
import sample.Objects.*;

public class TakeLPGDistributor extends BasicSimEvent<GasStation, Object> {



    public TakeLPGDistributor(GasStation entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        GasStation gasStation = getSimObj();
        Car car = gasStation.queueToLPG.popCar();
        gasStation.numberOfFreeLPGDistributors--;
        System.out.println(simTime() + " Car number: " + car.ID + " refuel LPG, the number of seats available at the LPG dispenser:  " + gasStation.numberOfFreeLPGDistributors);


        Platform.runLater(()->gasStation.board.removeCarFromPlace(car, Place.QUEUE_TO_LPG));
        Platform.runLater(()->gasStation.board.addCarToPlace(car,Place.LPG_DISTRIBUTOR));


        double dt = gasStation.simGenerator.exponential(gasStation.lambdaForLPGRefuel);
        new EnterQueueToCash(gasStation,dt,car);


    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}
