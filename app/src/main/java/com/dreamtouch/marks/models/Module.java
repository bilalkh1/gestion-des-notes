package com.dreamtouch.marks.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Module implements Parcelable {
    private final String name;
    private float marks;

    public Module(String name, float marks) {
        this.name = name;
        this.marks = marks;
    }

    protected Module(Parcel in) {
        name = in.readString();
        marks = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeFloat(marks);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Module> CREATOR = new Creator<Module>() {
        @Override
        public Module createFromParcel(Parcel in) {
            return new Module(in);
        }

        @Override
        public Module[] newArray(int size) {
            return new Module[size];
        }
    };

    public String getName() {
        return name;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Module{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
