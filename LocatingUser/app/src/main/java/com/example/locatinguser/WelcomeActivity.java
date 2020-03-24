package com.example.locatinguser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity
{
    ImageView locSym;
    TextView abtext;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        abtext=(TextView)findViewById(R.id.abText);
        abtext.setClickable(true);;
        abtext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),AboutActivity.class));
            }
        });
        locSym=findViewById(R.id.locSym);
        locSym.setClickable(true);
        locSym.setOnClickListener(new View.OnClickListener()
                                  {
                                      @Override
                                      public void onClick(View v)
                                      {
                                          Intent intent = new Intent(WelcomeActivity.this, SwitchActivity.class);
                                          startActivity(intent);
                                      }
                                  }
        );
    }
}