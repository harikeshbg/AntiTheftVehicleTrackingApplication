package com.example.locatinguser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SwitchActivity extends AppCompatActivity
{
    Switch userSwitch,deviceSwitch;
    public void goPressed(View view)
    {
        if(deviceSwitch.isChecked()==true)
        {
            startActivity(new Intent(this.getApplicationContext(),AunthenticationActivity.class));
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        userSwitch=(Switch)findViewById(R.id.userButton);
        deviceSwitch=(Switch)findViewById(R.id.deviceButton);
        userSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b==true)
                {
                    if(deviceSwitch.isChecked()==true)
                    {
                        deviceSwitch.setChecked(false);
                    }
                }
            }
        });
        deviceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (b==true)
                {
                    if(userSwitch.isChecked()==true)
                    {
                        userSwitch.setChecked(false);
                    }
                }
            }
        });
    }
}
