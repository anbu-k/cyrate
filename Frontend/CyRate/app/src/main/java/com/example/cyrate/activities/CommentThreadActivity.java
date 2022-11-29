package com.example.cyrate.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cyrate.R;
import com.example.cyrate.adapters.CommentThreadAdapter;
import com.example.cyrate.models.CommentThreadCardModel;
import com.example.cyrate.models.ReviewListCardModel;

import org.w3c.dom.Comment;

import java.util.ArrayList;

public class CommentThreadActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CommentThreadAdapter commentThreadAdapter;
    ArrayList<CommentThreadCardModel> commentListCardModels = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_thread);

        recyclerView = findViewById(R.id.commentThread_recyclerView);

        setupCommentModels();

        commentThreadAdapter = new CommentThreadAdapter(this, commentListCardModels);
        recyclerView.setAdapter(commentThreadAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupCommentModels(){
        for(int i = 0; i < 8; i++){
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




