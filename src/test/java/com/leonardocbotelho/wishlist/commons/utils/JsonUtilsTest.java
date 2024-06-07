package com.leonardocbotelho.wishlist.commons.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {

    @Test
    void testPrivateConstructorForUtilityClass() throws NoSuchMethodException {
        final Constructor<JsonUtils> constructor = JsonUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testToJsonWriteValueAsString() {
        final var target = "[\"two\",\"strings\"]";
        final var source = List.of("two", "strings");
        assertEquals(target, JsonUtils.toJson(source));
    }

    @Test
    void testToJsonValueToTree() {
        assertDoesNotThrow(() -> JsonUtils.toJson(new Object()));
    }

}
