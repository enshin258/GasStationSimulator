package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import javafx.application.Platform;
import sample.Objects.Client;
import sample.Objects.GasStation;
import sample.Objects.Place;

public class TakeCashWindow extends BasicSimEvent<GasStation, Object> {
    public TakeCashWindow(GasStation entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {

        GasStation gasStation = getSimObj();
        gasStation.numberOfFreeCash--;

        Client client = gasStation.queueToCash.popClient();


        System.out.println(simTime() + " Client number: " + client.ID + " is paying, available cash window: " + gasStation.numberOfFreeCash);
        Platform.runLater(()->gasStation.board.removeClientFromPlace(client, Place.QUEUE_TO_CASH));
        Platform.runLater(()->gasStation.board.addClientToPlace(client, Place.CASH));


        double dt = gasStation.simGenerator.exponential(gasStation.lambdaForCashService);
        new LeftCashWindow(gasStation,dt,client);

    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }
}
