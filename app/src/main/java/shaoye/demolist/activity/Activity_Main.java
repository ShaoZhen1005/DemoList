package shaoye.demolist.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import shaoye.demolist.R;

public class Activity_Main extends AppCompatActivity {
    private final String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "-->onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "-->onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "-->onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "-->onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "-->onDestroy");
        super.onDestroy();
    }

}
