package emirim.hs.kr.dormitory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eun bee on 2016-delete_things-08.
 */

public class FragmentNoticeRule extends Fragment {
    public FragmentNoticeRule(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.i("FragmentNoticeRule"," ");
        return inflater.inflate(R.layout.fragment_notice_rule, container, false);
    }
}
