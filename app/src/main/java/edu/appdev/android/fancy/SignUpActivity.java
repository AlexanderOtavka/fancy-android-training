package edu.appdev.android.fancy;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        final EditText nameField = (EditText) findViewById(R.id.sign_up_name_input);
        Button signUp = (Button) findViewById(R.id.sign_up_confirm_button);

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(MainActivity.INTENT_IS_SIGNED_IN, true);

                intent.putExtra(MainActivity.INTENT_USER_NAME, nameField.getText().toString());
                startActivity(intent);
            }
        });
    }
}
