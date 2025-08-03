package business;

import java.util.ArrayList;
import java.util.List;

public class TransactionSubject {
    private List<TransactionObserver> observers = new ArrayList<>();

    public void addObserver(TransactionObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void notifyObservers(Object data) {
        for (TransactionObserver observer : new ArrayList<>(observers)) { 
            observer.onTransactionUpdated(data);
        }
    }
}