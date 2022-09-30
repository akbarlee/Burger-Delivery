package com.example.commercial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.commercial.MainFragment.CallbackFragment;
import com.example.commercial.MainFragment.LoginFragment;
import com.example.commercial.MainFragment.RegisterFragment;

public class LogRegActivity extends AppCompatActivity implements CallbackFragment {
 Fragment fragment;
 FragmentManager fragmentManager;
 FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);
        addFragment();
    }
  public void addFragment() {
        LoginFragment loginFragment = new LoginFragment();
      loginFragment.setCallbackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.regcontainer,loginFragment);
        fragmentTransaction.commit();

  }

    public void replaceFragment() {
        fragment = new RegisterFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.regcontainer , fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void changeFragment() {
          replaceFragment();
    }


}