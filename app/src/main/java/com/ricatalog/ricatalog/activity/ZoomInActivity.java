package com.ricatalog.ricatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Magnifier;
import android.widget.ProgressBar;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.fragment.MouseZoom;
import com.ricatalog.ricatalog.fragment.ZoomInFragment;
import com.ricatalog.ricatalog.utils.Utils;
import com.ricatalog.ricatalog.viewmodel.SubCategoryViewModel;
import com.ricatalog.ricatalog.viewmodel.ZoomActivityViewModel;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZoomInActivity extends AppCompatActivity {
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.drag)
            ImageView dragimg;
    ZoomActivityViewModel viewModel;
    Fragment fragment;
    Bitmap bitap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_in);
        ButterKnife.bind(this);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.P)
        {
            dragimg.setImageResource(R.drawable.zoomin);
        }
        progressBar.setVisibility(View.VISIBLE);
        viewModel=new ViewModelProvider(this).get(ZoomActivityViewModel.class);
        viewModel.getBitmap().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(Bitmap bitmap) {
                progressBar.setVisibility(View.GONE);

                if(bitmap!=null)
                {bitap=bitmap;
                    Bundle b = new Bundle();
                    b.putParcelable("image",bitmap);
                    fragment=new MouseZoom();
                    fragment.setArguments(b);
                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment,fragment).commit();

                }
            }
        });
        viewModel.fetchBitmap("http://radiantimpexjewels.com/erp/small/"+getIntent().getStringExtra("img"));

    }
    @OnClick(R.id.closebttn)
    public void closeActivity(View v)
    {
        this.finish();
    }
    @OnClick(R.id.drag)
    public void drag(View v)
    {
       if(fragment instanceof ZoomInFragment)
       {dragimg.setImageResource(R.drawable.zoomin);
           fragment=new MouseZoom();
           Bundle b = new Bundle();
           b.putParcelable("image",bitap);
           fragment=new MouseZoom();
           fragment.setArguments(b);

           getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment,fragment).commit();

       }
       else if(fragment instanceof MouseZoom) {
           dragimg.setImageResource(R.drawable.drag);
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
               fragment=new ZoomInFragment();
               Bundle b = new Bundle();
               b.putParcelable("image",bitap);
               fragment.setArguments(b);

               getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment, fragment).commit();
           }
       }


    }

}
