package yoshinorihirosetraining.gmail.com.parcelablesample;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);

        if (savedInstanceState == null) {
            data = new Data();
            data.count = 0;
        } else {
            data = savedInstanceState.getParcelable("data");
        }

        button.setText("" + data.count);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("data", data);
    }

    public void onClickButton(View view) {
        data.count += 1;
        button.setText("" + data.count);
    }

    /**
     * Data of Activity.
     */

    private static class Data implements Parcelable {
        public int count;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(count);
        }

        public static final Parcelable.Creator<Data> CREATOR = new Creator();

        private static class Creator implements Parcelable.Creator<Data> {
            public Data createFromParcel(Parcel in) {
                Data data = new Data();
                data.count = in.readInt();
                return data;
            }

            public Data[] newArray(int size) {
                return new Data[size];
            }
        }
    }
}
