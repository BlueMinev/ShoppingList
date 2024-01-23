package com.example.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.system.ErrnoException;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private ListView mCupboardList;
    private EditText mAddItem;
    private Button mAddButton;

    ArrayList<String> lists = new ArrayList<>();

    ArrayAdapter<String> mAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        File path = getApplicationContext().getFilesDir();
        File check = new File(path, "cupboards.txt");
        if(!check.exists()){
            setUpFiles();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView mNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        if (mNavigationView != null) {
            mNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);}
        String list=readFile();
        mCupboardList = (ListView) findViewById(R.id.cupboard_listView);
        mAddItem = (EditText) findViewById(R.id.cupboard_itemInput);
        mAddButton = (Button) findViewById(R.id.cupboard_enterButton);
        ArrayList<String> lists = new ArrayList<String>(Arrays.asList(list.split("\n")));
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lists);
        mCupboardList.setAdapter(mAdapter);
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
            FileOutputStream FOS = openFileOutput("cupboards.txt",MODE_APPEND);
            FOS.write(item.getBytes(StandardCharsets.UTF_8));
            Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
            FOS.close();
            System.out.println(readFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public void setUpFiles(){
            String item = "Add your own !\n";
            try {
                File path=getApplicationContext().getFilesDir();
                FileOutputStream FOS = openFileOutput("cupboards.txt",MODE_APPEND);
                FOS.write(item.getBytes(StandardCharsets.UTF_8));
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                FOS.close();
                path=getApplicationContext().getFilesDir();
                 FOS = openFileOutput("spoilt.txt",MODE_APPEND);
                FOS.write(item.getBytes(StandardCharsets.UTF_8));
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                FOS.close();
                 path=getApplicationContext().getFilesDir();
                 FOS = openFileOutput("shopping.txt",MODE_APPEND);
                FOS.write(item.getBytes(StandardCharsets.UTF_8));
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                FOS.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    public String readFile() {
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, "cupboards.txt");
        byte[] content = new byte[(int) readFrom.length()];
        ArrayList<String> lists = new ArrayList<>();
        try {
            FileInputStream FIS = new FileInputStream(readFrom);
            FIS.read(content);
            return new String(content);
        } catch (RuntimeException e) {
            setUpFiles();
            throw new RuntimeException(e);

    }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

