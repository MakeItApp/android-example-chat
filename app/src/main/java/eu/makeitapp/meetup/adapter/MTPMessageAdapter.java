package eu.makeitapp.meetup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import eu.makeitapp.meetup.R;
import eu.makeitapp.meetup.model.MTPMessage;

/**
 * ${PROJECT}
 * <p/>
 * Created by Federico Oldrini (federico.oldrini@makeitapp.eu) on 03/04/2015.
 */
public class MTPMessageAdapter extends RecyclerView.Adapter<MTPMessageViewHolder> {
    private ArrayList<MTPMessage> messageArrayList;
    private String myUsername;

    public MTPMessageAdapter(ArrayList<MTPMessage> messageArrayList, String myUsername) {
        this.messageArrayList = messageArrayList;
        this.myUsername = myUsername;
    }

    @Override
    public MTPMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MTPMessageViewHolder holder = new MTPMessageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item__message, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MTPMessageViewHolder holder, int position) {
        MTPMessage message = messageArrayList.get(position);

        holder.messageTextView.setText(message.getMessageText());
        holder.senderTextView.setText(message.getMessageCreatorName());

        if (message.getMessageAttachment() == null || message.getMessageAttachment().isEmpty()) {
            holder.attachmentImageView.setVisibility(View.GONE);
            holder.messageTextView.setVisibility(View.VISIBLE);
        } else {
            holder.attachmentImageView.setVisibility(View.VISIBLE);
            holder.messageTextView.setVisibility(View.GONE);
            Picasso.with(holder.itemView.getContext()).load(message.getMessageAttachment()).into(holder.attachmentImageView);
        }

        if (message.getMessageCreatorName() != null && message.getMessageCreatorName().equals(myUsername)) {
            holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.green_200));
        } else {
            holder.cardView.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.grey_1000w));
        }
    }

    @Override
    public int getItemCount() {
        return messageArrayList != null ? messageArrayList.size() : 0;
    }
}
