package com.example.webprog26.task_1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //In this case it could be private
    public static final String IS_BUTTON_HIDDEN_ALREADY = "com.example.webprog26.task_1.is_button_hidden_already";

    private static final boolean BUTTON_IS_VISIBLE = false;
    private static final boolean BUTTON_IS_HIDDEN = true;

    private Button mBtnHideMe;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mBtnHideMe = (Button) findViewById(R.id.btnHideMe);
        mBtnHideMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getVisibility() == View.VISIBLE)
                {
                    v.setVisibility(View.GONE);
                    mEditor.putBoolean(IS_BUTTON_HIDDEN_ALREADY, BUTTON_IS_HIDDEN).apply();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.actionShowButton:
                if(isButtonHidden())
                {
                    mBtnHideMe.setVisibility(View.VISIBLE);
                    mEditor.putBoolean(IS_BUTTON_HIDDEN_ALREADY, BUTTON_IS_VISIBLE).apply();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
            menu.findItem(R.id.actionShowButton).setEnabled(isButtonHidden());
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Checks is view hidden already
     * @return true is the view already hidden, otherwise return false
     */
    private boolean isButtonHidden()
    {
        return mSharedPreferences.getBoolean(IS_BUTTON_HIDDEN_ALREADY, BUTTON_IS_VISIBLE);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(isButtonHidden())
        {
            mBtnHideMe.setVisibility(View.GONE);
        }
    }
}
