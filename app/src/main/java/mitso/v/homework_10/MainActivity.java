package mitso.v.homework_10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mRelativeLayout_Layout_AM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mRelativeLayout_Layout_AM = (RelativeLayout) findViewById(R.id.rl_Layout_AM);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mi_Ball:
                mRelativeLayout_Layout_AM.setBackgroundColor(getResources().getColor(R.color.c_bg_ball_AM));
                updateFragment(new BallFragment());
                return true;
            case R.id.mi_List:
                mRelativeLayout_Layout_AM.setBackgroundColor(getResources().getColor(R.color.c_bg_list_AM));
                updateFragment(new ListFragment());
                return true;
            case R.id.mi_Heart:
                mRelativeLayout_Layout_AM.setBackgroundColor(getResources().getColor(R.color.c_bg_heart_AM));
                updateFragment(new HeartFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateFragment(BaseFragment baseFragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_FragmentContainer_AM, baseFragment)
                .commitAllowingStateLoss();
    }


}