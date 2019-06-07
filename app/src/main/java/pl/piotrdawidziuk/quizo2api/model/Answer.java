package pl.piotrdawidziuk.quizo2api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

    Integer order;
    String text;
    Integer isCorrect;

    public Answer(Integer order, String text, Integer isCorrect) {
        this.order = order;
        this.text = text;
        this.isCorrect = isCorrect;
    }


    protected Answer(Parcel in) {
        if (in.readByte() == 0) {
            order = null;
        } else {
            order = in.readInt();
        }
        text = in.readString();
        if (in.readByte() == 0) {
            isCorrect = null;
        } else {
            isCorrect = in.readInt();
        }
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public Integer getOrder() {
        return order;
    }

    public String getText() {
        return text;
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (order == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(order);
        }
        parcel.writeString(text);
        if (isCorrect == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(isCorrect);
        }
    }
}
