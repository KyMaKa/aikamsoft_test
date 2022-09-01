package aikamsoft.finder;

import aikamsoft.connector.ConnectorDB;
import aikamsoft.objects.entitys.Person;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Finder {

  Connection connection;

  public Finder() {
    ConnectorDB connectorDB = new ConnectorDB();
    this.connection = connectorDB.getConnection();
  }

  public List<Person> getByLastName(String lastName) throws SQLException {
    Statement stmt = connection.createStatement();
    String sql = "SELECT * FROM shopping.buyers WHERE last_name = "
        + "'" + lastName + "'" + ";";
    ResultSet resultSet = stmt.executeQuery(sql);
    return mapPersonToList(resultSet);
  }

  public List<Person> getByProduct(String productName, int boughtCount) throws SQLException {
    Statement stmt = connection.createStatement();

    String item = "SELECT i.id FROM shopping.items as i WHERE name = "
        + "'" + productName + "'";

    String counter = "SELECT p.buyer_id, COUNT(p.buyer_id) as count"
        + " FROM shopping.purchases as p  WHERE item_id in"
        + "(SELECT id FROM product_id) GROUP BY p.buyer_id";

    String sql = "WITH product_id AS (" + item + ")"
        + ",c AS (" + counter + ")\n"
        + "SELECT * FROM shopping.buyers as b WHERE b.id in (SELECT buyer_id FROM c)"
        + " AND (SELECT count FROM c) >= " + boughtCount + ";";

    ResultSet resultSet = stmt.executeQuery(sql);
    return mapPersonToList(resultSet);
  }

  public List<Person> getByPrice(int min, int max) throws SQLException {
    Statement stmt = connection.createStatement();
    String sql = "WITH EXPENSES AS\n"
        + "(SELECT P.BUYER_ID,\n"
        + "SUM(I.PRICE) AS SUMM\n"
        + "FROM SHOPPING.PURCHASES AS P\n"
        + "INNER JOIN SHOPPING.ITEMS AS I ON P.ITEM_ID = I.ID\n"
        + "GROUP BY P.BUYER_ID\n"
        + "ORDER BY P.BUYER_ID)\n"
        + "SELECT *\n"
        + "FROM SHOPPING.BUYERS AS B\n"
        + "WHERE B.ID in\n"
        + "(SELECT BUYER_ID\n"
        + "FROM EXPENSES)\n"
        + "AND\n"
        + "(SELECT SUMM\n"
        + "FROM EXPENSES\n"
        + "WHERE B.ID = BUYER_ID) > " + min + "\n"
        + "AND\n"
        + "(SELECT SUMM\n"
        + "FROM EXPENSES\n"
        + "WHERE B.ID = BUYER_ID) < "+ max + ";";

    ResultSet resultSet = stmt.executeQuery(sql);
    return mapPersonToList(resultSet);
  }

  public List<Person> getByLeastPurchases(int countPerson) throws SQLException {
    Statement stmt = connection.createStatement();
    String sql = "WITH counter as "
        + "(SELECT p.buyer_id, COUNT(p.item_id) as cnt "
        + "FROM shopping.purchases as p GROUP BY p.buyer_id ORDER BY cnt)\n"
        + "SELECT * FROM shopping.buyers as b WHERE b.id in "
        + "(SELECT buyer_id FROM counter) LIMIT " + countPerson + ";";
    ResultSet resultSet = stmt.executeQuery(sql);
    return mapPersonToList(resultSet);
  }

  public void getStatistic(LocalDate startDate, LocalDate endDate) throws SQLException {
    Statement stmt = connection.createStatement();

    String sql =
        "WITH items as (SELECT DISTINCT on (p.buyer_id, p.item_id) p.buyer_id, p.item_id, p.date "
            + "FROM shopping.purchases as p WHERE p.date >= '" + startDate.toString() + "'::date AND p.date <= '"
            + endDate + "'::date)\n"
            + "SELECT DISTINCT b.first_name, b.last_name, i.name, i.price "
            + "FROM shopping.buyers as b, shopping.items as i\n"
            + "WHERE b.id in (SELECT buyer_id FROM items WHERE i.id = item_id);";
    ResultSet resultSet = stmt.executeQuery(sql);
    resultSet.next();
    System.out.println(resultSet.getString("first_name"));
  }

  private List<Person> mapPersonToList(ResultSet resultSet) throws SQLException {
    List<Person> people = new LinkedList<>();
    while (resultSet.next()) {
      Person person = new Person();
      person.setFirstName(resultSet.getString("first_name"));
      person.setLastName(resultSet.getString("last_name"));
      people.add(person);
    }
    return people;
  }
}
