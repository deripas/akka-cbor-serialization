package ru.deripas.akka.serialization;

import akka.actor.ExtendedActorSystem;
import akka.serialization.JSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import lombok.SneakyThrows;

import java.util.Objects;

public class CborSerializer extends JSerializer {

    private final ObjectMapper mapper;
    private final int identifier;

    public CborSerializer(ExtendedActorSystem actorSystem) {
        //todo read from config
        this(CBORConstants.TAG_ID_SELF_DESCRIBE);
    }

    public CborSerializer() {
        this(CBORConstants.TAG_ID_SELF_DESCRIBE);
    }

    public CborSerializer(int identifier) {
        mapper = new ObjectMapper(new CBORFactory());
        this.identifier = identifier;
    }

    @Override
    public int identifier() {
        return identifier;
    }

    @Override
    public boolean includeManifest() {
        return true;
    }

    @SneakyThrows
    @Override
    public Object fromBinaryJava(byte[] bytes, Class<?> manifest) {
        return mapper.readValue(bytes, Objects.requireNonNull(manifest, "type not found!"));
    }

    @SneakyThrows
    @Override
    public byte[] toBinary(Object o) {
        return mapper.writeValueAsBytes(o);
    }
}
