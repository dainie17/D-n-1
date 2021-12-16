package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GuiThongBaoActivity extends AppCompatActivity {

    ImageView img_ThongBaoBack;
    AutoCompleteTextView edt_LoaiThongBao;
    TextInputLayout edt_NoiDungTB;
    Button btn_GuiThongBao;
    TextView txt_ChonNguoi;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");;
    DatabaseReference ref;

    Thongbao thongbao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_thong_bao);

        img_ThongBaoBack = this.findViewById(R.id.img_ThongBaoBack);
        edt_LoaiThongBao = this.findViewById(R.id.edt_LoaiThongBao);
        edt_NoiDungTB = this.findViewById(R.id.edt_NoiDungTB);
        btn_GuiThongBao = this.findViewById(R.id.btn_GuiThongBao);
        txt_ChonNguoi = this.findViewById(R.id.txt_ChonNguoi);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        thongbao = new Thongbao();

        String id = firebaseUser.getUid();

        ref = database.getReference("Users");
        Query query = ref.orderByChild("id").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    thongbao.setLinkAnh(user.getImageURL());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        List<String> loaiTB = new ArrayList<>();
        loaiTB.add("Thông báo");
        loaiTB.add("Cảnh báo");
        ArrayAdapter adapterTB = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, loaiTB);
        edt_LoaiThongBao.setAdapter(adapterTB);
        edt_LoaiThongBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                thongbao.setLoaiThongBao(item);

            }
        });

        if (getIntent().getExtras() != null){

            List<String> IdNguoiNhan = getIntent().getStringArrayListExtra("listId");

            btn_GuiThongBao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseReference reference = database.getReference("ThongBao");

                    String noiDung = edt_NoiDungTB.getEditText().getText().toString();
                    thongbao.setNoiDung(noiDung);

                    for (int i = 0; i < IdNguoiNhan.size(); i++){

                        String idTB = reference.push().getKey();
                        thongbao.setIDThongBao(idTB);
                        thongbao.setIDNguoiNhan(IdNguoiNhan.get(i));
                        reference.child(thongbao.getIDThongBao()).setValue(thongbao).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(GuiThongBaoActivity.this, "Đã gửi thông báo !", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        });

                    }

                }
            });

        }

        txt_ChonNguoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            startActivity(new Intent(GuiThongBaoActivity.this, ChonNguoiActivity.class));

            }
        });

        img_ThongBaoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}