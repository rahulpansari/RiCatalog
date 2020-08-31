package com.ricatalog.ricatalog.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Magnifier;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.viewmodel.ZoomActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZoomInFragment extends Fragment {
    @BindView(R.id.imagezoom)
    SubsamplingScaleImageView imagezoom;
    Magnifier magnifier;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.zoom_in_fragment,container,false);
        ButterKnife.bind(this,v);
        Bundle bundle=getArguments();
        Bitmap bmp =  getArguments().getParcelable("image");
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.P)
        {
            magnifier=new Magnifier(imagezoom);
            imagezoom.setImage(ImageSource.bitmap(bmp));
            imagezoom.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getActionMasked()) {
                        case MotionEvent.ACTION_DOWN:
                            // Fall through.
                        case MotionEvent.ACTION_MOVE: {
                            final int[] viewPosition = new int[2];
                            v.getLocationOnScreen(viewPosition);
                            magnifier.show(event.getRawX() - viewPosition[0],
                                    event.getRawY() - viewPosition[1]);
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:
                            // Fall through.
                        case MotionEvent.ACTION_UP: {
                            magnifier.dismiss();
                        }
                    }
                    return true;
                }
            });
        }
        else
        {

        }



        return v;
    }
}
