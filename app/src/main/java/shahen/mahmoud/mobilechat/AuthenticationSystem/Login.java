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

import shahen.mahmoud.mobilechat.Authentication.LoginActivity;
import shahen.mahmoud.mobilechat.Chat.ChatActivity;
import shahen.mahmoud.mobilechat.R;

/**
 * Created by harty on 7/29/2017.
 */

public class Login extends BasicUser {

    private static Login login = new Login();

    private Login() {
    }

    public static Login getInstance() {
        return login;
    }

    public void login(FirebaseAuth firebaseAuth, final Activity activity) {
        firebaseAuth.signInWithEmailAndPassword(getEmail(), getPassword()).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (activity instanceof LoginActivity) {
                        ((LoginActivity) activity).hideProgressBar();
                        activity.startActivity(new Intent(activity, ChatActivity.class));
                        activity.finish();
                    }
                    Log.v("login", "success");
                } else {
                    ((LoginActivity) activity).hideProgressBar();
                    Toast.makeText(activity, activity.getString(R.string.wrongMail), Toast.LENGTH_LONG).show();
                    Log.v("login", "failed");
                }
            }
        });
    }
}
