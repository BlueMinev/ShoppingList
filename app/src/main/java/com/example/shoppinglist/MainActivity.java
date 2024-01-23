package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

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

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView navbar;
    private ListView mCupboardList;
    private EditText mAddItem;
    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView mNavigationView = (BottomNavigationView) findViewById(R.id.navbar);

        if (mNavigationView != null) {
            mNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
        }
        mCupboardList = (ListView) findViewById(R.id.cupboard_listView);
        mAddItem = (EditText) findViewById(R.id.cupboard_itemInput);
        mAddButton = (Button) findViewById(R.id.cupboard_enterButton);
        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile"};

        final ArrayList<String> lists = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            lists.add(values[i]);
        }

        ArrayAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lists);
        mCupboardList.setAdapter(mAdapter);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String item = mAddItem.getText().toString();
                mAdapter.add(item);
                mAdapter.notifyDataSetChanged();
                mAddItem.setText("");
            }
        });}
@Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
    if (item.getItemId() == R.id.Cupboard ){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true;
    } else if (item.getItemId() == R.id.Spoilt) {
        Intent intent2 = new Intent(this,SpoiltList.class);
        startActivity(intent2);
        System.out.println("pressed");
        return true;
    } else if (item.getItemId() == R.id.ShoppingList) {
        Intent intent3 = new Intent(this, ListActivity.class);
        startActivity(intent3);
        return true;
    }
    return false;
        }






        @Override
        protected void onDestroy() {
            File path = getApplicationContext().getFilesDir();
            try {
                FileOutputStream writer = new FileOutputStream(new File(path, "cupboardlist.txt"));
                //        writer.write(lists.toString().getBytes());
                //      writer.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            super.onDestroy();
        }


    };

