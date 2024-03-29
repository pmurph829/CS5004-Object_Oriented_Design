package cs5004.questionnaire;

/** Enum that holds allowed values for the Likert responses. */
public enum LikertResponseOption {
  STRONGLY_DISAGREE(-2, "Strongly Disagree"),
  DISAGREE(-1, "Disagree"),
  NEUTRAL(0, "Neither Agree Nor Disagree"),
  AGREE(1, "Agree"),
  STRONGLY_AGREE(2, "Strongly Agree");

  private final int val;
  private final String txt;

  LikertResponseOption(int val, String txt) {
    this.val = val;
    this.txt = txt;
  }

  int getValue() {
    return val;
  }

  String getText() {
    return txt;
  }
}
