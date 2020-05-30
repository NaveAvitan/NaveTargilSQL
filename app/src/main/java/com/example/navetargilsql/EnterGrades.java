package com.example.navetargilsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EnterGrades extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;
    EditText ETname, ETreva, ETgrades;

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
        if ((st.equals("credits"))) {
            Intent si = new Intent(this, credits.class);
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
        setContentView(R.layout.activity_enter_grades);

        ETname = (EditText) findViewById(R.id.ETname);
        ETreva = (EditText) findViewById(R.id.ETreva);
        ETgrades = (EditText) findViewById(R.id.ETgrades);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }

    /**
     * put in the edittext input
     * put in  name and the value
     * @param view
     */
    public void entergrades(View view) {
        int thegrade, thereva;
        String name1, reva1, grade1;
        name1 = ETname.getText().toString();
        reva1 = ETreva.getText().toString();
        thereva = Integer.parseInt(reva1);
        grade1 = ETgrades.getText().toString();
        thegrade = Integer.parseInt(grade1);

        ContentValues cv = new ContentValues();
        cv.put(GradesC.NAMES,name1);
        cv.put(GradesC.QUARTER,thereva );
        cv.put(GradesC.GRADE, thegrade);

        db = hlp.getWritableDatabase();
        db.insert(GradesC.TABLE_GRADES, null, cv);
        db.close();
    }
}
