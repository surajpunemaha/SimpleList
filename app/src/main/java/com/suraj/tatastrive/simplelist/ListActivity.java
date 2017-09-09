package com.suraj.tatastrive.simplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity
{
    EditText edit_name;
    ListView listv_names;

    ArrayList<String> al_names;
    ArrayAdapter<String> adapter;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        edit_name = (EditText)findViewById(R.id.edit_name);
        listv_names = (ListView) findViewById(R.id.listv_names);

        al_names= new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ListActivity.this, android.R.layout.simple_list_item_1,al_names);
        listv_names.setAdapter(adapter);

        registerForContextMenu(listv_names);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.add :
                if(!edit_name.getText().toString().trim().equals(""))
                {
                    al_names.add(edit_name.getText().toString());
                    edit_name.setText("");
                    adapter.notifyDataSetChanged();
                    Toast.makeText(ListActivity.this, "Added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ListActivity.this, "Please Language Name", Toast.LENGTH_SHORT).show();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);

        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        pos = acmi.position;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.remove :
                al_names.remove(pos);
                adapter.notifyDataSetChanged();
                Toast.makeText(ListActivity.this, "Removed", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
