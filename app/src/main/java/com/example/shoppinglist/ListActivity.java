package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class ListActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private ListView mShoppingList;
    private EditText mAddItem;
    private Button mAddButton;

    ArrayList<String> lists = new ArrayList<>();

    ArrayAdapter<String> mAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        BottomNavigationView mNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        if (mNavigationView != null) {
            mNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);}
        mShoppingList = (ListView) findViewById(R.id.List_listView);
        mAddItem = (EditText) findViewById(R.id.List_itemInput);
        mAddButton = (Button) findViewById(R.id.List_enterButton);

        String list=readFile();
        ArrayList<String> lists = new ArrayList<String>(Arrays.asList(list.split("\n")));
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lists);
        mShoppingList.setAdapter(mAdapter);
        mAddButton.setOnClickListener(this::onClick);

    }



    public void onClick(View v) {
        String item = mAddItem.getText().toString();
        mAdapter.add(item);
        mAdapter.notifyDataSetChanged();
        mAddItem.setText("");
        System.out.println("clicked");
        writeFile(item);
        System.out.println("after write"+readFile());}
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        if (item.getItemId() == R.id.Cupboard ){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.Spoilt) {
            Intent intent2 = new Intent(this,SpoiltActivity.class);
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



    public void writeFile(String item) {
        System.out.println("item to write " +item);
        item=item + "\n";
        try {
            File path=getApplicationContext().getFilesDir();
            FileOutputStream FOS = openFileOutput("shopping.txt",MODE_APPEND);
            FOS.write(item.getBytes(StandardCharsets.UTF_8));
            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
            FOS.close();
            System.out.println(readFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String readFile() {
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, "shopping.txt");
        byte[] content = new byte[(int) readFrom.length()];
        ArrayList<String> lists = new ArrayList<>();
        try {
            FileInputStream FIS = new FileInputStream(readFrom);
            FIS.read(content);
            return new String(content);
        } catch (RuntimeException e) {
            writeFile("Add your own!");
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

