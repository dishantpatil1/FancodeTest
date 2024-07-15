Copy code
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class FanCodeUserTest {

    @BeforeClass
    public void setUp() {
        // Set base URI for RestAssured
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void testFanCodeUsersTodosCompletion() {
        // Fetch users from the API
        Response usersResponse = get("/users");
        assertEquals(usersResponse.getStatusCode(), 200, "Failed to fetch users");

        // Extract users as List of Maps
        List<Map<String, Object>> users = usersResponse.jsonPath().getList("$");

        // Iterate over each user
        for (Map<String, Object> user : users) {
            if (isFanCodeCity(user)) {
                int userId = (int) user.get("id");

                // Fetch todos for the user
                Response todosResponse = get("/todos?userId=" + userId);
                assertEquals(todosResponse.getStatusCode(), 200, "Failed to fetch todos");

                // Extract todos as List of Maps
                List<Map<String, Object>> todos = todosResponse.jsonPath().getList("$");

                // Calculate completion percentage
                double completionPercentage = calculateCompletionPercentage(todos);

                // Assert completion percentage > 50
                assertTrue(completionPercentage > 50.0,
                        String.format("User %s from FanCode city has %s%% todos completed.", user.get("name"), completionPercentage));
            }
        }
    }

    // Method to check if user belongs to FanCode city based on lat/lng criteria
    private boolean isFanCodeCity(Map<String, Object> user) {
        Map<String, Object> address = (Map<String, Object>) user.get("address");
        Map<String, Object> geo = (Map<String, Object>) address.get("geo");
        double lat = (double) geo.get("lat");
        double lng = (double) geo.get("lng");
        return (lat >= -40 && lat <= 5 && lng >= 5 && lng <= 100);
    }

    // Method to calculate completion percentage of todos
    private double calculateCompletionPercentage(List<Map<String, Object>> todos) {
        if (todos == null || todos.isEmpty()) {
            return 0.0;
        }
        int totalTodos = todos.size();
        int completedCount = 0;
        for (Map<String, Object> todo : todos) {
            boolean completed = (boolean) todo.get("completed");
            if (completed) {
                completedCount++;
            }
        }
        return (completedCount * 100.0) / totalTodos;
    }
}