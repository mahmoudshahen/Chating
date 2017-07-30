package shahen.mahmoud.mobilechat.Authentication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import shahen.mahmoud.mobilechat.AuthenticationSystem.ResetPassword;
import shahen.mahmoud.mobilechat.R;
import shahen.mahmoud.mobilechat.Utilits;

/**
 * Created by harty on 7/29/2017.
 */

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    @BindView(R.id.et_forgot_email)
    EditText emailEditText;
    @BindView(R.id.b_reset_password)
    Button resetButton;
    @BindView(R.id.b_back)
    Button backButton;
    @BindView(R.id.pb_forgot_password)
    ProgressBar forgotPasswordProgressBar;
    FirebaseAuth firebaseAuth;
    ResetPassword resetPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot__password);
        ButterKnife.bind(this);
        resetButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        resetPassword = ResetPassword.getInstance();
       emailEditText.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == resetButton.getId()) {
            if (!emailEditText.getText().toString().isEmpty()) {
                resetPassword.setEmail(emailEditText.getText().toString());
                viewProgressBar();
                Utilits.hideSoftKeyboard(view);
                resetPassword.resetPassword(firebaseAuth, this);
            }
            else {
                Toast.makeText(this, getString(R.string.fill_fields), Toast.LENGTH_LONG).show();
            }
        }
        else if (view.getId() == backButton.getId()) {
            finish();
        }
    }
    public void viewProgressBar() {
        forgotPasswordProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        forgotPasswordProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == emailEditText.getId()) {
            if (!b)
                Utilits.hideSoftKeyboard(view);
        }
    }
}
