package sample;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import sample.Objects.GasStation;

import java.awt.*;

public class MyThread extends Thread {

    GasStation gasStation;

    public MyThread(GasStation gasStation)
    {
        this.gasStation = gasStation;
    }


    public void run()
    {
        try {
            gasStation.simManager.startSimulation();

            System.out.println("***********************************");
            System.out.println("Number of clients: " + gasStation.counterOfCars);
            System.out.println("Client lost:" + gasStation.lossOfCars);

            System.out.println("Chance of lost client:" + (((float)gasStation.lossOfCars/gasStation.counterOfCars) * 100) + "%");

            System.out.println("Average clients in LPG Queue: " + Statistics.arithmeticMean(gasStation.averageCarsInLPGQueue));
            System.out.println("Average clients in PB98 Queue: " + Statistics.arithmeticMean(gasStation.averageCarsInPB98Queue));
            System.out.println("Average clients in ON Queue: " + Statistics.arithmeticMean(gasStation.averageCarsInONQueue));
            System.out.println("Average clients in Car wash Queue: " + Statistics.arithmeticMean(gasStation.averageCarsInCarWashQueue));

            System.out.println("Average time of LPG refuel: " + Statistics.arithmeticMean(gasStation.averageLPGRefuelTime));
            System.out.println("Average time of PB98 refuel: " + Statistics.arithmeticMean(gasStation.averagePB98RefuelTime));
            System.out.println("Average time of ON refuel: " + Statistics.arithmeticMean(gasStation.averageONRefuelTime));
            System.out.println("Average time of Car washing: " + Statistics.arithmeticMean(gasStation.averageWashingCarTime));
            System.out.println("***********************************");

            Diagram cars_ON = new Diagram(Diagram.DiagramType.TIME_FUNCTION,"Average number of cars in ON Queue");
            Diagram cars_LPG = new Diagram(Diagram.DiagramType.TIME_FUNCTION,"Average number of cars in LPG Queue");
            Diagram cars_PB98 = new Diagram(Diagram.DiagramType.TIME_FUNCTION,"Average number of cars in PB98 Queue");
            Diagram cars_car_wash = new Diagram(Diagram.DiagramType.TIME_FUNCTION,"Average number of cars in Car wash Queue");

            Diagram cars_ON_2 = new Diagram(Diagram.DiagramType.DISTRIBUTION,"Average number of cars in ON Queue");
            Diagram cars_LPG_2 = new Diagram(Diagram.DiagramType.DISTRIBUTION,"Average number of cars in LPG Queue");
            Diagram cars_PB98_2 = new Diagram(Diagram.DiagramType.DISTRIBUTION,"Average number of cars in PB98 Queue");
            Diagram cars_car_wash_2 = new Diagram(Diagram.DiagramType.DISTRIBUTION,"Average number of cars in Car wash Queue");

            cars_ON.add(gasStation.averageCarsInONQueue, Color.RED);
            cars_LPG.add(gasStation.averageCarsInLPGQueue, Color.GREEN);
            cars_PB98.add(gasStation.averageCarsInPB98Queue, Color.YELLOW);
            cars_car_wash.add(gasStation.averageCarsInCarWashQueue, Color.BLUE);

            cars_ON_2.add(gasStation.averageCarsInONQueue, Color.RED);
            cars_LPG_2.add(gasStation.averageCarsInLPGQueue, Color.GREEN);
            cars_PB98_2.add(gasStation.averageCarsInPB98Queue, Color.YELLOW);
            cars_car_wash_2.add(gasStation.averageCarsInCarWashQueue, Color.BLUE);

            cars_ON.show();
            cars_LPG.show();
            cars_PB98.show();
            cars_car_wash.show();

            cars_ON_2.show();
            cars_LPG_2.show();
            cars_PB98_2.show();
            cars_car_wash_2.show();


        } catch (SimControlException e) {
            e.printStackTrace();
        }

    }
}
