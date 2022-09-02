package aikamsoft.main;

public class CustomException extends Exception{
  private final String message;

  public CustomException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;

  }
}
