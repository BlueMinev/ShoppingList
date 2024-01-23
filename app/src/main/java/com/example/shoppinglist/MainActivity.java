package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity  {

    private ListView mCupboardList;
    private EditText mAddItem;
    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCupboardList = (ListView) findViewById(R.id.cupboard_listView);
        mAddItem = (EditText) findViewById(R.id.cupboard_itemInput);
        mAddButton = (Button) findViewById(R.id.cupboard_enterButton);
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> lists = new ArrayList<String>();
       for (int i = 0; i < values.length; ++i) {
            lists.add(values[i]);
        }

         ArrayAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lists);
        mCupboardList.setAdapter(mAdapter);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String item = mAddItem.getText().toString();
                mAdapter.add(item);
                mAdapter.notifyDataSetChanged();
                mAddItem.setText("");
            }
        });
    }
    @Override
    protected void onDestroy(){
        File path = getApplicationContext().getFilesDir();
        try {
            FileOutputStream writer = new FileOutputStream( new File(path,"cupboardlist.txt"));
    //        writer.write(lists.toString().getBytes());
      //      writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.onDestroy();
    }
}

