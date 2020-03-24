package com.example.locatinguser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InterfaceActivity extends AppCompatActivity
{
    Button trackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);
        trackButton=findViewById(R.id.trackButton);
        trackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*FirebaseAuth fAuth=FirebaseAuth.getInstance();
                String uid=fAuth.getCurrentUser().getUid();
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("OnlineDevices").child(uid);
                Toast.makeText(InterfaceActivity.this, "uid:"+uid, Toast.LENGTH_SHORT).show();
                OnlineDevices device=new OnlineDevices();
                device.setUserid(uid);
                String id=ref.push().getKey();
                ref.child(id).setValue(device);*/
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
            }
        });
    }
}
