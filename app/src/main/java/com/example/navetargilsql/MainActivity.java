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

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    HelperDB hlp;
    EditText ETNAME, ETADD, ETNUM, ETHNUM, ETMNAME, ETMNUM, ETDNAME, ETDNUM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ETNAME = (EditText) findViewById(R.id.ETNAME);
        ETADD = (EditText) findViewById(R.id.ETADD);
        ETNUM = (EditText) findViewById(R.id.ETNUM);
        ETHNUM = (EditText) findViewById(R.id.ETHnum);
        ETMNAME = (EditText) findViewById(R.id.ETMNAME);
        ETMNUM = (EditText) findViewById(R.id.ETMNUM);
        ETDNAME = (EditText) findViewById(R.id.ETDNAME);
        ETDNUM = (EditText) findViewById(R.id.ETDNUM);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();
    }


    /**
     * put in  the edittext input
     * put in content value the name and the value
     * @param view
     */
    public void enter(View view) {
        String address, name, strNum, strHnum, momName, strMnum, dadName, strDnum;
        int num, Homenum, Momnum, Dadnum;
        name = ETNAME.getText().toString();
        address = ETADD.getText().toString();
        strNum = ETNUM.getText().toString();
        num = Integer.parseInt(ETNUM.getText().toString());
        strHnum = ETHNUM.getText().toString();
        Homenum = Integer.parseInt(ETHNUM.getText().toString());
        momName = ETMNAME.getText().toString();
        strMnum = ETMNUM.getText().toString();
        Momnum = Integer.parseInt(ETMNUM.getText().toString());
        dadName = ETDNAME.getText().toString();
        strDnum = ETDNUM.getText().toString();
        Dadnum = Integer.parseInt(ETDNUM.getText().toString());

        ContentValues cv = new ContentValues();
        cv.put(Users.NAME, name);
        cv.put(Users.ADDRESS, address);
        cv.put(Users.PHONE, num);
        cv.put(Users.HOME_P, Homenum);
        cv.put(Users.MOM_NAME, momName);
        cv.put(Users.MOM_NUM, Momnum);
        cv.put(Users.DAD_NAME, dadName);
        cv.put(Users.DAD_NUM, Dadnum);

        db = hlp.getWritableDatabase();
        db.insert(Users.TABLE_USERS, null, cv);
        db.close();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * here you switch activity
     *
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("credits"))) {
            Intent si = new Intent(this, credits.class);
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
    } }