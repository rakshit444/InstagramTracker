package com.jain.rakshit.instagramtrackerfinal.Activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.jain.rakshit.instagramtrackerfinal.Network.InstagramApp;
import com.jain.rakshit.instagramtrackerfinal.Network.InstagramApp.OAuthAuthenticationListener;
import com.jain.rakshit.instagramtrackerfinal.ObjectWrapperForBinder;
import com.jain.rakshit.instagramtrackerfinal.R;
import com.jain.rakshit.instagramtrackerfinal.util.ApplicationData;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    public Context mContext;
    private InstagramApp mApp;
    OAuthAuthenticationListener listener = new OAuthAuthenticationListener() {

        @Override
        public void onSuccess() {
            Intent intent = new Intent(getApplicationContext(), NavBarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("object", new ObjectWrapperForBinder(mApp));
            startActivity(intent);

        }

        @Override
        public void onFail(String error) {
            Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
        }
    };
    private Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mContext = MainActivity.this;

        mApp = new InstagramApp(getApplicationContext(), MainActivity.this, ApplicationData.Client_ID,
                ApplicationData.Client_Secret_ID, ApplicationData.CallBackURL);
        mApp.setListener(listener);


        btnConnect = (Button) findViewById(R.id.btnConnect);

        if (mApp.hasAccessToken()) {
            Log.i(TAG, mApp.hasAccessToken() + " Starting NavBarActivity");
            Intent intent = new Intent(this, NavBarActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("object", new ObjectWrapperForBinder(mApp));
            startActivity(intent);
        }
        btnConnect.setOnClickListener(new OnClickListener() {

                                          @Override
                                          public void onClick(View view) {

                                              mApp.authorize();
                                          }
                                      }
        );

        if (mApp.hasAccessToken()) {
            Log.i(TAG, mApp.hasAccessToken() + "");


            btnConnect.setText("Disconnect");


        }

    }


}
