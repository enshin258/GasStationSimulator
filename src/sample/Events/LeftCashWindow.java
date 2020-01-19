package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import dissimlab.simcore.SimParameters;
import javafx.application.Platform;
import sample.Objects.*;

public class LeftCashWindow extends BasicSimEvent<GasStation, Object> {

    Client client;
    public LeftCashWindow(GasStation entity, double delay,Object o) throws SimControlException {
        super(entity, delay,o);
        client = (Client)o;
    }

    @Override
    protected void stateChange() throws SimControlException {
        GasStation gasStation = getSimObj();
        Car car = client.car;
        switch (car.typeOfCar)
        {
            case LPG:
            {
                System.out.println(simTime() + " Client & car number: " + client.ID + " left station:");
                gasStation.numberOfFreeLPGDistributors++;
                gasStation.numberOfFreeCash++;

                Platform.runLater(()->gasStation.board.removeCarFromPlace(car,Place.LPG_DISTRIBUTOR));
                Platform.runLater(()->gasStation.board.removeClientFromPlace(client,Place.CASH));

                gasStation.averageLPGRefuelTime.setValue(simTime() - client.car.timeOfArrival);


                if(gasStation.queueToLPG.size()>0 && gasStation.numberOfFreeLPGDistributors>0)
                {
                    new TakeLPGDistributor(gasStation,0);
                }
                break;
            }
            case ON:
            {
                System.out.println(simTime() + " Client & car number: " + client.ID + " left station:");
                gasStation.numberOfFreeONDistributors++;
                gasStation.numberOfFreeCash++;

                Platform.runLater(()->gasStation.board.removeCarFromPlace(car,Place.ON_DISTRIBUTOR));
                Platform.runLater(()->gasStation.board.removeClientFromPlace(client,Place.CASH));

                gasStation.averageONRefuelTime.setValue(simTime() - client.car.timeOfArrival);


                if(gasStation.queueToON.size()>0 && gasStation.numberOfFreeONDistributors>0)
                {
                    new TakeONDistributor(gasStation,0);
                }
                break;
            }
            case PB98:
            {
                System.out.println(simTime() + " Client & car number: " + client.ID + " left station:");
                gasStation.numberOfFreePB98Distributors++;
                gasStation.numberOfFreeCash++;

                Platform.runLater(()->gasStation.board.removeCarFromPlace(car,Place.PB98_DISTRIBUTOR));
                Platform.runLater(()->gasStation.board.removeClientFromPlace(client,Place.CASH));

                gasStation.averagePB98RefuelTime.setValue(simTime() - client.car.timeOfArrival);

                if(gasStation.queueToPB98.size()>0 && gasStation.numberOfFreePB98Distributors>0)
                {
                    new TakePB98Distributor(gasStation,0);
                }
                break;
            }
            case DIRTY_ON:
            {
                System.out.println(simTime() + " Client & car number: " + client.ID + " go to car wash :");
                gasStation.numberOfFreeONDistributors++;
                gasStation.numberOfFreeCash++;

                Platform.runLater(()->gasStation.board.removeCarFromPlace(car,Place.ON_DISTRIBUTOR));
                Platform.runLater(()->gasStation.board.removeClientFromPlace(client,Place.CASH));

                gasStation.averageONRefuelTime.setValue(simTime() - client.car.timeOfArrival);


                if(gasStation.queueToON.size()>0 && gasStation.numberOfFreeONDistributors>0)
                {
                    new TakeONDistributor(gasStation,0);
                }
                new EnterQueueToCarWash(gasStation,0,client.car);

                break;
            }
            case DIRTY_LPG:
            {
                System.out.println(simTime() + " Client & car number: " + client.ID + " go to car wash :");
                gasStation.numberOfFreeLPGDistributors++;
                gasStation.numberOfFreeCash++;

                Platform.runLater(()->gasStation.board.removeCarFromPlace(car,Place.LPG_DISTRIBUTOR));
                Platform.runLater(()->gasStation.board.removeClientFromPlace(client,Place.CASH));
                gasStation.averageLPGRefuelTime.setValue(simTime() - client.car.timeOfArrival);

                if(gasStation.queueToLPG.size()>0 && gasStation.numberOfFreeLPGDistributors>0)
                {
                    new TakeLPGDistributor(gasStation,0);
                }
                new EnterQueueToCarWash(gasStation,0,client.car);

                break;
            }
            case DIRTY_PB98:
            {
                System.out.println(simTime() + " Client & car number: " + client.ID + " go to car wash :");
                gasStation.numberOfFreePB98Distributors++;
                gasStation.numberOfFreeCash++;

                Platform.runLater(()->gasStation.board.removeCarFromPlace(car,Place.PB98_DISTRIBUTOR));
                Platform.runLater(()->gasStation.board.removeClientFromPlace(client,Place.CASH));
                gasStation.averagePB98RefuelTime.setValue(simTime() - client.car.timeOfArrival);

                if(gasStation.queueToPB98.size()>0 && gasStation.numberOfFreePB98Distributors>0)
                {
                    new TakePB98Distributor(gasStation,0);
                }
                new EnterQueueToCarWash(gasStation,0,client.car);
                break;
            }
            case DIRTY:
            {
                System.out.println(simTime() + " Client & car number: " + client.ID + " go to car wash :");
                gasStation.numberOfFreeCash++;
                Platform.runLater(()->gasStation.board.removeClientFromPlace(client,Place.CASH));
                new EnterQueueToCarWash(gasStation,0,client.car);
                break;
            }
        }
        if(gasStation.queueToCash.size()>0 && gasStation.numberOfFreeCash>0)
        {
            new TakeCashWindow(gasStation,0);
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
