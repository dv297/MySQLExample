package edu.sc.cse.rdc.mysqlexample;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ListFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ListFragment extends Fragment {

        Button submitButton;
        EditText editText;
        ListView itemListView;
        ArrayAdapter<Item> adapter;

        ArrayList<Item> list;

        ItemsDataSource datasource;

        public ListFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            submitButton = (Button) rootView.findViewById(R.id.submitButton);
            editText = (EditText) rootView.findViewById(R.id.editText);
            itemListView = (ListView) rootView.findViewById(R.id.listView);
            list = new ArrayList<Item>();

            datasource = new ItemsDataSource(getActivity());
            datasource.open();

            list = (ArrayList<Item>) datasource.getAllItems();

            adapter = new ArrayAdapter<Item>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, list);
            itemListView.setAdapter(adapter);

            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem(editText.getText().toString());
                    editText.setText("");
                }
            });

            itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    deleteItem(position);
                }
            });

            return rootView;
        }

        private void addItem(String s)
        {
            list.add(new Item(s));
            datasource.createItem(s);
            adapter.notifyDataSetChanged();
        }

        private void deleteItem(int index)
        {
            datasource.deleteItem(list.get(index));
            list.remove(index);
            adapter.notifyDataSetChanged();
        }
    }
}
