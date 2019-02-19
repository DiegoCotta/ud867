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
import com.udacity.gradle.builditbigger.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponse {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    public void tellJoke(View view) throws ExecutionException, InterruptedException {
        mBinding.progressBar.setVisibility(View.VISIBLE);
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.callback = this;
        asyncTask.execute().get();
    }


    @Override
    public void processFinish(String Joke) {
        if(Joke == null){
            Toast.makeText(this, R.string.error_get_joke,Toast.LENGTH_SHORT).show();
            mBinding.progressBar.setVisibility(View.GONE);
        } else {
            Intent intent = new Intent(this, ShowJokeActivity.class);
            intent.putExtra(ShowJokeActivity.JOKE_TEXT_KEY, Joke);
            mBinding.progressBar.setVisibility(View.GONE);
            startActivity(intent);
        }
    }
}
