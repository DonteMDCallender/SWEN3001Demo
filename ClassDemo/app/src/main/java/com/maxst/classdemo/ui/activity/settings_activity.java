package com.maxst.classdemo.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.maxst.classdemo.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class settings_activity extends AppCompatActivity {


    @Bind(R.id.switch1)
    CompoundButton sSwitch;
    @Bind(R.id.columns) TextView sColumns;
    @Bind(R.id.rows) TextView sRows;
    @Bind(R.id.columnInput) EditText cInput;
    @Bind(R.id.rowInput) EditText rInput;
    @Bind(R.id.grid_button) Button sGridButton;
    @Bind(R.id.gridView)
    GridView mGridView;
    @Bind(R.id.linearLayout2) LinearLayout mSettingsLinearLayout;
    Canvas mCanvas;
    Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        ButterKnife.bind(this, mSettingsLinearLayout);
        getSupportActionBar().hide();

        sGridButton = (Button)findViewById(R.id.grid_button);


        /*sSwitch = (CompoundButton)findViewById(R.id.switch1);
        sSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    openGridDialog();
                }
            }
        });*/


    }
/*
    public void openGridDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.grid_dialog,mSettingsLinearLayout);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);

        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str1;
                String str2;
                str1 = cInput.getText().toString();
                str2 = rInput.getText().toString();
                int numOfCol = Integer.parseInt(str1);
                int numofRow = Integer.parseInt(str2);
                sColumns = (TextView)findViewById(R.id.columns);
                sColumns.setText("Columns: " + str1);
                sRows = (TextView)findViewById(R.id.rows);
                sRows.setText("Rows: " + str2);
                mGridView.setNumColumns(numOfCol);
                openGrid();
                createGrid(mCanvas, numOfCol, numofRow);
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void createGrid(Canvas canvas, int col, int row) {
        int width = mGridView.getWidth();
        int height = mGridView.getHeight();
        float cell_width = width/col;
        float cell_height = height/row;
        for (int i = 0; i < col; i++) {
            for(int j = 0; j < row; j++) {
                canvas.drawRect(i * cell_width, j * cell_height, (i + 1) * cell_width, (j + 1) * cell_height, paint);
            }
        }
    }

    public void openGrid() {
        Intent intent = new Intent(this, Grid_View.class);
        startActivity(intent);
    }*/
}
