package aikamsoft.data.models.output;

public class Error {
  private final String type = "error";

  private String message;

  public Error(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
