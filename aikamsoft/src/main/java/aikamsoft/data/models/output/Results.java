package aikamsoft.data.models.output;

import java.util.List;

public class Results {

  private String type;

  private List<CriteriaResults> results;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<CriteriaResults> getResults() {
    return results;
  }

  public void setResults(List<CriteriaResults> results) {
    this.results = results;
  }
}
