package com.example.navetargilsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import static com.example.navetargilsql.GradesC.TABLE_GRADES;
import static com.example.navetargilsql.Users.KEY_ID;
import static com.example.navetargilsql.Users.TABLE_USERS;

public class SeeInfo extends AppCompatActivity implements AdapterView.OnItemClickListener{


        SQLiteDatabase db;
        HelperDB hlp;
        Cursor crsr;
        ListView lvT, lvR;
        ArrayAdapter adp;
        ArrayList<String> arr = new ArrayList<>();
        int tableC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_info);


        lvT = (ListView) findViewById(R.id.Lv1);
            lvR = (ListView) findViewById(R.id.Lv2);

            hlp = new HelperDB(this);
            db = hlp.getWritableDatabase();
            db.close();

            tableC = 0;
            /**
             sets the listView
             */

            lvT.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            lvT.setOnItemClickListener(this);
            lvR.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            lvR.setOnItemClickListener(this);

            String[] t = {TABLE_USERS,TABLE_GRADES};
            adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, t);
            lvT.setAdapter(adp);

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            db = hlp.getReadableDatabase();
            if (parent == lvT) {
                arr = new ArrayList<>();
                tableC = position + 1;

                /**
                 * adds the data into the list
                 */
                if (tableC == 1) {
                    crsr = db.query(TABLE_USERS, null, null, null, null, null, null);
                    int co1 = crsr.getColumnIndex(Users.KEY_ID);
                    int co2 = crsr.getColumnIndex(Users.NAME);
                    int co3 = crsr.getColumnIndex(Users.ADDRESS);
                    int co4 = crsr.getColumnIndex(Users.PHONE);
                    int co5 = crsr.getColumnIndex(Users.HOME_P);
                    int co6 = crsr.getColumnIndex(Users.MOM_NAME);
                    int co7 = crsr.getColumnIndex(Users.MOM_NUM);
                    int co8 = crsr.getColumnIndex(Users.DAD_NAME);
                    int co9 = crsr.getColumnIndex(Users.DAD_NUM);

                    crsr.moveToFirst();
                    while (!crsr.isAfterLast()) {
                        int key = crsr.getInt(co1);
                        String name = crsr.getString(co2);
                        String add = crsr.getString(co3);
                        int pho = crsr.getInt(co4);
                        int Hpho = crsr.getInt(co5);
                        String Mname = crsr.getString(co6);
                        int Mnum = crsr.getInt(co7);
                        String Dname = crsr.getString(co8);
                        int Dnum = crsr.getInt(co9);
                        String stUsers = "" + key + ", " + name + ", " + add + "," + pho + ", " + Hpho + ", " + Mname + ", " + Mnum + ", " + Dname + ", " + Dnum;
                        arr.add(stUsers);
                        crsr.moveToNext();
                    }
                } else {
                    crsr = db.query(TABLE_GRADES, null, null, null, null, null, null);
                    int co1 = crsr.getColumnIndex(GradesC.KEY_ID);
                    int co2 = crsr.getColumnIndex(GradesC.NAMES);
                    int co3 = crsr.getColumnIndex(GradesC.QUARTER);
                    int co4 = crsr.getColumnIndex(GradesC.GRADE);

                    crsr.moveToFirst();
                    while (!crsr.isAfterLast()) {
                        int key = crsr.getInt(co1);
                        String names = crsr.getString(co2);
                        int qua = crsr.getInt(co3);
                        int grade = crsr.getInt(co4);
                        String stGrades = "" + key + "," + names + "," + qua + "," + grade;
                        arr.add(stGrades);
                        crsr.moveToNext();
                    }

                }
                crsr.close();
                db.close();
                adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arr);
                lvR.setAdapter(adp);
            }
            /**
             * when listview row is clicked it deletes the row
             */

            else {
                if (tableC == 1) {
                    db.delete(TABLE_USERS, KEY_ID + "=?", new String[]{Integer.toString(position + 1)});
                }
                else{
                    db.delete(TABLE_GRADES, KEY_ID + "=?", new String[]{Integer.toString(position + 1)});
                }
                db.close();
                arr.remove(position);
                adp.notifyDataSetChanged();
            }


        }






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
        if ((st.equals("credits"))) {
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        if ((st.equals("SeeSort"))) {
            Intent si = new Intent(this, SeeSort.class);
            startActivity(si);
        }
        return true;
    }}

