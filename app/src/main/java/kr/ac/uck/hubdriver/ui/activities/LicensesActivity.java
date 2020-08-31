package kr.ac.uck.hubdriver.ui.activities;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import kr.ac.uck.hubdriver.R;
import kr.ac.uck.hubdriver.databinding.ActivityLicensesBinding;

/**
 * Created by Jaehyeon on 2020/08/27.
 */
public class LicensesActivity extends AppCompatActivity {

    private ActivityLicensesBinding binding;
    private static String openSources = "opensource.md";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_licenses);

        Uri uriData = getIntent().getData();
        assert uriData != null;
        //String code = uriData.getQueryParameter("policy_id");

        binding.markdownView.loadMarkdownFromAssets(openSources);
    }
}
