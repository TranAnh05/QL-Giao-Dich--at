package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class TransactionSubject extends Observable {
    private List<TransactionObserver> observers = new ArrayList<>();

    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Object arg) {
        setChanged();
        for (TransactionObserver observer : observers) {
            observer.update(this, arg);
        }
    }
}