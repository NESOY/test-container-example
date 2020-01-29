## TestContainer

#### Requirements
- Docker


#### Getting Started
- gradle
```gradle
testImplementation("org.testcontainers:testcontainers:1.12.0")
```

#### JUnit 4 
```java
@Rule
public GenericContainer mongoDbContainer = new GenericContainer("mongo:4.0.10");
```
