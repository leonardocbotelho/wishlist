package com.leonardocbotelho.wishlist.infrastructure.database.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "wishlist")
public class WishlistEntity {

    @Id
    private String id;

    private String customerId;
    private ZonedDateTime createdDateTime;
    private List<ProductEntity> products;

    public WishlistEntity(final String customerId) {
        this.customerId = customerId;
        this.createdDateTime = ZonedDateTime.now();
        this.products = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class ProductEntity {

        private String productId;
        private ZonedDateTime createdDateTime;

        public ProductEntity(final String productId) {
            this.productId = productId;
            this.createdDateTime = ZonedDateTime.now();
        }

    }

}
