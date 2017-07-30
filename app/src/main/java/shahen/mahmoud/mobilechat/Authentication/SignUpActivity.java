package shahen.mahmoud.mobilechat.Authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import shahen.mahmoud.mobilechat.AuthenticationSystem.SignUp;
import shahen.mahmoud.mobilechat.R;
import shahen.mahmoud.mobilechat.Utilits;

/**
 * Created by harty on 7/29/2017.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    @BindView(R.id.et_sign_up_email)
    EditText emailEditText;
    @BindView(R.id.et_sign_up_password)
    EditText passwordEditText;
    @BindView(R.id.et_confirm_password)
    EditText confirmPasswordEditText;
    @BindView(R.id.b_sign_up)
    Button signUpButton;
    @BindView(R.id.pb_sign_up)
    ProgressBar signUpPrgressBar;
    FirebaseAuth firebaseAuth;
    SignUp signUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        firebaseAuth = FirebaseAuth.getInstance();
        signUp = SignUp.getInstance();
        signUpButton.setOnClickListener(this);
        emailEditText.setOnFocusChangeListener(this);
        passwordEditText.setOnFocusChangeListener(this);
        confirmPasswordEditText.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == signUpButton.getId()) {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            if (!email.isEmpty() && !password.isEmpty()) {
                if (password.equals(confirmPassword)) {
                    signUp.setEmail(email);
                    signUp.setPassword(password);
                    viewProgressBar();
                    Utilits.hideSoftKeyboard(view);
                    signUp.signUp(firebaseAuth, this);
                } else {
                    Toast.makeText(this, getString(R.string.passwordsnotmatch), Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_LONG).show();
            }
        }
    }
    public void viewProgressBar() {
        signUpPrgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        signUpPrgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (!b)
            Utilits.hideSoftKeyboard(view);
    }
}
