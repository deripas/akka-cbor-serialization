akka-cbor-serialization
=========================
Akka CBOR serializer.

Configuration of akka-cbor-serialization
-----------------------------------------
````buildoutcfg
akka.actor.serialization-bindings {
    "example.package.Pojo"    = cbor
}
````