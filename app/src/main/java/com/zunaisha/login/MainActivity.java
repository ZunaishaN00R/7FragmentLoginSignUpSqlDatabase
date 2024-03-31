package com.zunaisha.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransitionImpl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button firstFramgment , secondFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstFramgment = findViewById(R.id.firstFragment);
        secondFragment = findViewById(R.id.secondFragment);
        firstFramgment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstFragment firstFragment=new FirstFragment();
                Bundle b =new Bundle();
                b.putString("course","Mobile Application Development");
                firstFragment.setArguments(b);
                loadFragment(firstFragment);
            }
        });
        secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondFragment secondFragment=new SecondFragment();
                Bundle b=new Bundle();
                b.putString("student","Zunaisha Noor Biet-f21-049");
                secondFragment.setArguments(b);
                loadFragment(secondFragment);
            }
        });
    }
    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
    }
}