package com.example.lelu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.lelu.R.layout.activity_workout;
import static com.example.lelu.R.layout.workout_layer;
import static com.example.lelu.R.layout.workout_menu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class MyWorkoutActivity<imageButton> extends ConstraintLayout {

/*-------------------------------------------------declaration global variables-------------------------------------------------*/
    private ViewStub viewStub_workoutMenu;
    private ConstraintSet constraintSet;
    //workout ListView, adding workouts
        ArrayAdapter<String> adapter;
        ArrayList<String> arrayList;
        EditText customWorkout;
        int numberOfItems = 0;
    //chronometer
        private TextView chronometer;
        Double time = 0.0;
        private boolean running = false;
        Timer timer = new Timer();
        TimerTask timerTask;
    //workout sector


/*--------------------------------------------------setting new layout via class--------------------------------------------------*/
    @RequiresApi(api = Build.VERSION_CODES.Q)//some methods doesn't work on lower APIs
    public MyWorkoutActivity(@NonNull Context context) {
        super(context);

        //creating instance of ConstraintSet class for constraining views in ConstraintLayout
        constraintSet = new ConstraintSet();
        //cloning layout, work in clone then apply to real
        constraintSet.clone(this);
        //code that will be applied upon applying from clone to original
        hardCodedWorkoutSegment(context, constraintSet);
        //applying clone to original
        constraintSet.applyTo(this);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.Q)

/*--------------------------------------------------new layout--------------------------------------------------*/
    void hardCodedWorkoutSegment(Context context, ConstraintSet constraintSet){
        //setup constrain layout-a
        //'this' is ViewGroup root, aka parent layout, that is main layout where every layout is
        this.setBackground(getResources().getDrawable(R.drawable.gradient));
        this.setForceDarkAllowed(false);

        // setup ViewStub-a, this is place where different layouts will be called
        //creating view
        viewStub_workoutMenu = new ViewStub(context);
        //generating id for view (ViewStub), can access with .getId()
        viewStub_workoutMenu.setId(View.generateViewId());
        //setting which layout will be displayed
        viewStub_workoutMenu.setLayoutResource(workout_menu);
        /* setting width and height
        *  MATCH_CONSTRAINT -> android:layout_height="match_parent", android:layout_width="match_parent" (in this case)
        *  WRAP_CONTENT -> android:layout_height="wrap_content", android:layout_width="wrap_content"
        */
        constraintSet.constrainWidth(viewStub_workoutMenu.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(viewStub_workoutMenu.getId(),ConstraintSet.MATCH_CONSTRAINT);
        /*constraining to every parent (here parent is 'this')
        * app:layout_constraintStart_toStartOf="parent"
        * app:layout_constraintTop_toTopOf="parent"
        * app:layout_constraintBottom_toBottomOf="parent"
        * app:layout_constraintEnd_toEndOf="parent"
        */
        constraintSet.connect(viewStub_workoutMenu.getId(),ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(viewStub_workoutMenu.getId(),ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(viewStub_workoutMenu.getId(),ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.connect(viewStub_workoutMenu.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        addView(viewStub_workoutMenu);

    }

/*----------------------------------------------------------------------methods----------------------------------------------------------------------*/

    public void StartWorkout(Context context){
/*----------------------------------------checking if any ChekBox is checked----------------------------------------*/
        CheckBox checkBoxPullUp = (CheckBox)findViewById(R.id.cbPullUp);
        CheckBox checkBoxPushUp = (CheckBox)findViewById(R.id.cbPushUp);
        CheckBox checkBoxDip = (CheckBox)findViewById(R.id.cbDip);
        if(checkBoxPushUp.isChecked())numberOfItems++;
        if(checkBoxDip.isChecked())numberOfItems++;
        if(checkBoxPullUp.isChecked())numberOfItems++;


/*--------------------------------------------------changing layouts--------------------------------------------------*/
        //removing everything from layout
        this.removeAllViews();
        //adding new layout
        this.addView(LayoutInflater.from(context).inflate(workout_layer,this,false));
        //View.inflate(getContext(), workout_layer, null);
        chronometer = (TextView) findViewById(R.id.cChronometer);




/*----------------------------------------loading number of workout sectors----------------------------------------*///fix this part
        LinearLayout linearLayoutWorkoutSubLayer = (LinearLayout)findViewById(R.id.WorkoutSubLayer);
        if(linearLayoutWorkoutSubLayer == null){
            Toast.makeText(context, "Layout postoji idiote jedan",
                    Toast.LENGTH_LONG).show();
        }
        else {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            linearLayoutWorkoutSubLayer.addView(layoutInflater.inflate(R.layout.workout_sector,linearLayoutWorkoutSubLayer,true),1);
        }

    }



    public void AddWorkout(Context context){
        View.inflate(getContext(), workout_menu,null);
        if(!(customWorkout.getText().toString().equals(""))) {
            arrayList.add(customWorkout.getText().toString());
            adapter.notifyDataSetChanged();
        }
        customWorkout.setText("");
        numberOfItems++;
    }


/*----------------------------------------timer methods----------------------------------------*/
    public void startChronometer(Context context){
        if(!running){
            startTimer(context);
            running = true;
        }
    }
    private void startTimer(Context context) {
        timerTask = new TimerTask() {
            @Override
            public void run()
            {
                ((Activity)context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        time++;
                        chronometer.setText(getTimerText());
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask,0,1000);
    }
    private String getTimerText(){
        int rounded = (int) Math.round(time);
        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);
        return formatTime(seconds,minutes,hours);
    }
    private String formatTime(int seconds, int minutes, int hours) {
        @SuppressLint("DefaultLocale") String s = String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
        return s;
    }
    public void pauseChronometer(Context context){
        if(running){
            timerTask.cancel();
            running = false;
        }
    }
    public void resetChronometer(Context context){
        if(timerTask != null){
            timerTask.cancel();
            running = false;
            time= 0.0;
            chronometer.setText(formatTime(0,0,0));
        }
    }


    public void OnClickSaveData(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Information");
        builder.setMessage("Unfortunately this feature doesn't work\nWe are hoping that it will be fixed soon");
        AlertDialog.Builder builder1 = builder.setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void OnClickResetAll(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Confirmation dialog");
        builder.setMessage("Data isn't saved.\nDo you want do delete your workout?");
        AlertDialog.Builder builder1 = builder.setPositiveButton(R.string.Confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                    pushUps.Reset();
//                    pullUps.Reset();
//                    pushUpCount.setText(""+pushUps.GetCounter());
//                    pullUpCount.setText(""+pullUps.GetCounter());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
