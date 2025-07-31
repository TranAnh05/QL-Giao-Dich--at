package business;

import java.util.Observable;
import java.util.Observer;

public interface TransactionObserver extends Observer {
    void update(Observable o, Object arg);
}