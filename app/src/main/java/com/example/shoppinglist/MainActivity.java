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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mCupboardList;
    private EditText mAddItem;
    private Button mAddButton;
    private ArrayAdapter<String> mAdapter;
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

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
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

}

