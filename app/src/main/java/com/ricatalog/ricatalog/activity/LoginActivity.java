package com.ricatalog.ricatalog.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.Fade;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.ricatalog.ricatalog.R;
import com.ricatalog.ricatalog.fragment.LoginFragment;
import com.ricatalog.ricatalog.fragment.SignupFragment;
import com.ricatalog.ricatalog.viewmodel.LoginViewModel;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {
LoginFragment loginFragment;
SignupFragment signupFragment;
LoginViewModel viewModel;
@BindView(R.id.imgback)
ImageView imgbackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel=new ViewModelProvider(this).get(LoginViewModel.class);
        loginFragment=LoginFragment.getInstance();
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up).addToBackStack(null).add(R.id.framelayout,LoginFragment.getInstance()).commit();
        viewModel.getFragment.observe(this, new Observer<Fragment>() {
            @Override
            public void onChanged(Fragment fragment) {
                if(fragment instanceof LoginFragment)
                {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up).addToBackStack(null).replace(R.id.framelayout,LoginFragment.getInstance()).commit();

                }
                else
                {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up).addToBackStack(null).replace(R.id.framelayout,SignupFragment.getInstance()).commit();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}