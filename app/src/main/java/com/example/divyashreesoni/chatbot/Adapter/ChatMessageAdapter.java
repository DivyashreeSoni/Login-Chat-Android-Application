package com.example.divyashreesoni.chatbot.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.divyashreesoni.chatbot.Model.ChatMessage;
import com.example.divyashreesoni.chatbot.R;

import java.util.List;


public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.CustomViewHolder>{

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView TextView;
        public CustomViewHolder(View itemView){
            super(itemView);
            TextView = itemView.findViewById(R.id.TextMessage);
        }
    }

    List<ChatMessage> ChatMessageList;

    public ChatMessageAdapter(List<ChatMessage> ChatMessageList){
        this.ChatMessageList = ChatMessageList;
    }

    @Override
    public int getItemViewType(int position) {
        if(ChatMessageList.get(position).isMe()){
            return R.layout.user_query;
        }
        return R.layout.bot_response;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageAdapter.CustomViewHolder holder, int position) {
        holder.TextView.setText(ChatMessageList.get(position).getTextMessage());
    }

    @Override
    public int getItemCount() {
        return ChatMessageList.size();
    }
}
