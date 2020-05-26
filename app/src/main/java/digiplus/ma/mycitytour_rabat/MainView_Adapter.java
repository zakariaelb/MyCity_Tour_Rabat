package digiplus.ma.mycitytour_rabat;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Main Adapter
 */
public class MainView_Adapter extends FragmentPagerAdapter {

    private Context mContext;

    public MainView_Adapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Monuments_Fragment();
        } else if (position == 1) {
            return new Culture_Fragment();
        } else if (position == 2) {
            return new Lifestyle_Fragment();
        } else {
            return new Shopping_Fragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.Monuments);
        } else if (position == 1) {
            return mContext.getString(R.string.Cultur);
        } else if (position == 2) {
            return mContext.getString(R.string.LifeStyle);
        } else {
            return mContext.getString(R.string.Shopping);
        }
    }
}
