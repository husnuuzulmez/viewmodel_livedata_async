package hsn.com.viewmodel_livedata_async;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

public class myviewmodel extends ViewModel {

    private MutableLiveData<Integer> myint;
    Myasync mys;



    public MutableLiveData<Integer> getMyint() {
        if (myint==null)
        {
            myint = new MutableLiveData<>();
            myint.setValue(0);


            mys = new Myasync();
            mys.execute(100);

        }
        return myint;
    }

    public void setMyint(MutableLiveData<Integer> myint) {
        this.myint = myint;
    }

    class Myasync extends AsyncTask<Integer,Integer,Integer> {


        @Override
        protected Integer doInBackground(Integer... voids) {
            int k = voids[0];

            int r=myint.getValue();

            while (r<voids[0]) {
                try {

                    Thread.sleep(500);
                    r=myint.getValue();
                    r++;
                    publishProgress(r++);



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return 155;
        }

        @Override
        protected void onPostExecute(Integer aVoid) {
            super.onPostExecute(aVoid);
            myint.setValue(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate();
            Log.d("abc", "updating"+values[0]);
            myint.setValue(values[0]);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
    }

}


