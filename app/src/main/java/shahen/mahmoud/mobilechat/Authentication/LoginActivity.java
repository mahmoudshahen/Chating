package shahen.mahmoud.mobilechat.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import shahen.mahmoud.mobilechat.AuthenticationSystem.Login;
import shahen.mahmoud.mobilechat.Chat.ChatActivity;
import shahen.mahmoud.mobilechat.R;
import shahen.mahmoud.mobilechat.Utilits;

/**
 * Created by harty on 7/29/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    @BindView(R.id.et_email)
    EditText emailEditText;
    @BindView(R.id.et_password)
    EditText passwordEditText;
    @BindView(R.id.b_login)
    Button loginButton;
    @BindView(R.id.b_forgot_password)
    Button forgotPasswordButton;
    @BindView(R.id.b_register)
    Button registerButton;
    @BindView(R.id.pb_login)
    ProgressBar loginProgressBar;

    FirebaseAuth firebaseAuth;
    Login login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        login = Login.getInstance();
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        forgotPasswordButton.setOnClickListener(this);
        emailEditText.setOnFocusChangeListener(this);
        passwordEditText.setOnFocusChangeListener(this);
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(this, ChatActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == loginButton.getId()) {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (!email.isEmpty() && !password.isEmpty()) {
                login.setEmail(email);
                login.setPassword(password);
                Log.v("click", "clicked");
                viewProgressBar();
                Utilits.hideSoftKeyboard(view);
                login.login(firebaseAuth, LoginActivity.this);
            }
        } else if (view.getId() == registerButton.getId()) {
            startActivity(new Intent(this, SignUpActivity.class));
        } else if (view.getId() == forgotPasswordButton.getId()) {
            startActivity(new Intent(this, ForgotPasswordActivity.class));
        }
    }

    public void viewProgressBar() {
        loginProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        loginProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (!b) {
            Utilits.hideSoftKeyboard(view);
        }
    }
}
