package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import javafx.application.Platform;
import sample.Objects.Car;
import sample.Objects.Client;
import sample.Objects.GasStation;
import sample.Objects.Place;

public class EnterQueueToCash extends BasicSimEvent<GasStation, Object> {
    Car car;
    public EnterQueueToCash(GasStation entity, double delay, Object o) throws SimControlException {
        super(entity, delay, o);
        car = (Car)o;
    }

    @Override
    protected void stateChange() throws SimControlException {
        //create client
        GasStation gasStation = getSimObj();
        Client client = new Client(car);
        if(!gasStation.queueToCash.contains(client))
        {
            if(gasStation.queueToCash.addClient(client))
            {
                System.out.println(simTime() + " Client number: " + client.ID + " enter into cash queue, actual size of queue:" + gasStation.queueToCash.size());
                Platform.runLater(()-> client.car.stackPane.setOpacity(0.5f));
                Platform.runLater(() -> gasStation.board.addClientToPlace(client, Place.QUEUE_TO_CASH));
            }
            else
            {
                double dt = gasStation.simGenerator.exponential(gasStation.lambdaForCashService);
                new EnterQueueToCash(gasStation,dt,client.car);
            }
        }
        if(gasStation.numberOfFreeCash>0 && gasStation.queueToCash.size()>0)
        {
            new TakeCashWindow(gasStation,0);
        }

    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}
