package emirim.hs.kr.dormitory;

import android.app.Activity;
import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Eun bee on 2016-10-24.
 */
public class SplashActivity extends Activity{
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** 처음 액티비티가 생성될때 불려진다. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        /* SPLASH_DISPLAY_LENGTH 뒤에 메뉴 액티비티를 실행시키고 종료한다.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* 메뉴액티비티를 실행하고 로딩화면을 죽인다.*/
                Intent mainIntent = new Intent(SplashActivity.this,FacebookLoginActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
