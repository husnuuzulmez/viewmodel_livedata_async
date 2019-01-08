package hsn.com.viewmodel_livedata_async;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public TextView t1,t2;
    myviewmodel model;
    int ll=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("abc", "created");

        t2 = findViewById(R.id.text2);



        model = ViewModelProviders.of(this).get(myviewmodel.class);

        model.getMyint().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                ll = integer;
                t2.setText(String.valueOf(ll));
            }
        });



        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = ll;
                MutableLiveData<Integer> myint = model.getMyint();
                myint.setValue(k+10);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("abc","destroyed");
    }

}
