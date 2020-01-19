package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import javafx.application.Platform;
import sample.Objects.Car;
import sample.Objects.GasStation;
import sample.Objects.Place;

public class EnterQueueToCarWash extends BasicSimEvent<GasStation, Object> {
    Car car;
    public EnterQueueToCarWash(GasStation entity, double delay, Object o) throws SimControlException {
        super(entity, delay, o);
        car= (Car)o;
    }

    @Override
    protected void stateChange() throws SimControlException {
        GasStation gasStation = getSimObj();

        if(!gasStation.queueToCarWash.contains(car))
        {
            if(gasStation.queueToCarWash.addCar(car))
            {
                System.out.println(simTime() + " Car number: " + car.ID + " enter into car wash queue, actual size of queue:" + gasStation.queueToCarWash.size());
                Platform.runLater(()-> car.stackPane.setOpacity(1f));
                Platform.runLater(() -> gasStation.board.addCarToPlace(car, Place.QUEUE_TO_CAR_WASH));

                gasStation.averageCarsInCarWashQueue.setValue(gasStation.queueToCarWash.size());
            }
            else
            {
                gasStation.lossOfCars++;
                System.out.println(simTime() + " Car number: " + car.ID + " can't enter queue to car wash!, actual loss: " + gasStation.lossOfCars);
            }
        }
        if(gasStation.queueToCarWash.size()>0 && gasStation.freeCarWash)
        {
            new TakeCarWash(gasStation,0);
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}
