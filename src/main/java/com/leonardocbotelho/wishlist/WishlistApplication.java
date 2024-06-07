package com.leonardocbotelho.wishlist;

import com.leonardocbotelho.wishlist.domain.config.WishlistProperties;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({WishlistProperties.class})
public class WishlistApplication {

    @Generated
    public static void main(String... args) {
        SpringApplication.run(WishlistApplication.class, args);
    }

}