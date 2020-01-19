package sample.Objects;

import java.util.LinkedList;

public class QueueToLPG extends LinkedList<Car> {
    public int limitOfQueue;

    public QueueToLPG(int limitOfQueue)
    {
        this.limitOfQueue = limitOfQueue;
    }

    public boolean addCar(Car car) {
        if(size() >= limitOfQueue)
        {
            return false;
        }
        else
        {
            super.add(car);
            return true;
        }
    }

    public Car popCar() {
        Car car = get(0);
        remove(get(0));
        return car;
    }
}
