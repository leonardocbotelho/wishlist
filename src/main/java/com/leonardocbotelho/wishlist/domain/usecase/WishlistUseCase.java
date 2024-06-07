package com.leonardocbotelho.wishlist.domain.usecase;

import com.leonardocbotelho.wishlist.domain.model.Wishlist;

public interface WishlistUseCase {

    Wishlist findByCustomerId(final String customerId);

    Wishlist addProductToCustomerId(final String productId, final String customerId);

    Wishlist removeProductFromCustomerId(final String productId, final String customerId);

    boolean validateProductInCustomerId(final String productId, final String customerId);

}
