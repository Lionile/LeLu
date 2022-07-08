package com.example.lelu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import static android.os.Build.ID;

import static com.example.lelu.R.layout.workout_layer;
import static com.example.lelu.R.layout.workout_menu;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;
import java.util.List;

class MyWorkoutActivity extends ConstraintLayout {

/*-------------------------------------------------declaration global variables-------------------------------------------------*/

    ViewStub viewStub_workoutMenu;
    ConstraintSet constraintSet;
    EditText customWorkout;
    Chronometer chronometer;
    String helper;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    private long pauseOffset;
    private boolean running;

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
        this.setBackground(getResources().getDrawable(R.drawable.gradient));
        this.setForceDarkAllowed(false);

        /*  setup ViewStub-a, this is place where different layouts will be called
        *   'this' is ViewGroup root, aka parent layout, that is main layout where every layout is
        */
        //creating view
        viewStub_workoutMenu = new ViewStub(context);
        //generating id for view (ViewStub), can access with .getId()
        viewStub_workoutMenu.setId(View.generateViewId());
        //setting which layout will be displayed
        viewStub_workoutMenu.setLayoutResource(workout_menu);
         /* setting width and height
        *   MATCH_CONSTRAINT -> android:layout_height="match_parent", android:layout_width="match_parent"
        *   WRAP_CONTENT -> android:layout_height="wrap_content", android:layout_width="wrap_content"
         */
        constraintSet.constrainWidth(viewStub_workoutMenu.getId(),ConstraintSet.MATCH_CONSTRAINT);
        constraintSet.constrainHeight(viewStub_workoutMenu.getId(),ConstraintSet.MATCH_CONSTRAINT);
        //constraining to every parent
        constraintSet.connect(viewStub_workoutMenu.getId(),ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        constraintSet.connect(viewStub_workoutMenu.getId(),ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        constraintSet.connect(viewStub_workoutMenu.getId(),ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        constraintSet.connect(viewStub_workoutMenu.getId(),ConstraintSet.END,ConstraintSet.PARENT_ID,ConstraintSet.END);
        addView(viewStub_workoutMenu);

    }

/*------------------------------------------------------------methods------------------------------------------------------------*/

    public void StartWorkout(Context context){
        //removing everything from layout
        this.removeAllViews();
        //adding new layout
        this.addView(LayoutInflater.from(context).inflate(workout_layer,this,false));
        //changing layout from which 'findViewById' takes IDs --> VERY IMPORTANT LINE ( nothing works without this, otherwise 'findViewById' will RETURN null )
        View.inflate(getContext(), workout_layer,null);
        chronometer = (Chronometer)findViewById(R.id.cChronometer);
    }
    public void AddWorkout(Context context){
        View.inflate(getContext(), workout_menu,null);
        if(!(customWorkout.getText().toString().equals(""))) {
            arrayList.add(customWorkout.getText().toString());
            adapter.notifyDataSetChanged();
        }
        customWorkout.setText("");
    }

    public void startChronometer() {
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer(){
        if(running){
            chronometer.stop();
            pauseOffset=SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    public void resetChronometer(){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
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
