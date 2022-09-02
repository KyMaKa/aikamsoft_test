package aikamsoft.data.models.output;

import aikamsoft.data.models.input.Criteria;
import java.util.List;

public class CriteriaResults {

  private Criteria criteria;

  private List<Person> results;

  public Criteria getCriteria() {
    return criteria;
  }

  public void setCriteria(Criteria criteria) {
    this.criteria = criteria;
  }

  public List<Person> getResults() {
    return results;
  }

  public void setResults(List<Person> people) {
    this.results = people;
  }
}
