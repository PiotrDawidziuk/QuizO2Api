package pl.piotrdawidziuk.quizo2api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question implements Parcelable {
    String text;
    ArrayList<Answer> answers;

    public Question(String text, ArrayList<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public static Creator<Question> getCREATOR() {
        return CREATOR;
    }

    protected Question(Parcel in) {
        text = in.readString();
        answers = in.createTypedArrayList(Answer.CREATOR);
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
        parcel.writeTypedList(answers);
    }
}
