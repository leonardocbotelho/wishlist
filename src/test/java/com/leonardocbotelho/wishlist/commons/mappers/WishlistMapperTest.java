package com.leonardocbotelho.wishlist.commons.mappers;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class WishlistMapperTest {

    @Test
    void testPrivateConstructorForUtilityClass() throws NoSuchMethodException {
        final Constructor<WishlistMapper> constructor = WishlistMapper.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void givenNullModel_whenMapToResponse_thenResponseShouldNotBeNull() {
        assertNotNull(WishlistMapper.responseFromModel(null));
    }

    @Test
    void givenNullEntity_whenMapToModel_thenModelShouldNotBeNull() {
        assertNotNull(WishlistMapper.modelFromEntity(null));
    }

}
