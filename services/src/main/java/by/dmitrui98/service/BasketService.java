package by.dmitrui98.service;

import by.dmitrui98.dto.BasketDto;

import java.util.List;

/**
 * Created by Администратор on 09.07.2017.
 */
public interface BasketService {
    double getSum(List<BasketDto> entities);

    void putInBasket(List<BasketDto> entities, long id);

    void removeFromBasketAll(List<BasketDto> entities, long removedId);

    void removeFromBasketOne(List<BasketDto> entities, long removedId);
}
