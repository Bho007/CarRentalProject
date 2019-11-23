package main;

public class TestDatabaseResponse<T> implements DatabaseResponse<T> {
    private String query;
    private boolean success;
    private String response;
    private T value;
    
    public TestDatabaseResponse(String query, boolean success, String response, T value) {
        this.query = query;
        this.success = success;
        this.response = response;
        this.value = value;
    }
    
    @Override
    public String getQuery() {
        return query;
    }
    
    @Override
    public boolean isSuccess() {
        return success;
    }
    
    @Override
    public String getResponse() {
        return response;
    }
    
    @Override
    public T getValue() {
        return value;
    }
}
