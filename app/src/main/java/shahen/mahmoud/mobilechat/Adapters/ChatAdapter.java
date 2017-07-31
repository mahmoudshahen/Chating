package shahen.mahmoud.mobilechat.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import shahen.mahmoud.mobilechat.Animatation.Animate;
import shahen.mahmoud.mobilechat.Chat.Message;
import shahen.mahmoud.mobilechat.R;

/**
 * Created by harty on 7/30/2017.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    List<Message> messages;
    FirebaseUser user;

    public ChatAdapter(List<Message> messages, FirebaseUser user) {
        this.messages = messages;
        this.user = user;
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getOwner().equals(user.getEmail()))
            return 0;
        else
            return 1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_message, parent, false);
        else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1)
            holder.owner.setText(messages.get(position).getOwner());
        holder.message.setText(messages.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView owner;

        public ViewHolder(View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.tv_message);
            owner = itemView.findViewById(R.id.tv_email);
        }
    }
}
