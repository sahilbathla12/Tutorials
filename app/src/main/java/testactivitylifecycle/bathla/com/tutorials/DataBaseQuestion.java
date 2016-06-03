package testactivitylifecycle.bathla.com.tutorials;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by bathla on 4/27/2016.
 */

/*
    <QuestionNo>Question no 1</QuestionNo>
                    <Question>What is Your Name</Question>
                    <options>
                        <option1> A  </option1>
                        <option2> B  </option2>
                        <option3> C  </option3>
                        <option4> D  </option4>
                    </options>
                    <TypeofQuestion></TypeofQuestion>
                    <Answer>A</Answer>
                    <Explanation>SAHIL</Explanation>
 */
public class DataBaseQuestion implements Parcelable {

    String Question =null;
    String QuestionNo =null;
    List<String> optionList=null;
    String typeOfQuestion=null;
    String Answer = null;
    String Explanation = null;

    DataBaseQuestion()
    {

    }
    protected DataBaseQuestion(Parcel in) {
        Question = in.readString();
        QuestionNo = in.readString();
        optionList = in.createStringArrayList();
        typeOfQuestion = in.readString();
        Answer = in.readString();
        Explanation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Question);
        dest.writeString(QuestionNo);
        dest.writeStringList(optionList);
        dest.writeString(typeOfQuestion);
        dest.writeString(Answer);
        dest.writeString(Explanation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataBaseQuestion> CREATOR = new Creator<DataBaseQuestion>() {
        @Override
        public DataBaseQuestion createFromParcel(Parcel in) {
            return new DataBaseQuestion(in);
        }

        @Override
        public DataBaseQuestion[] newArray(int size) {
            return new DataBaseQuestion[size];
        }
    };

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getQuestionNo() {
        return QuestionNo;
    }

    public void setQuestionNo(String questionNo) {
        QuestionNo = questionNo;
    }

    public List<String> getOptions() {
        return optionList;
    }

    public void setOptions(List<String> optionList) {
        this.optionList = optionList;
    }

    public String getTypeOfQuestion() {
        return typeOfQuestion;
    }

    public void setTypeOfQuestion(String typeOfQuestion) {
        this.typeOfQuestion = typeOfQuestion;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        Explanation = explanation;
    }

    @Override
    public String toString() {
        return "DataBaseQuestion{" +
                "Question='" + Question + '\'' +
                ", QuestionNo='" + QuestionNo + '\'' +
                ", options=" + optionList +
                ", typeOfQuestion='" + typeOfQuestion + '\'' +
                ", Answer='" + Answer + '\'' +
                ", Explanation='" + Explanation + '\'' +
                '}';
    }
}
