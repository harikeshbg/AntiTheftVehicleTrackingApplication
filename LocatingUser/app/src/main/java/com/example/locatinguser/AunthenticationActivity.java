package com.example.locatinguser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AunthenticationActivity extends AppCompatActivity
{
    EditText logmailid,logpassword;
    Button loginButton,offButton;
    TextView regLab,displayText;
    FirebaseAuth fAuth;
    DatabaseReference ref;
    ProgressBar logBar;
    OnlineDevices device;
    String id;
    Switch userSwitch,deviceSwitch;
    ImageView img;
    public void offClicked(View view)
    {
        DatabaseReference ref2=FirebaseDatabase.getInstance().getReference("OnlineDevices").child(id);
        ref2.removeValue();
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aunthentication);
        displayText=(TextView)findViewById(R.id.displayText);
        offButton=(Button)findViewById(R.id.offbutton);
        img=(ImageView) findViewById(R.id.typeImage);
        img.setVisibility(View.INVISIBLE);
        offButton.setVisibility(View.INVISIBLE);
        userSwitch=(Switch)findViewById(R.id.userSwitch);
        deviceSwitch=(Switch)findViewById(R.id.deviceSwitch);
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
                logmailid.setHint("Email ID");
                logpassword.setHint("password");
                displayText.setText("User-Login");
                loginButton.setText("Login");
                img.setVisibility(View.VISIBLE);
                img.setImageResource(R.drawable.place);
                offButton.setVisibility(View.INVISIBLE);
                offButton.setClickable(false);
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
                logmailid.setHint("Device ID");
                logpassword.setHint("password");
                displayText.setText("Device-Login");
                loginButton.setText("Switch on");
                img.setVisibility(View.VISIBLE);
                img.setImageResource(R.drawable.deviceimage);
                offButton.setVisibility(View.VISIBLE);
                offButton.setClickable(true);
            }
        });
        logmailid=(EditText)findViewById(R.id.mailField);
        logpassword=(EditText)findViewById(R.id.pwdField);
        loginButton=(Button)findViewById(R.id.loginButton);
        regLab=(TextView)findViewById(R.id.regDisplay);
        logBar=(ProgressBar)findViewById(R.id.logProgBar);
        fAuth=FirebaseAuth.getInstance();
        device=new OnlineDevices();
        logBar.setVisibility(View.INVISIBLE);
        regLab.setClickable(true);
        regLab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(deviceSwitch.isChecked())
                {
                    final String email = logmailid.getText().toString().trim();
                    String password = logpassword.getText().toString().trim();
                    //input validation.
                    if (TextUtils.isEmpty(email)) {
                        logmailid.setError("Required Email-ID");
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        logpassword.setError("Required password");
                        return;
                    }
                    if (password.length() < 6) {
                        logpassword.setError("password must be >= 6 characters");
                        return;
                    }
                    logBar.setVisibility(View.VISIBLE);
                    //authenticating the user
                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AunthenticationActivity.this, "Logged in..", Toast.LENGTH_SHORT).show();
                                logBar.setVisibility(View.INVISIBLE);
                                startActivity(new Intent(getApplicationContext(), InterfaceActivity.class));//to move to first activity upon successful registeration
                            } else {
                                Toast.makeText(AunthenticationActivity.this, "Log in unsuccessful" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                logBar.setVisibility(View.INVISIBLE);
                            }
                            String uid = fAuth.getCurrentUser().getUid();
                            //ref= FirebaseDatabase.getInstance().getReference("OnlineDevices").child(uid);
                            ref = FirebaseDatabase.getInstance().getReference("OnlineDevices");
                            Toast.makeText(AunthenticationActivity.this, "uid:" + uid, Toast.LENGTH_SHORT).show();
                            device.setUserid(uid);
                            device.setMailid(email);
                            id = ref.push().getKey();
                            ref.child(id).setValue(device);
                        }
                    });
                }
                else
                    if(userSwitch.isChecked())
                    {

                    }
                    else
                    {
                        Toast.makeText(AunthenticationActivity.this, "Mode not specified", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }
}
