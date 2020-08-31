package kr.ac.uck.hubdriver.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import kr.ac.uck.hubdriver.R;
import kr.ac.uck.hubdriver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.tvMain.setOnClickListener(view -> {
            Intent intent = new Intent(this, LicensesActivity.class);
            startActivity(intent);
        });
    }
}