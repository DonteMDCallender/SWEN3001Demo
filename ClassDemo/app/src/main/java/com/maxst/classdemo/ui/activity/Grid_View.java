package com.maxst.classdemo.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.maxst.classdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Grid_View extends AppCompatActivity {
    @Bind(R.id.gridView) GridView mGridView;
    @Bind(R.id.columnInput) TextView cInput;
    @Bind(R.id.rowInput) TextView rInput;
    @Bind(R.id.setGrid) Button setGridButton;
    @Bind(R.id.columns) TextView sColumns;
    @Bind(R.id.rows) TextView sRows;
    private boolean[][] cellchecked;
    Paint paint = new Paint();
    Canvas mCanvas;

    public int getNumber(String string) {
        int num = 0;
        num = Integer.parseInt(string);
        return num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        ButterKnife.bind(this);

        mGridView = (GridView)findViewById(R.id.gridView);
        setGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGridDialog();
            }
        });
    }

    public void openGridDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.grid_dialog,null);

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
                TextView row_input = (TextView)findViewById(R.id.rowInput);
                TextView column_input = (TextView)findViewById(R.id.columnInput);
                String str1;
                String str2;
                str1 = column_input.getText().toString();
                str2 = row_input.getText().toString();
                int numOfCol = Integer.parseInt(str1);
                int numofRow = Integer.parseInt(str2);
                sColumns = (TextView)findViewById(R.id.columns);
                sColumns.setText("Columns: " + str1);
                sRows = (TextView)findViewById(R.id.rows);
                sRows.setText("Rows: " + str2);
                mGridView.setNumColumns(numOfCol);
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


}
