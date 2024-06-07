package com.leonardocbotelho.wishlist.application.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class WishlistResponse {

    private final String customerId;
    private final List<ProductResponse> products;

    public record ProductResponse(String productId) {
    }

}