

---

# API Testing with RestAssured in Eclipse

This guide provides step-by-step instructions to set up and run an API testing project using RestAssured in Eclipse IDE.

## Prerequisites

Before starting, ensure you have the following installed:
- Java Development Kit (JDK)
- Eclipse IDE
- Internet connection for Maven dependencies

## Installation Steps

### 1. Clone the Project

Clone the project repository from Git into your local workspace.

```bash
git clone <repository-url>
```

### 2. Import the Project into Eclipse

1. Open Eclipse IDE.
2. Go to `File` -> `Import...`.
3. Select `Existing Maven Projects` and click `Next`.
4. Browse to the root directory of the cloned project and click `Finish`.

### 3. Install Maven Dependencies

Ensure all necessary dependencies are downloaded and updated:

1. Right-click on the project in the Project Explorer.
2. Select `Maven` -> `Update Project...`.
3. Check `Force Update of Snapshots/Releases` and click `OK`.

### 4. Set Up RestAssured and TestNG Dependencies

Verify that the following dependencies are added to your `pom.xml` file:

```xml
<dependencies>
    <!-- RestAssured -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>4.4.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Gson for JSON serialization/deserialization -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.8</version>
    </dependency>

    <!-- TestNG for testing -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.4.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 5. Run the Test

1. Locate `FanCodeUserTest.java` under `src/test/java`.
2. Right-click on the file and select `Run As` -> `TestNG Test`.

### 6. Verify Test Results

After running the test, review the console output in Eclipse to ensure all assertions pass. This confirms that users from the `FanCode` city have completed more than 50% of their todos.

---



