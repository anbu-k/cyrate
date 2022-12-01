package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cyrate.R;
import com.example.cyrate.adapters.CommentThreadAdapter;
import com.example.cyrate.models.CommentThreadCardModel;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class CommentThreadActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CommentThreadAdapter commentThreadAdapter;
    ArrayList<CommentThreadCardModel> commentListCardModels = new ArrayList<>();

    TextView sendBtn;
    EditText comment_et;
    ImageView back_btn;
    Bundle extras;

    Handler handler;

    WebSocketClient websocket;
    String SERVER_PATH = "wss://ws.postman-echo.com/raw/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_thread);

        extras = getIntent().getExtras();
        sendBtn = findViewById(R.id.commentThread_sendBtn);
        comment_et = findViewById(R.id.commentThread_editText);
        back_btn = findViewById(R.id.commentThread_backBtn);

        handler = new Handler();


        try {
            initiateSocketConnection();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.commentThread_recyclerView);

        setupCommentModels();

        commentThreadAdapter = new CommentThreadAdapter(this, commentListCardModels);
        recyclerView.setAdapter(commentThreadAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CommentThreadActivity.this, IndividualReviewActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (comment_et.getText().toString().isEmpty()) {
                    Toast.makeText(CommentThreadActivity.this, "Write out your comment!", Toast.LENGTH_LONG).show();
                } else {
                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("commenterName", MainActivity.globalUser.getFullName());
                        obj.put("photoUrl", MainActivity.globalUser.getPhotoUrl());
                        obj.put("commentBody", comment_et.getText().toString());
                        obj.put("date", "3 days ago");

                        websocket.send(obj.toString());
                        commentThreadAdapter.addItem(obj);
                        recyclerView.smoothScrollToPosition(commentThreadAdapter.getItemCount() - 1);

                        // Empty the Edit Text
                        comment_et.setText("");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void initiateSocketConnection() throws URISyntaxException {
        Draft[] drafts = {
                new Draft_6455()
        };

        Log.d("Socket:", "Trying socket");
        websocket = new WebSocketClient(new URI(SERVER_PATH), drafts[0]) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.d("OPEN", "run() returned: " + "is connecting");
                runOnUiThread(() -> {
                    Toast.makeText(CommentThreadActivity.this, "Socket Connection Successful", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onMessage(String message) {
                runOnUiThread(() -> {
                    try {
                        JSONObject obj = new JSONObject(message);
                        commentThreadAdapter.addItem(obj);
                        recyclerView.smoothScrollToPosition(commentThreadAdapter.getItemCount() - 1);

//
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                View view = recyclerView.getLayoutManager().findViewByPosition(commentThreadAdapter.getItemCount() - 1);
                                if (view != null) {
                                    view.findViewById(R.id.threadBar).setVisibility(View.INVISIBLE);
                                }
                            }
                        }, 350);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {

            }

            @Override
            public void onError(Exception ex) {

            }
        };

        websocket.connect();
    }

    private void setupCommentModels() {
        for (int i = 0; i < 8; i++) {
            CommentThreadCardModel model = new CommentThreadCardModel(
                    "John Doe",
                    "https://t4.ftcdn.net/jpg/03/64/21/11/360_F_364211147_1qgLVxv1Tcq0Ohz3FawUfrtONzz8nq3e.jpg",
                    commentBody,
                    "3 days ago"
            );
            commentListCardModels.add(model);
        }
    }

    public static final String commentBody = "Hey, this is a great post. Thanks for sharing! Do you have any other recommendations that you could share?";
}




