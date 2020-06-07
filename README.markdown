RWX XML-RPC Object-Binding API
=======================================

RWX is a set of APIs for dealing with XML-RPC interactions in Java. It offers
an annotation-based object-binding API for Java objects.

It uses Java Annotation Processor API to generate parser/renderer Java source files
according to RWX annotations. The top-level `RWXMapper` uses those generated classes under the hood.

You will need four steps to use RWX:

1. Create model classes according to your XML-RPC req/response format.
2. Annotate model classes with RWX annotations.
3. Generate sources from annotated classes (an example POM is provided in the `rwx-test` module).
4. Call `RWXMapper.render`/`parse` methods to convert between XML-RPC req/resp strings and Java objects.

The first two steps can be simplified by binding a field to `java.lang.Object` if you
do not know the exact Java type. In such cases, RWX will bind a `List`/`Map` based data structure to the field and you
can always interpret it in your program.
