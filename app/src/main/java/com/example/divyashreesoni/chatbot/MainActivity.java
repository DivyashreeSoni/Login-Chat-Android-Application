package com.example.divyashreesoni.chatbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.divyashreesoni.chatbot.Adapter.ChatMessageAdapter;
import com.example.divyashreesoni.chatbot.Model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText userInput;
    Button b1;
    RecyclerView r;
    List<ChatMessage> ChatMessageList;
    ChatMessageAdapter MessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = findViewById(R.id.userInput);
        r = findViewById(R.id.rv);
        ChatMessageList = new ArrayList<>();
        MessageAdapter = new ChatMessageAdapter(ChatMessageList);
        r.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        r.setAdapter(MessageAdapter);
        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    ChatMessage message = new ChatMessage(userInput.getText().toString(), true);
                    ChatMessageList.add(message);
                    ChatMessage message2 = new ChatMessage(userInput.getText().toString(), false);
                    ChatMessageList.add(message2);
                    MessageAdapter.notifyDataSetChanged();
                    userInput.setText("");
                }
                return true;
            }
        });
    }
}
