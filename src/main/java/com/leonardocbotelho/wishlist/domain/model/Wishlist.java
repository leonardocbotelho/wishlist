package com.leonardocbotelho.wishlist.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
public class Wishlist {

    private final String customerId;
    private final ZonedDateTime createdDateTime;
    private final List<Product> products;

    public record Product(String productId, ZonedDateTime createdDateTime) {
    }

}
