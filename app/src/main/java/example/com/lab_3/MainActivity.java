package example.com.lab_3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private static final Pattern NAME_PATTERN = Pattern.compile("[A-Z][a-z]+");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=!_-])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private EditText submitPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameEditText = findViewById(R.id.etFirstName);
        lastNameEditText = findViewById(R.id.etLastName);
        emailEditText = findViewById(R.id.etEmail);
        phoneEditText = findViewById(R.id.etPhone);
        passwordEditText = findViewById(R.id.etPassword);
        submitPasswordEditText = findViewById(R.id.etSubmitPassword);

        Button viewListButton = findViewById(R.id.bViewList);
        viewListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openListActivity = new Intent(MainActivity.this, ListActivity.class);
                startActivity(openListActivity);
            }
        });

        Button submitButton = findViewById(R.id.bSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validFirstName = validateFirstName();
                boolean validLastName = validateLastName();
                boolean validEmail = validateEmail();
                boolean validPhone = validatePhone();
                boolean validPassword = validatePassword();
                boolean validSubmitPassword = validateSubmitPassword();
                if (validFirstName && validLastName && validEmail && validPhone && validPassword && validSubmitPassword) {
                    saveInfo();
                    clearInputData();
                }

            }
        });
    }

    private boolean validateFirstName() {
        String firstName = firstNameEditText.getText().toString();
        if (firstName.isEmpty()) {
            firstNameEditText.setError("Please correctly fill out the field");
            return false;
        } else if (!NAME_PATTERN.matcher(firstName).matches()) {
            firstNameEditText.setError("First letter must be capital");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateLastName() {
        String lastName = lastNameEditText.getText().toString();
        if (lastName.isEmpty()) {
            lastNameEditText.setError("Please correctly fill out the field");
            return false;
        } else if (!NAME_PATTERN.matcher(lastName).matches()) {
            lastNameEditText.setError("First letter must be capital");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmail() {
        String email = emailEditText.getText().toString();
        if (email.isEmpty()) {
            emailEditText.setError("Please correctly fill out the field");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email");
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = phoneEditText.getText().toString();
        if (phone.isEmpty()) {
            phoneEditText.setError("Please correctly fill out the field");
            return false;
        } else if (!Patterns.PHONE.matcher(phone).matches()) {
            phoneEditText.setError("Please enter a valid phone");
            return false;
        } else {
            return true;
        }
    }

    private boolean validatePassword() {
        String password = passwordEditText.getText().toString();


        if (password.isEmpty()) {
            passwordEditText.setError("Please correctly fill out the field");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            passwordEditText.setError("Password too weak");
            return false;
        } else {
            return true;
        }

    }

    private boolean validateSubmitPassword() {
        String submitPassword = submitPasswordEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (submitPassword.isEmpty()) {
            submitPasswordEditText.setError("Please correctly fill out the field");
            return false;
        } else if (!password.equals(submitPassword)) {
            submitPasswordEditText.setError("Password doesn't match");
            return false;
        } else {
            return true;
        }
    }

    private void saveInfo() {
        SharedPreferences sharedPref = getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int count = sharedPref.getAll().size() + 1;

        Set<String> user = new HashSet<>();
        user.add("name: " + firstNameEditText.getText().toString());
        user.add("surname: " + lastNameEditText.getText().toString());
        user.add("phone: " + phoneEditText.getText().toString());
        editor.putStringSet("user" + count, user);
        editor.apply();
    }

    private void clearInputData() {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        emailEditText.setText("");
        phoneEditText.setText("");
        passwordEditText.setText("");
        submitPasswordEditText.setText("");
    }

}
