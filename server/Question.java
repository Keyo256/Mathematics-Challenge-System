package server;

public class Question {
    private int questionNo;
    private String text;
    private String answer;
    private int marks;
    public Question(int questionNo,String text,String answer,int marks){
        this.questionNo = questionNo;
        this.text= text;
        this.answer= answer;
        this.marks = marks;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }
    public int getQuestionNo() {
        return questionNo;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
    public int getMarks() {
        return marks;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
    public static void scoredMarks(int marks){

    }
}
