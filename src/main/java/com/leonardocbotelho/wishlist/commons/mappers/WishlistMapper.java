package com.leonardocbotelho.wishlist.commons.mappers;

import com.leonardocbotelho.wishlist.application.response.WishlistResponse;
import com.leonardocbotelho.wishlist.domain.model.Wishlist;
import com.leonardocbotelho.wishlist.infrastructure.database.entity.WishlistEntity;

import java.util.ArrayList;
import java.util.Objects;

public class WishlistMapper {

    private WishlistMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static WishlistResponse responseFromModel(final Wishlist source) {
        final var responseBuilder = WishlistResponse.builder();

        if (Objects.isNull(source))
            return responseBuilder.build();

        return responseBuilder
                .customerId(source.getCustomerId())
                .products(Objects.requireNonNullElse(source.getProducts(), new ArrayList<Wishlist.Product>())
                        .stream()
                        .filter(Objects::nonNull)
                        .map(WishlistMapper::productResponseFromProductModel)
                        .toList())
                .build();
    }

    private static WishlistResponse.ProductResponse productResponseFromProductModel(final Wishlist.Product source) {
        return new WishlistResponse.ProductResponse(source.productId());
    }

    public static Wishlist modelFromEntity(final WishlistEntity source) {
        final var modelBuilder = Wishlist.builder();

        if (Objects.isNull(source))
            return modelBuilder.build();

        return modelBuilder
                .customerId(source.getCustomerId())
                .createdDateTime(source.getCreatedDateTime())
                .products(Objects.requireNonNullElse(source.getProducts(), new ArrayList<WishlistEntity.ProductEntity>())
                        .stream()
                        .filter(Objects::nonNull)
                        .map(WishlistMapper::productModelFromProductEntity)
                        .toList())
                .build();
    }

    private static Wishlist.Product productModelFromProductEntity(final WishlistEntity.ProductEntity source) {
        return new Wishlist.Product(source.getProductId(), source.getCreatedDateTime());
    }

}
