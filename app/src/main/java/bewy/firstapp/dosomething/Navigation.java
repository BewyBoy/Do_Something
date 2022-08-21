package bewy.firstapp.dosomething;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class Navigation extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent;
        SharedPreferences sp  = getSharedPreferences("GenPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        checkDate(sp, editor);
        boolean isNotLocked = sp.getBoolean("First_time_today", true);
        if (isNotLocked){
            intent = new Intent(Navigation.this, MainActivity.class);
        } else {
            intent = new Intent(Navigation.this, Focus_mode.class);
        }

        startActivity(intent);
        finish();

    }


    private void checkDate(SharedPreferences sp, SharedPreferences.Editor editor){
        Calendar calendar = Calendar.getInstance();
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);
        System.out.println(dayOfYear + " " + year);
        int lastDateCollected = sp.getInt("lastDate", -1);
        int lastYearCollected = sp.getInt("lastYear", -1);
        if (year > lastYearCollected){
            editor.putInt("lastYear", year);
            editor.putBoolean("First_time_today", true);
            editor.apply();
        } else {
            if (dayOfYear > lastDateCollected){
                editor.putInt("lastDate", dayOfYear);
                editor.putBoolean("First_time_today", true);
                editor.apply();
            }
        }



    }
}

