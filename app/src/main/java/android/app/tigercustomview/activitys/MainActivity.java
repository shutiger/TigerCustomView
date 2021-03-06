package android.app.tigercustomview.activitys;

import android.app.tigercustomview.R;
import android.app.tigercustomview.customviews.LoadingCustomView;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mLineCustomViewBtn;
    private Button mRaysCustomViewBtn;
    private Button mLoadingCustomViewBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mLineCustomViewBtn = (Button) findViewById(R.id.id_line_custom_view);
        mLineCustomViewBtn.setOnClickListener(this);
        mRaysCustomViewBtn = (Button) findViewById(R.id.id_rays_custom_view);
        mRaysCustomViewBtn.setOnClickListener(this);
        mLoadingCustomViewBtn = (Button) findViewById(R.id.id_loading_custom_view);
        mLoadingCustomViewBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_line_custom_view:
                startActivity(new Intent(this, LineCustomViewActivity.class));
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case R.id.id_rays_custom_view:
                startActivity(new Intent(this, RaysCustomViewActivity.class));
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            case R.id.id_loading_custom_view:
                startActivity(new Intent(this, LoadingCustomViewActivity.class));
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                break;
            default:
                break;
        }
    }

}
