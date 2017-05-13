package emirim.hs.kr.dormitory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import emirim.hs.kr.dormitory.models.Room;
import emirim.hs.kr.dormitory.models.UserLogin;

/**
 * Created by Eun bee on 2016-delete_things-18.
 */

public class OpenRoomActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private static final String REQUIRED = "입력해주세요";
    ImageView makeRoom,enterRoom;
    EditText roomPwSet,roomPwChk;
    EditText getRoomName,roomPw;
    EditText roomName;
    String passwd;
    String DBroom=null;
    String DBpass=null;
    String chk=null;
    String searchRoomPass,searchRoom;
    private DatabaseReference mDatabase;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_room);
        makeRoom = (ImageView) findViewById(R.id.makeRoom);
        enterRoom = (ImageView) findViewById(R.id.enterRoom);

        makeRoom.setOnClickListener(this);
        enterRoom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.makeRoom:
                LayoutInflater inflater1=getLayoutInflater();
                final View dialogView1= inflater1.inflate(R.layout.activity_room_form, null);

                AlertDialog.Builder buider1= new AlertDialog.Builder(this);
                buider1.setTitle("매점 생성하기");
                buider1.setView(dialogView1);
                roomName = (EditText)dialogView1.findViewById(R.id.room_name_set);
                roomPwSet = (EditText) dialogView1.findViewById(R.id.room_pw_set);
                roomPwChk = (EditText)dialogView1.findViewById(R.id.room_pw_chk);
                buider1.setPositiveButton("생성", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                buider1.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(OpenRoomActivity.this, "매점 생성을 취소합니다", Toast.LENGTH_SHORT).show();
                    }
                });
                final AlertDialog dialog1=buider1.create();
                dialog1.show();
                dialog1.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Boolean wantToCloseDialog = true;
                                if (TextUtils.isEmpty(roomName.getText().toString())) {
                                    roomName.setError(REQUIRED);
                                    return;
                                }
                                if (TextUtils.isEmpty(roomPwSet.getText().toString())) {
                                    roomPwSet.setError(REQUIRED);
                                    return;
                                }
                                if (TextUtils.isEmpty(roomPwChk.getText().toString())) {
                                    roomPwChk.setError(REQUIRED);
                                    return;
                                }
                                if(roomPwSet.getText().toString().equals(roomPwChk.getText().toString())) {
                                    S.roomNameP = roomName.getText().toString();
                                    passwd = roomPwSet.getText().toString();
                                    Room room = new Room(S.roomNameP,passwd);
                                    S.roomPassword = passwd;
                                    databaseReference.child("room").child(S.roomNameP).push().setValue(room);
                                    databaseReference.child("room_num").child(S.roomNameP).child("password").setValue(S.roomPassword);
                                    databaseReference.child("room_num").child(S.roomNameP).child("roomNum").setValue(S.roomNameP);

                                    databaseReference.child("user-room").child(S.roomNameP).child(S.author).child("photo").setValue(S.phorourl);
                                    databaseReference.child("user-room").child(S.roomNameP).child(S.author).child("name").setValue(S.author);
                                    Toast.makeText(OpenRoomActivity.this, "새로운 매점"+S.roomNameP+"을(를) 생성했습니다", Toast.LENGTH_SHORT).show();
                                    wantToCloseDialog = true;
                                }else{
                                    Toast.makeText(OpenRoomActivity.this, "비밀번호가 서로 다릅니다. 동일하게 입력해주세요.", Toast.LENGTH_SHORT).show();
                                    wantToCloseDialog = false;
                                }
                                if (wantToCloseDialog) {
                                    dialog1.dismiss();
                                    startActivity(new Intent(OpenRoomActivity.this, MainActivity.class));
                                }
                            }
                        });
                break;
            case R.id.enterRoom:
                LayoutInflater inflater2=getLayoutInflater();
                final View dialogView2= inflater2.inflate(R.layout.dialog_enter_room, null);
                AlertDialog.Builder buider2= new AlertDialog.Builder(this);
                buider2.setTitle("매점 입장하기");
                buider2.setView(dialogView2);
                getRoomName = (EditText) dialogView2.findViewById(R.id.inputRoomNumber);
                roomPw = (EditText)dialogView2.findViewById(R.id.inputPasswd);
                buider2.setPositiveButton("Complite", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                buider2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(OpenRoomActivity.this, "매점 입장을 취소합니다", Toast.LENGTH_SHORT).show();
                    }
                });
                final AlertDialog dialog2=buider2.create();
                dialog2.show();
                dialog2.getButton(AlertDialog.BUTTON_POSITIVE)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Boolean wantToCloseDialog = true;
                                mDatabase= FirebaseDatabase.getInstance().getReference();

                                //if( mDatabase.child("room/"+getRoomName.getText().toString() != )
                                //String searchRoom = "room_num/"+getRoomName.getText().toString()+"/password";
                                searchRoom = "room_num/"+getRoomName.getText().toString()+"/roomNum";
                                mDatabase.child(searchRoom).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override

                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        try {
                                            DBroom = dataSnapshot.getValue(String.class);
                                            searchRoomPass = "room_num/" + getRoomName.getText().toString() + "/password";
                                            mDatabase.child(searchRoomPass).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshots) {
                                                    try {
                                                        DBpass = dataSnapshots.getValue(String.class);
                                                        if(roomPw.getText().toString().equals(DBpass)){

                                                            Toast.makeText(OpenRoomActivity.this,DBpass+"  "+DBroom+"(으)로 입장합니다. 환영합니다.",Toast.LENGTH_SHORT).show();
                                                            dialog2.dismiss();
                                                            S.roomNameP = DBroom;
                                                            startActivity(new Intent(OpenRoomActivity.this,MainActivity.class));
                                                        }
                                                    } catch (Exception es) {
                                                        Toast.makeText(OpenRoomActivity.this,"입력하신 방 이름과 비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });
                                        }catch(Exception e){
                                            Toast.makeText(OpenRoomActivity.this,"입력하신 방 이름이 존재하지 않습니다.",Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }
                        });
                break;
        }
    }
}