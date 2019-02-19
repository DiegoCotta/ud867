package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.showjoke.ShowJokeActivity;
import com.udacity.gradle.builditbigger.databinding.ActivityMainBinding;


import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.AsyncResponse {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }

    public void tellJoke(View view) throws ExecutionException, InterruptedException {
        binding.progressBar.setVisibility(View.VISIBLE);
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        asyncTask.callback = this;
        asyncTask.execute(new Pair<Context, String>(this, "")).get();
    }


    @Override
    public void processFinish(String Joke) {
        Intent intent = new Intent(this, ShowJokeActivity.class);
        intent.putExtra(ShowJokeActivity.JOKE_TEXT_KEY, Joke);
        binding.progressBar.setVisibility(View.GONE);
        startActivity(intent);
    }
}
