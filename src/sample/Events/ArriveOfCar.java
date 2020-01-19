package sample.Events;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.Objects.*;

import java.nio.ByteOrder;
import java.util.Random;

public class ArriveOfCar extends BasicSimEvent<GasStation, Object> {
    public ArriveOfCar(GasStation entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        GasStation gasStation = getSimObj();

        //create car
        double dt = gasStation.simGenerator.exponential(gasStation.lambdaForTypeOfCars);
        int a = ((int)dt)%4;
        Car car = new Car(gasStation.counterOfCars,simTime(),generateTypeOfCar(a));
        System.out.println(simTime() + " Car number: " + car.ID + " arrived to station, type of car: " + car.typeOfCar);
        gasStation.counterOfCars++;


        //add car to queue based on type
        switch (car.typeOfCar)
        {

            case LPG:
            case DIRTY_LPG:
            {
                if(gasStation.queueToLPG.addCar(car))
                {
                    System.out.println(simTime() + " Car number: " + car.ID + " enter into LPG queue, actual size of queue:" + gasStation.queueToLPG.size());
                    Platform.runLater(() -> gasStation.board.addCarToPlace(car, Place.QUEUE_TO_LPG));
                    gasStation.averageCarsInLPGQueue.setValue(gasStation.queueToLPG.size());

                }
                else
                {
                    gasStation.lossOfCars++;
                    System.out.println(simTime() + " Car number: " + car.ID + " can't enter queue to distributor!, actual loss: " + gasStation.lossOfCars);
                }
                break;
            }
            case ON:
            case DIRTY_ON:
            {
                if(gasStation.queueToON.addCar(car))
                {
                    System.out.println(simTime() + " Car number: " + car.ID + " enter into ON queue, actual size of queue:" + gasStation.queueToON.size());
                    Platform.runLater(() -> gasStation.board.addCarToPlace(car, Place.QUEUE_TO_ON));
                    gasStation.averageCarsInONQueue.setValue(gasStation.queueToON.size());

                }
                else
                {
                    gasStation.lossOfCars++;
                    System.out.println(simTime() + " Car number: " + car.ID + " can't enter queue to distributor!, actual loss: " + gasStation.lossOfCars);
                }
                break;
            }
            case PB98:
            case DIRTY_PB98:
            {
                if(gasStation.queueToPB98.addCar(car))
                {
                    System.out.println(simTime() + " Car number: " + car.ID + " enter into PB98 queue, actual size of queue:" + gasStation.queueToPB98.size());
                    Platform.runLater(() -> gasStation.board.addCarToPlace(car, Place.QUEUE_TO_PB98));
                    gasStation.averageCarsInPB98Queue.setValue(gasStation.queueToPB98.size());

                }
                else
                {
                    gasStation.lossOfCars++;
                    System.out.println(simTime() + " Car number: " + car.ID + " can't enter queue to distributor!, actual loss: " + gasStation.lossOfCars);
                }
                break;
            }
            case DIRTY:
            {
                Client client = new Client(car);
                new EnterQueueToCash(gasStation,0,client.car);
                break;
            }
        }

        if(gasStation.queueToLPG.size()>0 && gasStation.numberOfFreeLPGDistributors>0)
        {
            new TakeLPGDistributor(gasStation,0);
        }
        if(gasStation.queueToON.size()>0 && gasStation.numberOfFreeONDistributors>0)
        {
            new TakeONDistributor(gasStation,0);
        }
        if(gasStation.queueToPB98.size()>0 && gasStation.numberOfFreePB98Distributors>0)
        {
            new TakePB98Distributor(gasStation,0);
        }


        //add new car
        if(gasStation.counterOfCars<gasStation.carsLimit)
        {
            dt = gasStation.simGenerator.exponential(gasStation.lambdaForCars);
            new ArriveOfCar(gasStation,dt);
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    protected void onInterruption() throws SimControlException {

    }

    public TypeOfCar generateTypeOfCar(int a)
    {
        Random random = new Random();
        int value = random.nextInt(100);
        switch (a)
        {
            case 0:
            {
                if(value<=getSimObj().percentageChanceOfADirtyCar)
                {
                    return TypeOfCar.DIRTY_LPG;
                }
                else
                {
                    return TypeOfCar.LPG;
                }
            }
            case 1:
            {
                if(value<=getSimObj().percentageChanceOfADirtyCar)
                {
                    return TypeOfCar.DIRTY_ON;
                }
                else
                {
                    return TypeOfCar.ON;
                }
            }
            case 2:
            {
                if(value<=getSimObj().percentageChanceOfADirtyCar)
                {
                    return TypeOfCar.DIRTY_PB98;
                }
                else
                {
                    return TypeOfCar.PB98;
                }
            }
            case 3:
            {
                return TypeOfCar.DIRTY;
            }
        }
        return TypeOfCar.PB98;
    }
}
