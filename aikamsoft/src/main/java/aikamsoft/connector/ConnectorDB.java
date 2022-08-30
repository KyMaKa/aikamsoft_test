package aikamsoft.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorDB {

  private final Connection connection;

  public ConnectorDB() {

    this.connection = initConnection();

  }

  private Connection initConnection() {
    Connection connection = null;

    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager
          .getConnection("jdbc:postgresql://localhost:5432/aikamsoft",
              "postgres", "Pai4Piqwerty");
      System.out.println("DB opened successfully.");

    } catch (Exception e) {
      e.printStackTrace();
    }
    return connection;
  }

  public Connection getConnection() {
    return this.connection;
  }

  public void closeConnection() throws SQLException {
    this.connection.close();
  }

}
