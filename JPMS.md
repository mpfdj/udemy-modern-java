# JPMS (Java Platform Module System)

Question: 
when I do a maven package I still see the packages which are not exported, how is this possible?

I expect them not to be packaged in the jar file...


Short answer:  
**The module system does *not* control what goes into the JAR. It only controls what is *accessible at runtime via the module boundaries*.  
Maven will always package *all* compiled classes, exported or not.**

This is expected behavior.

***

# ✔ Why your JAR still contains unexported packages

Your `module-info.java`:

```java
module moduleone {
    requires java.net.http;

    exports com.moduleone.dtos;
    exports com.moduleone.service;
}
```

says:

*   Only `com.moduleone.dtos` and `com.moduleone.service` are visible to *other* modules
*   **BUT** all other packages (e.g., `com.moduleone.internal`, etc.) still exist inside the JAR

This is normal because:

### 🟦 JPMS ≠ packaging tool

JPMS (Java Platform Module System) controls *visibility* and *encapsulation at runtime*, not packaging.  
Maven simply compiles all your source files and puts all `.class` files into the JAR. It does **not** inspect `module-info.java` to determine which packages should be excluded.

### 🟦 Exporting ≠ Packaging

`exports` only affects module boundaries:

*   exported ⇒ accessible to other modules
*   not exported ⇒ still present, but encapsulated (not accessible)

Nothing in JPMS indicates that unexported packages should be deleted or omitted during packaging.