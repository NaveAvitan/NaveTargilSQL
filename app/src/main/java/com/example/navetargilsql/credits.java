package com.example.navetargilsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class credits extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * switch activity
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("EnterData"))) {
            Intent si = new Intent(this, MainActivity.class);
            startActivity(si);
        }
        if ((st.equals("EnterGrades"))) {
            Intent si = new Intent(this, EnterGrades.class);
            startActivity(si);
        }
        if ((st.equals("SeeInfo"))) {
            Intent si = new Intent(this, SeeInfo.class);
            startActivity(si);
        }
        if ((st.equals("SeeSort"))) {
            Intent si = new Intent(this, SeeSort.class);
            startActivity(si);
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }
}
