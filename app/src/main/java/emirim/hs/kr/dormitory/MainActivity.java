package emirim.hs.kr.dormitory;

        import android.content.Intent;

        import android.os.Bundle;
        import android.support.design.widget.TabLayout;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;

        import emirim.hs.kr.dormitory.fragment.RecentBuyFragment;
        import emirim.hs.kr.dormitory.fragment.RecentPostsFragment;

public class  MainActivity extends BaseActivity2 {

    private static final String TAG = "MainActivity";
    private final long FINISH_INTERVAL_TIME = 2000;
    private long   backPressedTime = 0;
    private int canexit = 0;
    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private final int[] ICONS = {
            R.drawable.this1,
            R.drawable.this2,
            R.drawable.this3,
            R.drawable.this4
    };
    private final int[] thisIcons = {
            R.drawable.this1_1,
            R.drawable.this2_2,
            R.drawable.this3_2,
            R.drawable.this4_4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(S.roomNameP+"_과자들");
        // Create the adapter that will return a fragment for each section
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[] mFragments = new Fragment[] {
                    new FragmentProfile(),
                    new RecentBuyFragment(),
                    new RecentPostsFragment(),
                    new FragmentNotice()
            };
            private final String[] mFragmentNames = new String[] {
                    "과자들",
                    "예약",
                    "공지",
                    "규칙"
            };

            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }
            public int getCount() {
                return mFragments.length;
            }
            /*
            @Override
            public CharSequence getPageTitle(int position) {
                return mFragmentNames[position];
            }*/
        };
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(ICONS[0]);
        tabLayout.getTabAt(1).setIcon(thisIcons[1]);
        tabLayout.getTabAt(2).setIcon(thisIcons[2]);
        tabLayout.getTabAt(3).setIcon(thisIcons[3]);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        getSupportActionBar().setTitle(S.roomNameP+"_과자들");
                        canexit=0;
                        findViewById(R.id.fab_new_post).setVisibility(View.GONE);
                        findViewById(R.id.fab_new_buy).setVisibility(View.GONE);
                        for(int i=0;i<tabLayout.getTabCount();i++){
                            if(i==position){
                                tabLayout.getTabAt(i).setIcon(ICONS[i]);
                            }else {
                                tabLayout.getTabAt(i).setIcon(thisIcons[i]);
                            }
                        }
                        break;
                    case 1:
                        getSupportActionBar().setTitle(S.roomNameP+"_예약");
                        canexit=1;
                        findViewById(R.id.fab_new_post).setVisibility(View.GONE);
                        findViewById(R.id.fab_new_buy).setVisibility(View.VISIBLE);
                        for(int i=0;i<tabLayout.getTabCount();i++){
                            if(i==position){
                                tabLayout.getTabAt(i).setIcon(ICONS[i]);
                            }else {
                                tabLayout.getTabAt(i).setIcon(thisIcons[i]);
                            }
                        }
                        break;
                    case 2:
                        getSupportActionBar().setTitle(S.roomNameP+"_공지");
                        canexit=2;
                        findViewById(R.id.fab_new_post).setVisibility(View.VISIBLE);
                        findViewById(R.id.fab_new_buy).setVisibility(View.GONE);
                        for(int i=0;i<tabLayout.getTabCount();i++){
                            if(i==position){
                                tabLayout.getTabAt(i).setIcon(ICONS[i]);
                            }else {
                                tabLayout.getTabAt(i).setIcon(thisIcons[i]);
                            }
                        }
                        break;
                    case 3:
                        getSupportActionBar().setTitle(S.roomNameP+"_규칙");
                        canexit=3;
                        findViewById(R.id.fab_new_post).setVisibility(View.GONE);
                        findViewById(R.id.fab_new_buy).setVisibility(View.GONE);
                        for(int i=0;i<tabLayout.getTabCount();i++){
                            if(i==position){
                                tabLayout.getTabAt(i).setIcon(ICONS[i]);
                            }else {
                                tabLayout.getTabAt(i).setIcon(thisIcons[i]);
                            }
                        }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Button launches NewPostActivity
        findViewById(R.id.fab_new_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewPostActivity.class));
            }
        });
        findViewById(R.id.fab_new_buy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewBuyActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, SplashActivity.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;
        switch (canexit){
            case 0:
                if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
                {
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
                else
                {
                    backPressedTime = tempTime;
                    Toast.makeText(getApplicationContext(), "종료하시려면 뒤로가기를 한 번더 눌러주세요", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:case 2:case 3:
                mViewPager.setCurrentItem(0);break;
        }
    }
}
