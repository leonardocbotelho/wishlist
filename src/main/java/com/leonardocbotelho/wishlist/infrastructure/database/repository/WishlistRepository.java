package com.leonardocbotelho.wishlist.infrastructure.database.repository;

import com.leonardocbotelho.wishlist.infrastructure.database.entity.WishlistEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface WishlistRepository extends MongoRepository<WishlistEntity, String> {

    Optional<WishlistEntity> findByCustomerId(final String customerId);

    @Query("{ 'customerId' : ?0, 'products.productId' : ?1 }")
    Optional<WishlistEntity> findByCustomerIdAndProductId(final String customerId, final String productId);

}
