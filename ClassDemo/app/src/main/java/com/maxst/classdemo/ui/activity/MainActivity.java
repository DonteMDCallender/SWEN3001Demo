package com.maxst.classdemo.ui.activity;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.maxst.classdemo.R;
import com.maxst.classdemo.manager.FileManager;
import com.maxst.classdemo.manager.PermissionManager;
import com.maxst.classdemo.ui.component.DrawingView;
import com.maxst.classdemo.ui.dialog.StrokeSelectorDialog;

import org.jetbrains.annotations.NotNull;
import org.xdty.preference.colorpicker.ColorPickerDialog;
import org.xdty.preference.colorpicker.ColorPickerSwatch;

import java.util.Objects;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.maxst.classdemo.R.id.settings;
import static com.maxst.classdemo.R.layout.shapes_selector_dialog;

//import static com.maxst.classdemo.R.id.save_and_share;

//import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_drawing_view) DrawingView mDrawingView;
    @Bind(R.id.main_fill_ib) ImageButton mFillBackgroundImageButton;
    @Bind(R.id.main_colour_ib) ImageButton mColorImageView;
    @Bind(R.id.main_stroke_ib) ImageButton mStrokeImageView;
    @Bind(R.id.select_shape_ib) ImageButton mShapeImageButton;
    @Bind(R.id.main_undo_ib) ImageButton mUndoImageView;
    @Bind(R.id.main_redo_ib) ImageButton mRedoImageButton;
    //@Bind(R.id.settings) ImageButton mSettingsImageButton;
    @Bind(R.id.linearLayout2) LinearLayout mSettingsLinearLayout;

    private int mCurrentBackgroundColor;
    private int mCurrentColor;
    private int mCurrentStroke;
    private static final int MAX_STROKE_WIDTH = 75;
    //private Canvas canvas = new Canvas();
    //private Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initDrawingView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.save_and_share:
                requestPermissionsAndSaveBitmap();
                break;
            case R.id.delete:
                mDrawingView.clearCanvas();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initDrawingView()
    {
        mCurrentBackgroundColor = ContextCompat.getColor(this, android.R.color.white);
        mCurrentColor = ContextCompat.getColor(this, android.R.color.black);
        mCurrentStroke = 10;

        mDrawingView.setBackgroundColor(mCurrentBackgroundColor);
        mDrawingView.setPaintColor(mCurrentColor);
        mDrawingView.setPaintStrokeWidth(mCurrentStroke);
    }

    private void startFillBackgroundDialog()
    {
        int[] colors = getResources().getIntArray(R.array.palette);

        ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
                colors,
                mCurrentBackgroundColor,
                5,
                ColorPickerDialog.SIZE_SMALL);

        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener()
        {

            @Override
            public void onColorSelected(int color)
            {
                mCurrentBackgroundColor = color;
                mDrawingView.setBackgroundColor(mCurrentBackgroundColor);
            }

        });

        dialog.show(getFragmentManager(), "ColorPickerDialog");
    }

    private void startColorPickerDialog()
    {
        int[] colors = getResources().getIntArray(R.array.palette);

        ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
                colors,
                mCurrentColor,
                5,
                ColorPickerDialog.SIZE_SMALL);

        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener()
        {

            @Override
            public void onColorSelected(int color)
            {
                mCurrentColor = color;
                mDrawingView.setPaintColor(mCurrentColor);
            }

        });

        dialog.show(getFragmentManager(), "ColorPickerDialog");
    }

    private void startStrokeSelectorDialog()
    {
        StrokeSelectorDialog dialog = StrokeSelectorDialog.newInstance(mCurrentStroke, MAX_STROKE_WIDTH);

        dialog.setOnStrokeSelectedListener(new StrokeSelectorDialog.OnStrokeSelectedListener()
        {
            @Override
            public void onStrokeSelected(int stroke)
            {
                mCurrentStroke = stroke;
                mDrawingView.setPaintStrokeWidth(mCurrentStroke);
            }
        });

        dialog.show(getSupportFragmentManager(), "StrokeSelectorDialog");
    }

    private void startShareDialog(Uri uri)
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, "Share Image"));
    }

    private void requestPermissionsAndSaveBitmap()
    {
        if (PermissionManager.checkWriteStoragePermissions(this))
        {
            Uri uri = FileManager.saveBitmap(mDrawingView.getBitmap());
            startShareDialog(uri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, Objects.requireNonNull(permissions), grantResults);
        switch (requestCode)
        {
            case PermissionManager.REQUEST_WRITE_STORAGE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Uri uri = FileManager.saveBitmap(mDrawingView.getBitmap());
                    startShareDialog(uri);
                } else
                {
                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void onMoreOptionsSelect(MenuItem item) {
        switch (item.getItemId()) {
            case settings:
                openSettingsActivity();
            default:
                super.onOptionsItemSelected(item);
        }
    }

    public void openSettingsActivity() {
        Intent intent = new Intent(this, settings_activity.class);
        startActivity(intent);
    }


    @OnClick(R.id.main_fill_ib)
    public void onBackgroundFillOptionClick()
    {
        startFillBackgroundDialog();
    }

    @OnClick(R.id.main_colour_ib)
    public void onColorOptionClick()
    {
        startColorPickerDialog();
    }

    @OnClick(R.id.main_stroke_ib)
    public void onStrokeOptionClick()
    {
        startStrokeSelectorDialog();
    }

   // @OnClick(R.id.select_shape_ib)
   // public void onShapeOptionClick() { drawRectangle(); }

    @OnClick(R.id.main_undo_ib)
    public void onUndoOptionClick()
    {
        mDrawingView.undo();
    }

    @OnClick(R.id.main_redo_ib)
    public void onRedoOptionClick()
    {
        mDrawingView.redo();
    }

}


