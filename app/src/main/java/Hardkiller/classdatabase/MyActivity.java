package Hardkiller.classdatabase;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


public class MyActivity extends Activity implements View.OnClickListener{

    EditText nameEditText;
    EditText markEditText;
    ImageButton save;
    Button search;
    TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        nameEditText = (EditText) findViewById(R.id.queryEditText);
        markEditText = (EditText) findViewById(R.id.tagEditText);
        save = (ImageButton) findViewById(R.id.saveButton);
        search = (Button) findViewById(R.id.button);
        results = (TextView) findViewById(R.id.textView);

        save.setOnClickListener(this);
        search.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if ((ImageButton) v == save) {
            if (nameEditText.getText().toString().trim().length() != 0) {
                // AsyncTask to save contact, then notify listener
                AsyncTask<Object, Object, Object> saveContactTask =
                        new AsyncTask<Object, Object, Object>() {
                            @Override
                            protected Object doInBackground(Object... params) {
                                saveStudentMark(); // save contact to the database
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Object result) {
                                // hide soft keyboard
                                hideKeyboard();
                            }
                        }; // end AsyncTask
                saveStudentMarkTask.execute(nameEditText.getText().toString(), markEditText.getText().toString());
                Log.v("Database Log", "Saved" + nameEditText.getText().toString() + markEditText.getText().toString());
            }

            else
            {
                //Do Search
            }
        }
    }

    private void hideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager)
                this.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

        //listener.onAddEditCompleted(rowID);
    }

    private void saveStudentMark()
    {
        DatabaseConnector databaseConnector = new DatabaseConnector(this);
    }
}
