package ru.deripas.akka.serialization;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CborSerializerTest {

    private CborSerializer serializer = new CborSerializer();

    @Test
    public void testString() {
        String str = "test string!";
        Assert.assertEquals(copy(str), str);
    }

    @Test
    public void testByteArray() {
        byte[] bytes = new byte[]{1, 2, 3, 4, 5};
        Assert.assertEquals(copy(bytes), bytes);
    }

    @Test
    public void testMap() {
        Map<String, String> pojo = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            pojo.put("key_" + i, "value_" + i);
        }
        Assert.assertEquals(copy(pojo), pojo);
    }


    private <T> T copy(T o) {
        @SuppressWarnings("unchecked")
        Class<T> type = (Class<T>) o.getClass();
        byte[] bytes = serializer.toBinary(o);
        Object obj = serializer.fromBinaryJava(bytes, o.getClass());
        return type.cast(obj);
    }
}
