package cs5004.questionnaire;

import java.util.function.Predicate;

/** A question that takes 280 character responses as answers. */
public class ShortAnswer extends AbstractQuestion {

  /** A predicate that checks if a String has a length less than or equal to 280. */
  private final Predicate<String> p = s -> s.length() <= 280;

  /**
   * Constructor for a ShortAnswer (Question) object.
   *
   * @param prompt the question prompt.
   * @param required true if the question is required.
   */
  public ShortAnswer(String prompt, boolean required) {
    this.prompt = prompt;
    this.required = required;
  }

  @Override
  public void answer(String ans) {
    if (ans == null) {
      throw new IllegalArgumentException("Answer cannot be null.");
    }
    this.answerHelp(p, ans);
  }

  @Override
  public Question copy() {
    Question q = new ShortAnswer(this.prompt, this.required);
    return copyHelp(q);
  }
}
