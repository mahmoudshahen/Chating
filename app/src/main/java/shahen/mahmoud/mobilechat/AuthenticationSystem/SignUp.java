package shahen.mahmoud.mobilechat.AuthenticationSystem;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import shahen.mahmoud.mobilechat.Authentication.SignUpActivity;
import shahen.mahmoud.mobilechat.Chat.ChatActivity;
import shahen.mahmoud.mobilechat.R;

/**
 * Created by harty on 7/29/2017.
 */

public class SignUp extends BasicUser {
    private static SignUp signUp = new SignUp();


    private SignUp() {
    }

    public static SignUp getInstance() {
        return signUp;
    }

    public void signUp(final FirebaseAuth firebaseAuth, final Activity activity) {
        firebaseAuth.createUserWithEmailAndPassword(getEmail(), getPassword()).addOnCompleteListener(activity,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.v("signUp", "success");
                            if (activity instanceof SignUpActivity) {
                                ((SignUpActivity) activity).hideProgressBar();
                                activity.startActivity(new Intent(activity, ChatActivity.class));
                                activity.finish();
                            } else
                                Log.v("instance", "false");
                        } else {
                            ((SignUpActivity) activity).hideProgressBar();
                            Toast.makeText(activity, activity.getString(R.string.failedSignUp), Toast.LENGTH_LONG).show();
                            Log.v("signUp", "failed");
                        }
                    }
                });
    }
}