package com.example.kunal_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class DashboardActivity extends BaseActivity {
    AppCompatEditText secretCode;
    AppCompatButton joinButton, shareButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeStatusBarWithWhiteIcon();
        setContentView(R.layout.activity_dashboardactivity);
        secretCode = findViewById(R.id.codebox);
        shareButton = findViewById(R.id.shareButton);
        // using try catch block to handle exceptions
        try {
            // object creation of JitsiMeetConferenceOptions
            // class by the name of options
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))
                    //.setWelcomePageEnabled(false)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        joinButton = findViewById(R.id.joinbutton);
        joinButton.setOnClickListener(view ->
        {
            String text = Objects.requireNonNull(secretCode.getText()).toString();

            if (text.length() > 0)
            {
                JitsiMeetConferenceOptions options
                        = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(text)
                        .build();
                JitsiMeetActivity.launch(DashboardActivity.this, options);
            }

        });
    }
}