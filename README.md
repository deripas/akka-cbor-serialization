akka-cbor-serialization
=========================
[CBOR](http://cbor.io/) serializer for Akka using [Jackson implementation](https://github.com/FasterXML/jackson-dataformats-binary/tree/master/cbor).

Configuration of akka-cbor-serialization
-----------------------------------------
````buildoutcfg
akka.actor.serialization-bindings {
    "example.package.Pojo"    = cbor
}
````