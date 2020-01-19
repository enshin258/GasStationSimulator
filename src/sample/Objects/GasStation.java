package sample.Objects;

import dissimlab.monitors.MonitoredVar;
import dissimlab.random.SimGenerator;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;
import sample.Events.*;

import java.util.LinkedList;

public class GasStation extends BasicSimObj {


    public Board board;

    public int carsLimit;

    public double lambdaForCars;
    public double percentageChanceOfADirtyCar;
    public double lambdaForTypeOfCars;
    public double lambdaForLPGRefuel;
    public double lambdaForONRefuel;
    public double lambdaForPB98Refuel;
    public double lambdaForCashService;
    public double lambdaForCarWash;


    public int counterOfCars;

    public SimGenerator simGenerator;
    public SimManager simManager;

    public QueueToCash queueToCash;
    public QueueToLPG queueToLPG;
    public QueueToPB98 queueToPB98;
    public QueueToON queueToON;
    public QueueToCarWash queueToCarWash;

    public int numberOfFreeLPGDistributors;
    public int numberOfFreeONDistributors;
    public int numberOfFreePB98Distributors;
    public int numberOfFreeCash;
    public boolean freeCarWash;

    public int lossOfCars=0;

    public MonitoredVar averageCarsInLPGQueue;
    public MonitoredVar averageCarsInONQueue;
    public MonitoredVar averageCarsInPB98Queue;
    public MonitoredVar averageCarsInCarWashQueue;

    public MonitoredVar averageLPGRefuelTime;
    public MonitoredVar averageONRefuelTime;
    public MonitoredVar averagePB98RefuelTime;

    public MonitoredVar averageWashingCarTime;

    public MonitoredVar probabilityOfLeavingStation;


    public GasStation(Board board, int carsLimit, double lambdaForCars, double percentageChanceOfADirtyCar, double lambdaForTypeOfCars, double lambdaForLPGRefuel, double lambdaForONRefuel, double lambdaForPB98Refuel, double lambdaForCashService, double lambdaForCarWash, SimGenerator simGenerator, SimManager simManager, QueueToCash queueToCash, QueueToLPG queueToLPG, QueueToPB98 queueToPB98, QueueToON queueToON, QueueToCarWash queueToCarWash, int numberOfFreeLPGDistributors, int numberOfFreeONDistributors, int numberOfFreePB98Distributors, int numberOfFreeCash, boolean freeCarWash) {
        this.board = board;
        this.carsLimit = carsLimit;
        this.lambdaForCars = lambdaForCars;
        this.percentageChanceOfADirtyCar = percentageChanceOfADirtyCar;
        this.lambdaForTypeOfCars = lambdaForTypeOfCars;
        this.lambdaForLPGRefuel = lambdaForLPGRefuel;
        this.lambdaForONRefuel = lambdaForONRefuel;
        this.lambdaForPB98Refuel = lambdaForPB98Refuel;
        this.lambdaForCashService = lambdaForCashService;
        this.lambdaForCarWash = lambdaForCarWash;
        this.simGenerator = simGenerator;
        this.simManager = simManager;
        this.queueToCash = queueToCash;
        this.queueToLPG = queueToLPG;
        this.queueToPB98 = queueToPB98;
        this.queueToON = queueToON;
        this.queueToCarWash = queueToCarWash;
        this.numberOfFreeLPGDistributors = numberOfFreeLPGDistributors;
        this.numberOfFreeONDistributors = numberOfFreeONDistributors;
        this.numberOfFreePB98Distributors = numberOfFreePB98Distributors;
        this.numberOfFreeCash = numberOfFreeCash;
        this.freeCarWash = freeCarWash;

        this.averageCarsInLPGQueue = new MonitoredVar(0,simManager);
        this.averageCarsInPB98Queue = new MonitoredVar(0,simManager);
        this.averageCarsInONQueue = new MonitoredVar(0,simManager);
        this.averageCarsInCarWashQueue = new MonitoredVar(0,simManager);
        this.averageLPGRefuelTime = new MonitoredVar(0,simManager);
        this.averageONRefuelTime = new MonitoredVar(0,simManager);
        this.averagePB98RefuelTime = new MonitoredVar(0,simManager);
        this.averageWashingCarTime = new MonitoredVar(0,simManager);
        this.probabilityOfLeavingStation = new MonitoredVar(0,simManager);

    }

    @Override
    public String toString() {
        return "GasStation{" +
                "board=" + board +
                ", carsLimit=" + carsLimit +
                ", lambdaForCars=" + lambdaForCars +
                ", percentageChanceOfADirtyCar=" + percentageChanceOfADirtyCar +
                ", lambdaForTypeOfCars=" + lambdaForTypeOfCars +
                ", lambdaForLPGRefuel=" + lambdaForLPGRefuel +
                ", lambdaForONRefuel=" + lambdaForONRefuel +
                ", lambdaForPB98Refuel=" + lambdaForPB98Refuel +
                ", lambdaForCashService=" + lambdaForCashService +
                ", lambdaForCarWash=" + lambdaForCarWash +
                ", counterOfCars=" + counterOfCars +
                ", simGenerator=" + simGenerator +
                ", simManager=" + simManager +
                ", queueToCash=" + queueToCash +
                ", queueToLPG=" + queueToLPG +
                ", queueToPB98=" + queueToPB98 +
                ", queueToON=" + queueToON +
                ", queueToCarWash=" + queueToCarWash +
                ", numberOfFreeLPGDistributors=" + numberOfFreeLPGDistributors +
                ", numberOfFreeONDistributors=" + numberOfFreeONDistributors +
                ", numberOfFreePB98Distributors=" + numberOfFreePB98Distributors +
                ", numberOfFreeCash=" + numberOfFreeCash +
                ", freeCarWash=" + freeCarWash +
                ", lossOfCars=" + lossOfCars +
                '}';
    }
}
