package cs5004.questionnaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class QuestionnaireImpl implements Questionnaire{
  private List<String> idList; // Contains the identifiers for each Question in order
  private HashMap<String, Question> qHashMap; // Contains id:Question pairs

  public QuestionnaireImpl(){
    this.idList = new ArrayList<>();
    this.qHashMap = new HashMap<>();
  }
  /**
   * Create a string representation of a Questionnaire.
   * @return string representation.
   */
  public String toString(){
    String s = "";
    for (String id : idList){
      Question q = qHashMap.get(id);
      s += q.toString();
    }
    return s;
  }

  /**
   * Create a list of all question objects. Useful for filtering. Questions added to the list are
   * copies of the original question objects.
   * @return a list of all questions in the questionnaire.
   */
  private List<Question> createQuestionList(){
    List<Question> questionList = new ArrayList<>();
    for (String id : idList){
      Question q = qHashMap.get(id);
      questionList.add(q.copy());
    }
    return questionList;
  }

  @Override
  public void addQuestion(String identifier, Question q) {
    if (idList.contains(identifier)) {
      throw new IllegalArgumentException(String.format("Identifier already exists: %s.",identifier));
    }
    idList.add(identifier);
    qHashMap.put(identifier, q);
  }

  @Override
  public void removeQuestion(String identifier) {
    if (! idList.contains(identifier)){
      throw new IllegalArgumentException(String.format(
              "Could not find question identifier: %s.", identifier));
    }
    idList.remove(identifier);
    qHashMap.remove(identifier);
  }

  @Override
  public Question getQuestion(int num) {
    if (num > idList.size()){
      throw new IllegalArgumentException(String.format("Could not find question %d.", num));
    }
    return qHashMap.get(idList.get(num-1));
  }

  @Override
  public Question getQuestion(String identifier) {
    if (! idList.contains(identifier)){
      throw new IllegalArgumentException(String.format(
              "Could not find question identifier: %s.", identifier));
    }
    return qHashMap.get(identifier);
  }

  @Override
  public List<Question> getRequiredQuestions() {
    Predicate<Question> p = question -> question.isRequired();
    List<Question> allQs = this.createQuestionList();
    List<Question> requiredQs = allQs
            .stream()
            .filter(p)
            .collect(Collectors.toList());
    return requiredQs;
  }

  @Override
  public List<Question> getOptionalQuestions() {
    Predicate<Question> p = question -> !question.isRequired();
    List<Question> allQs = this.createQuestionList();
    List<Question> optionalQs = allQs
            .stream()
            .filter(p)
            .collect(Collectors.toList());
    return optionalQs;
  }

  @Override
  public boolean isComplete() {
    List<Question> requiredQs = this.getRequiredQuestions();
    if (requiredQs.size() == 0){
      return true;
    }
    for (Question q : requiredQs){
      if (q.getAnswer() == ""){
        return false;
      }
    }
    return true;
  }

  @Override
  public List<String> getResponses() {
    List<String> responses = new ArrayList<>();
    for (Question q : this.createQuestionList()){
      String ans = q.getAnswer();
      if (ans != ""){
        responses.add(ans);
      }
    }
    return responses;
  }

  @Override
  public Questionnaire filter(Predicate<Question> pq) {
    List<Question> filteredQs= new ArrayList<>();

    return null;
  }

  @Override
  public void sort(Comparator<Question> comp) {

  }

  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) {
    return null;
  }
}
