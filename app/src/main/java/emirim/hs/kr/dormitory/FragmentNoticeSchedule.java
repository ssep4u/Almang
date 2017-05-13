package emirim.hs.kr.dormitory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Eun bee on 2016-delete_things-08.
 */

public class FragmentNoticeSchedule extends Fragment {
    ImageView floor4,floor5;
    ImageView washing4,washing5;
    ImageView sun,mon,tue,wed,thu,fri,sat;
    public FragmentNoticeSchedule(){}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        RelativeLayout v=(RelativeLayout)inflater.inflate(R.layout.fragment_notice_schedule, container, false);
        sun = (ImageView)v.findViewById(R.id.sun);
        mon = (ImageView)v.findViewById(R.id.mon);
        tue = (ImageView)v.findViewById(R.id.tue);
        wed = (ImageView)v.findViewById(R.id.wed);
        thu = (ImageView)v.findViewById(R.id.thu);
        fri = (ImageView)v.findViewById(R.id.fri);
        sat = (ImageView)v.findViewById(R.id.sat);
        //but1.setOnClickListener(bHandler);
        sun.setOnClickListener(bHandler);
        mon.setOnClickListener(bHandler);
        tue.setOnClickListener(bHandler);
        wed.setOnClickListener(bHandler);
        thu.setOnClickListener(bHandler);
        fri.setOnClickListener(bHandler);
        sat.setOnClickListener(bHandler);
        floor4 = (ImageView)v.findViewById(R.id.four);
        floor5 = (ImageView)v.findViewById(R.id.five);
        washing4 = (ImageView)v.findViewById(R.id.washing4);
        washing5 = (ImageView)v.findViewById(R.id.washing5);
        floor4.setOnClickListener(aHandler);
        floor5.setOnClickListener(aHandler);
        return v;
    }
    View.OnClickListener aHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.four:
                    washing4.setVisibility(View.VISIBLE);
                    washing5.setVisibility(View.GONE);
                    break;
                case R.id.five:
                    washing4.setVisibility(View.GONE);
                    washing5.setVisibility(View.VISIBLE);
            }
        }
    };
    View.OnClickListener bHandler=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if((int)sun.getVisibility()==0){
                sun.setVisibility(View.GONE);
                mon.setVisibility(View.VISIBLE);
            }else if((int)mon.getVisibility()==0){
                mon.setVisibility(View.GONE);
                tue.setVisibility(View.VISIBLE);
            }else if((int)tue.getVisibility()==0){
                tue.setVisibility(View.GONE);
                wed.setVisibility(View.VISIBLE);
            }else if((int)wed.getVisibility()==0){
                wed.setVisibility(View.GONE);
                thu.setVisibility(View.VISIBLE);
            }else if((int)thu.getVisibility()==0){
                thu.setVisibility(View.GONE);
                fri.setVisibility(View.VISIBLE);
            }else if((int)fri.getVisibility()==0){
                fri.setVisibility(View.GONE);
                sat.setVisibility(View.VISIBLE);
            }else if((int)sat.getVisibility()==0){
                sat.setVisibility(View.GONE);
                sun.setVisibility(View.VISIBLE);
            }
        }
    };
}
