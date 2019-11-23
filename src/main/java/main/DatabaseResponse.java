package main;

public interface DatabaseResponse<T> {
     String getQuery();
     boolean isSuccess();
     String getResponse();
     T getValue();
}
