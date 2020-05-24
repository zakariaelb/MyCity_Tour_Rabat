package digiplus.ma.mycitytour_rabat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
        /* Set ativity content to use the activity_main.xml
        */

        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.Root_View_Pager_ID);

        MainView_Adapter adapter = new MainView_Adapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.TabLayout_ID);

        tabLayout.setupWithViewPager(viewPager);
    }
}
