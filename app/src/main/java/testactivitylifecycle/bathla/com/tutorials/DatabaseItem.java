package testactivitylifecycle.bathla.com.tutorials;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by bathla on 4/26/2016.
 */
public class DatabaseItem implements  Parcelable{

    String Topic = null;
    String example =null;
    String description = null;
    List<String> exampleList= null;
    List<DataBaseQuestion> practiceQuestionList= null;

    DatabaseItem()
    {

    }

    protected DatabaseItem(Parcel in) {
        Topic = in.readString();
        example = in.readString();
        description = in.readString();
        exampleList = in.createStringArrayList();
        practiceQuestionList = in.createTypedArrayList(DataBaseQuestion.CREATOR);

    }

    public static final Creator<DatabaseItem> CREATOR = new Creator<DatabaseItem>() {
        @Override
        public DatabaseItem createFromParcel(Parcel in) {
            return new DatabaseItem(in);
        }

        @Override
        public DatabaseItem[] newArray(int size) {
            return new DatabaseItem[size];
        }
    };

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getExampleList() {
        return exampleList;
    }

    public void setExampleList(List<String> exampleList) {
        this.exampleList = exampleList;
    }

    public List<DataBaseQuestion> getPracticeQuestionList() {
        return practiceQuestionList;
    }

    public void setPracticeQuestionList(List<DataBaseQuestion> practiceQuestionList) {
        this.practiceQuestionList = practiceQuestionList;
    }
//
//    @Override
//    public String toString() {
//        return "DatabaseItem{" +
//                "Topic='" + Topic + '\'' +
//                ", example='" + example + '\'' +
//                ", description='" + description + '\'' +
//                ", exampleList=" + exampleList +
//                ", practiceQuestionList=" + practiceQuestionList +
//                '}';
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Topic);
        dest.writeString(example);
        dest.writeString(description);
        dest.writeTypedList(practiceQuestionList);
        dest.writeStringList(exampleList);
    }
}
