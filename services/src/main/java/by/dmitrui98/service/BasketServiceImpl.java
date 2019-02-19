package by.dmitrui98.service;

import by.dmitrui98.dto.BasketDto;
import by.dmitrui98.dto.ProductDto;
import by.dmitrui98.entity.Product;
import by.dmitrui98.service.dao.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Администратор on 29.04.2017.
 */
@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private ProductService productService;
    @Autowired
    private ModelMapper mapper;


    public double getSum(List<BasketDto> basket) {
        double sum = 0;
        if (basket != null) {
            for (BasketDto basketDto : basket) {
                Product product = productService.getById(basketDto.getProductDto().getProductId());
                sum += product.getPrice() * basketDto.getCountProducts();
            }
        }
        return sum;
    }

    public void putInBasket(List<BasketDto> basketProducts, long productId) {
        Optional<BasketDto> basketOptional = basketProducts.stream()
                .filter(dto -> dto.getProductDto().getProductId() == productId)
                .findFirst();
        if (basketOptional.isPresent()) {
            BasketDto basketDto = basketOptional.get();
            basketDto.setCountProducts(basketDto.getCountProducts() + 1);
        }
        else {
            Product puttingProduct = productService.getById(productId);
            ProductDto productDto = mapper.map(puttingProduct, ProductDto.class);
            BasketDto basketDto = new BasketDto(productDto, 1);
            basketProducts.add(basketDto);
        }
    }

    public void removeFromBasketAll(List<BasketDto> basketProducts, long removedProductId) {
        basketProducts.removeIf(basketDto -> basketDto.getProductDto().getProductId() != removedProductId);
    }

    public void removeFromBasketOne(List<BasketDto> basketProducts, long removedProductId) {
        basketProducts.stream()
                .filter(basketDto -> basketDto.getProductDto().getProductId() == removedProductId)
                .forEach(basketDto -> {
                    basketDto.setCountProducts(basketDto.getCountProducts() - 1);

                });
        basketProducts.removeIf(basketDto -> basketDto.getCountProducts() == 0);
    }
}
