package shahen.mahmoud.mobilechat.AuthenticationSystem;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

import shahen.mahmoud.mobilechat.Authentication.ForgotPasswordActivity;
import shahen.mahmoud.mobilechat.R;

/**
 * Created by harty on 7/29/2017.
 */

public class ResetPassword extends BasicUser {
    private static ResetPassword resetPassword = new ResetPassword();

    private ResetPassword() {
    }

    public static ResetPassword getInstance() {
        return resetPassword;
    }

    public void resetPassword(FirebaseAuth firebaseAuth, final Activity activity) {
        firebaseAuth.sendPasswordResetEmail(getEmail())
                .addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            ((ForgotPasswordActivity) activity).hideProgressBar();
                            Toast.makeText(activity, activity.getString(R.string.resetSuccess), Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            Toast.makeText(activity, activity.getString(R.string.resetFailed), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }
}
