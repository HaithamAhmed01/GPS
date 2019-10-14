package com.example.gps;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gps.Base.BaseActivity;
import com.example.gps.Model.Note;
import com.example.gps.dataBase.MyDataBase;

public class AddNoteActivity extends BaseActivity implements View.OnClickListener {

    protected EditText title;
    protected EditText content;
    protected EditText time;
    protected Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_note);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add) {
            String titleS = title.getText().toString();
            String contentS = content.getText().toString();
            String timeS = time.getText().toString();


            Note note = new Note(titleS, contentS, timeS);
            MyDataBase.getInstance(this).notesDao()
                    .addNote(note);
            showMessage(R.string.note_add_succussfully, R.string.ok
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    }, false);
        }
    }

    private void initView() {
        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        time = (EditText) findViewById(R.id.time);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(AddNoteActivity.this);
    }
}
