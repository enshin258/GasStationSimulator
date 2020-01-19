package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters;
import javafx.application.Platform;
import sample.Objects.*;

public class LeftCarWash extends BasicSimEvent<GasStation, Object> {
    Car car;
    public LeftCarWash(GasStation entity, double delay, Object o) throws SimControlException {
        super(entity, delay, o);
        car = (Car) o;
    }

    @Override
    protected void stateChange() throws SimControlException {
        GasStation gasStation = getSimObj();
        System.out.println(simTime() + "Car number: " +car.ID + " left car wash");
        gasStation.freeCarWash = true;
        Platform.runLater(()->gasStation.board.removeCarFromPlace(car, Place.CAR_WASH));
        gasStation.averageWashingCarTime.setValue(simTime() - car.timeOfArrival);

        if(gasStation.queueToCarWash.size()>0 && gasStation.freeCarWash)
        {
            new TakeCarWash(gasStation,0);
        }
        if(gasStation.queueToCarWash.isEmpty() && gasStation.queueToON.isEmpty() && gasStation.queueToLPG.isEmpty() && gasStation.queueToPB98.isEmpty() && gasStation.queueToCash.isEmpty() && gasStation.carsLimit<=gasStation.counterOfCars)
        {
            SimManager.simMode = SimParameters.SimMode.ASAP;
        }

    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}
