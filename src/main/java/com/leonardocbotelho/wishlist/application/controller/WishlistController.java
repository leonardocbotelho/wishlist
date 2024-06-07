package com.leonardocbotelho.wishlist.application.controller;

import com.leonardocbotelho.wishlist.application.response.WishlistResponse;
import com.leonardocbotelho.wishlist.commons.exceptions.ProductLimitSizeException;
import com.leonardocbotelho.wishlist.commons.mappers.WishlistMapper;
import com.leonardocbotelho.wishlist.domain.usecase.WishlistUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistUseCase useCase;

    @GetMapping("/{customerId}")
    public ResponseEntity<WishlistResponse> find(
            @PathVariable("customerId") final String customerId
    ) {
        final var response = WishlistMapper.responseFromModel(useCase.findByCustomerId(customerId));
        if (Objects.nonNull(response.getCustomerId()))
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<WishlistResponse> add(
            @PathVariable("customerId") final String customerId,
            @RequestParam("productId") final String productId
    ) {
        final var response = WishlistMapper.responseFromModel(useCase.addProductToCustomerId(productId, customerId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{customerId}/{productId}")
    public ResponseEntity<WishlistResponse> remove(
            @PathVariable("customerId") final String customerId,
            @PathVariable("productId") final String productId
    ) {
        final var response = WishlistMapper.responseFromModel(useCase.removeProductFromCustomerId(productId, customerId));
        if (Objects.nonNull(response) && Objects.nonNull(response.getCustomerId()))
            return ResponseEntity.ok(response);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{customerId}/{productId}")
    public ResponseEntity<Void> exists(
            @PathVariable("customerId") final String customerId,
            @PathVariable("productId") final String productId
    ) {
        if (useCase.validateProductInCustomerId(productId, customerId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ProductLimitSizeException.class)
    public ResponseEntity<String> handleProductLimitSizeException(
            final ProductLimitSizeException exception
    ) {
        return new ResponseEntity<>("Customer has reached products limit size.", HttpStatus.NOT_ACCEPTABLE);
    }

}
