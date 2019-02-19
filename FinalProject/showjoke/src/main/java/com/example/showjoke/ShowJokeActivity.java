package com.example.showjoke;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.showjoke.databinding.ActivityShowJokeBinding;

public class ShowJokeActivity extends AppCompatActivity {

    public static final String JOKE_TEXT_KEY = "joke-text-key";

    ActivityShowJokeBinding binding;

    private String jokeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_joke);

        if (getIntent() != null && getIntent().hasExtra(JOKE_TEXT_KEY)) {
            jokeText = getIntent().getStringExtra(JOKE_TEXT_KEY);
        } else {
            Toast.makeText(this, R.string.error_joke_text, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        binding.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.tvJokeText.setText(jokeText);

    }
}
