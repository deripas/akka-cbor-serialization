package ru.deripas.akka.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Map;

@Warmup(iterations = 5)
@Measurement(iterations = 5)
@Fork(1)
@State(Scope.Benchmark)
public class JsonCompareBenchmark {

    private ObjectMapper json;
    private ObjectMapper cbor;
    private Map<String, Object> pojo;

    @Setup(Level.Trial)
    public void initialize() {
        json = new ObjectMapper();
        cbor = new ObjectMapper(new CBORFactory());
        pojo = new HashMap<>();
        for (int i = 0; i < 50; i++) {
            pojo.put("key_" + i, "value_" + i);
        }
    }

    @Benchmark
    public void json(Blackhole blackhole) throws Exception {
        byte[] bytes = json.writeValueAsBytes(pojo);
        blackhole.consume(json.readValue(bytes, Map.class));
    }

    @Benchmark
    public void cbor(Blackhole blackhole) throws Exception {
        byte[] bytes = cbor.writeValueAsBytes(pojo);
        blackhole.consume(cbor.readValue(bytes, Map.class));
    }
}
