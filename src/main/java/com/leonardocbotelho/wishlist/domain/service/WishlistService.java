package com.leonardocbotelho.wishlist.domain.service;

import com.leonardocbotelho.wishlist.commons.exceptions.ProductLimitSizeException;
import com.leonardocbotelho.wishlist.commons.mappers.WishlistMapper;
import com.leonardocbotelho.wishlist.domain.config.WishlistProperties;
import com.leonardocbotelho.wishlist.domain.model.Wishlist;
import com.leonardocbotelho.wishlist.domain.usecase.WishlistUseCase;
import com.leonardocbotelho.wishlist.infrastructure.database.entity.WishlistEntity;
import com.leonardocbotelho.wishlist.infrastructure.database.repository.WishlistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WishlistService implements WishlistUseCase {

    private final WishlistRepository repository;
    private final WishlistProperties properties;

    @Override
    public Wishlist findByCustomerId(final String customerId) {
        return WishlistMapper.modelFromEntity(repository.findByCustomerId(customerId).orElse(null));
    }

    @Override
    public Wishlist addProductToCustomerId(final String productId, final String customerId) {
        final var entity = repository.findByCustomerId(customerId).orElse(new WishlistEntity(customerId));
        if (entity.getProducts().size() >= properties.getLimit().getSize())
            throw new ProductLimitSizeException();

        if (entity.getProducts().stream().noneMatch(product -> product.getProductId().equals(productId))) {
            entity.getProducts().add(new WishlistEntity.ProductEntity(productId));
            repository.save(entity);
        }

        return this.findByCustomerId(customerId);
    }

    @Override
    public Wishlist removeProductFromCustomerId(final String productId, final String customerId) {
        repository.findByCustomerId(customerId)
                .ifPresent(wishlist -> {
                    wishlist.getProducts().removeIf(product -> product.getProductId().equals(productId));
                    if (wishlist.getProducts().isEmpty())
                        repository.delete(wishlist);
                    else
                        repository.save(wishlist);
                });

        return this.findByCustomerId(customerId);
    }

    @Override
    public boolean validateProductInCustomerId(final String productId, final String customerId) {
        return repository.findByCustomerIdAndProductId(customerId, productId).isPresent();
    }

}
