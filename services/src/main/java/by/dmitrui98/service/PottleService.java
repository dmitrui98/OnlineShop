package by.dmitrui98.service;

import java.util.List;

/**
 * Created by Администратор on 09.07.2017.
 */
public interface PottleService<T> {
    double getAmount(List<T> entities);
    void putInPottle(List<T> entities, long id);
    void removeFromPottleAll(List<T> entities, long removedId);
    void removeFromPottle(List<T> entities, long removedId);
}
