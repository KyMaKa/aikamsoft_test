package aikamsoft.service.finder;

import aikamsoft.service.connector.ConnectorDB;
import aikamsoft.data.models.output.Item;
import aikamsoft.data.models.output.Person;
import aikamsoft.data.models.output.PersonStatistic;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Connects to DB and executes queries to retrieve needed data.*
 */
public class Finder {

  Connection connection;

  public Finder() {
    ConnectorDB connectorDB = new ConnectorDB();
    this.connection = connectorDB.getConnection();
  }

  /**
   * Finds records with given last name*
   * @param lastName to search.
   * @return list of persons with given last name.
   * @throws SQLException if something goes wrong with connection.
   */
  public List<Person> getByLastName(String lastName) throws SQLException {
    Statement stmt = connection.createStatement();
    String sql = "SELECT * FROM shopping.buyers WHERE last_name = " + "'" + lastName + "'" + ";";
    ResultSet resultSet = stmt.executeQuery(sql);
    return mapPersonToList(resultSet);
  }

  /**
   * Find persons that bought product with productName at least n times.*
   * @param productName product.
   * @param boughtCount number of tiems bought.
   * @return list of persons.
   * @throws SQLException if something goes wrong with connection.
   */
  public List<Person> getByProduct(String productName, int boughtCount) throws SQLException {
    Statement stmt = connection.createStatement();

    String sql = "WITH product_id AS (" + "SELECT i.id FROM shopping.items as i WHERE name = " + "'"
        + productName + "'" + ")" + ",c AS (" + "SELECT p.buyer_id, COUNT(p.buyer_id) as count"
        + " FROM shopping.purchases as p  WHERE item_id in"
        + "(SELECT id FROM product_id) GROUP BY p.buyer_id" + ")\n"
        + "SELECT * FROM shopping.buyers as b WHERE b.id in (SELECT buyer_id FROM c)"
        + " AND (SELECT count FROM c) >= " + boughtCount + ";";

    ResultSet resultSet = stmt.executeQuery(sql);
    return mapPersonToList(resultSet);
  }

  /**
   * Finds persons that bought products > min and < max.*
   * @param min lover bound.
   * @param max upper bound.
   * @return list of persons.
   * @throws SQLException if something goes wrong with connection.
   */
  public List<Person> getByPrice(int min, int max) throws SQLException {
    Statement stmt = connection.createStatement();
    String sql = "WITH EXPENSES AS\n" + "(SELECT P.BUYER_ID,\n" + "SUM(I.PRICE) AS SUMM\n"
        + "FROM SHOPPING.PURCHASES AS P\n" + "INNER JOIN SHOPPING.ITEMS AS I ON P.ITEM_ID = I.ID\n"
        + "GROUP BY P.BUYER_ID\n" + "ORDER BY P.BUYER_ID)\n" + "SELECT *\n"
        + "FROM SHOPPING.BUYERS AS B\n" + "WHERE B.ID in\n" + "(SELECT BUYER_ID\n"
        + "FROM EXPENSES)\n" + "AND\n" + "(SELECT SUMM\n" + "FROM EXPENSES\n"
        + "WHERE B.ID = BUYER_ID) > " + min + "\n" + "AND\n" + "(SELECT SUMM\n" + "FROM EXPENSES\n"
        + "WHERE B.ID = BUYER_ID) < " + max + ";";

    ResultSet resultSet = stmt.executeQuery(sql);
    return mapPersonToList(resultSet);
  }

  /**
   * Finds persons that bought the least items.*
   * @param countPerson how many persons to retrieve.
   * @return list of persons.
   * @throws SQLException if something goes wrong with connection.
   */
  public List<Person> getByLeastPurchases(int countPerson) throws SQLException {
    Statement stmt = connection.createStatement();
    String sql = "WITH counter as " + "(SELECT p.buyer_id, COUNT(p.item_id) as cnt "
        + "FROM shopping.purchases as p GROUP BY p.buyer_id ORDER BY cnt)\n"
        + "SELECT * FROM shopping.buyers as b WHERE b.id in "
        + "(SELECT buyer_id FROM counter) LIMIT " + countPerson + ";";
    ResultSet resultSet = stmt.executeQuery(sql);
    return mapPersonToList(resultSet);
  }

  /**
   * Finds statistics for a given period of time, excluding weekends.*
   * Ex: each person who bought something in that period *
   * with listing all products, their price, total price spent by each person *
   * and all persons together. *
   * @param startDate start date.
   * @param endDate end date.
   * @return list of persons.
   * @throws SQLException if something goes wrong with connection.
   */
  public List<PersonStatistic> getStatistic(LocalDate startDate, LocalDate endDate)
      throws SQLException {
    Statement stmt = connection.createStatement();

    String sql =
        "WITH items as (SELECT DISTINCT on (p.buyer_id, p.item_id) p.buyer_id, p.item_id, p.date "
            + "FROM shopping.purchases as p WHERE p.date >= '" + startDate.toString()
            + "'::date AND p.date <= '" + endDate
            + "'::date AND EXTRACT('ISODOW' FROM p.date) < 6)\n"
            + ", summ as (SELECT p.buyer_id, p.item_id, SUM(i.price) as price \n"
            + "FROM shopping.purchases as p, shopping.items as i WHERE p.item_id = i.id "
            + "GROUP BY p.buyer_id, p.item_id ORDER BY buyer_id)\n"
            + "SELECT DISTINCT b.first_name, b.last_name, i.name, summ.price\n"
            + "FROM shopping.buyers as b, shopping.items as i, summ \n"
            + "WHERE b.id in (SELECT buyer_id FROM items WHERE i.id = item_id) AND i.id = summ.item_id\n"
            + "GROUP BY b.first_name, b.last_name, i.name, summ.price;";
    ResultSet resultSet = stmt.executeQuery(sql);
    return mapItemsToPersonList(resultSet);
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

  private List<PersonStatistic> mapItemsToPersonList(ResultSet resultSet) throws SQLException {

    List<PersonStatistic> personStatistics = new LinkedList<>();

    PersonStatistic predPerson = new PersonStatistic();

    while (resultSet.next()) {
      PersonStatistic person = new PersonStatistic();
      Item item = new Item();
      person.setName(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
      item.setName(resultSet.getString("name"));
      item.setExpenses(resultSet.getInt("price"));
      if (predPerson.getName() == null) {
        predPerson = person;
      }
      if (predPerson.getName().equals(person.getName())) {
        predPerson.setTotalExpenses(predPerson.getTotalExpenses() + item.getExpenses());
        predPerson.getPurchases().add(item);
      } else {
        person.setTotalExpenses(item.getExpenses());
        person.getPurchases().add(item);
        personStatistics.add(predPerson);
        predPerson = person;
      }
    }
    personStatistics.add(predPerson);
    return personStatistics;
  }
}
