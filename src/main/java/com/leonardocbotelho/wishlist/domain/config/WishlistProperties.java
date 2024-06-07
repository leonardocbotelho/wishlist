package com.leonardocbotelho.wishlist.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("wishlist")
public class WishlistProperties {

    private LimitProperties limit;

    @Getter
    @Setter
    public static class LimitProperties {
        private int size;
    }

}
