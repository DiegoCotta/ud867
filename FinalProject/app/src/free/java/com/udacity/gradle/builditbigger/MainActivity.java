package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.example.showjoke.ShowJokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponse {

    ActivityMainBinding mBinding;
    private InterstitialAd mInterstitialAd;
    public String mJoke = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_Id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                callShowJoke();
            }
        });

    }

    private void callShowJoke() {
        mBinding.progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(MainActivity.this, ShowJokeActivity.class);
        intent.putExtra(ShowJokeActivity.JOKE_TEXT_KEY, mJoke);
        startActivity(intent);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void tellJoke(View view) throws ExecutionException, InterruptedException {
        mBinding.progressBar.setVisibility(View.VISIBLE);
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.callback = this;
        asyncTask.execute().get();
    }


    @Override
    public void processFinish(String Joke) {
        mJoke = Joke;
        if (Joke == null) {
            Toast.makeText(this, R.string.error_get_joke, Toast.LENGTH_SHORT).show();
            mBinding.progressBar.setVisibility(View.GONE);
        } else if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            callShowJoke();
        }

    }
}
