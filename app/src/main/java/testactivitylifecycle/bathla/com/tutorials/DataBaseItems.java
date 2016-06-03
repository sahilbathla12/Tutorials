package testactivitylifecycle.bathla.com.tutorials;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bathla on 4/26/2016.
 */
public class DataBaseItems implements Parcelable{


    List<DatabaseItem> itemList =null;

    protected DataBaseItems(Parcel in) {
    }

    public static final Creator<DataBaseItems> CREATOR = new Creator<DataBaseItems>() {
        @Override
        public DataBaseItems createFromParcel(Parcel in) {
            return new DataBaseItems(in);
        }

        @Override
        public DataBaseItems[] newArray(int size) {
            return new DataBaseItems[size];
        }
    };

    public List<DatabaseItem> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<DatabaseItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
