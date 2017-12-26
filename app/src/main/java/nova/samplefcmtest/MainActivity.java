package nova.samplefcmtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class MainActivity extends AppCompatActivity {
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseMessaging.getInstance().subscribeToTopic("news");
         token = FirebaseInstanceId.getInstance().getToken() ;


         SendTask sendTask = new SendTask();
         sendTask.execute();
    }


    public class SendTask extends AsyncTask<Void, String, String> {



        @Override
        protected String doInBackground(Void... voids) {

            sendTest(token);
            return null;
        }


        @Override

        protected void onPostExecute(String result) {

            super.onPostExecute(result);


        }

    }
    private void sendTest(String token) {
        // Add custom implementation, as needed.
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token", token)
                .build();

        //request
        Request request = new Request.Builder()
                .url("http://115.68.231.13/project/android/fcm/fcmTestSend.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
