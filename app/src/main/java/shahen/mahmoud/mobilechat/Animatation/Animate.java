package shahen.mahmoud.mobilechat.Animatation;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by harty on 7/30/2017.
 */

public class Animate {
    public static void animatePositiveY(RecyclerView.ViewHolder holder)
    {
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView,"translationY",100,0);
        animatorTranslateY.setDuration(1000);
        animatorTranslateY.start();
    }
}
