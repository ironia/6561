package org.howcompany.mytown;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout init_main_message;
    RecyclerView recyclerView;
    RecyclerView contentRecyclerView;
    EditText input_code;
    EditText input_phone;
    Button request_button;
    Button homepage_button;
    Button band_button;
    Button cafe_button;
    Button open_kakao_button;
    Button friend_company;
    Button re_input;
    Button findUser;
    String code;
    String phone;
    String city_value="000";
    String shop_value="000";
    int fu;
    ConstraintLayout input_code_layout;
    ConstraintLayout input_after_Button_layout;
    ConstraintLayout input_spinner_layout;
    private FirebaseRemoteConfig firebaseRemoteConfig;
    TextView input_after_message;
    TextView round_text;
    TextView message_line1;
    TextView sum_lp;
    ConstraintLayout message_line2;
    Button kakao_11_textView;
    Button kakao_story_button;
    Button face_book_button;
    Button twitter_button;
    Button app_button;
    RadioGroup checkBox;
    Retrofit retrofit;
    ApiService service;
    FindUserApiService findUserApiService;
    FindUserAdapter adapter;
    Spinner city_spinner;
    Spinner shop_spinner;
    ArrayList<String> city_code = new ArrayList<>();
    ArrayList<String> shop_code = new ArrayList<>();

    @Override
    public void onBackPressed() {
        //안전한 종료 버튼

        AlertDialog.Builder programEndBuilder = new AlertDialog.Builder(this);
        programEndBuilder.setTitle(R.string.exit);
        programEndBuilder.setIcon(android.R.drawable.ic_dialog_alert);
        programEndBuilder.setMessage(R.string.exitMessage);
        programEndBuilder.setNegativeButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.finishAffinity(MainActivity.this);
                System.runFinalization();
                System.exit(0);
            }
        });
        programEndBuilder.setPositiveButton(R.string.cancel, null);
        programEndBuilder.show();


    }

    public void init_view(final String value, final TextView view) {

        Call<Mytown> call = service.inputCode("","","", "");

        call.enqueue(new Callback<Mytown>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Mytown> call, Response<Mytown> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body() != null) {
                            Mytown mytown = response.body();
                            fu = mytown.getFu();
                        } else {
                            fu = 1;
                        }
                        String message = "제 " + value + "회차      " + fu + " / 6561 ";
                        view.setText(message);
                    } catch (Error e) {
                        Log.d("Main", "못 받음.");

                    }
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Mytown> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        final FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        shop_code.add("▶ 가맹점번호");
        city_code.add("▶ 지점번호");
        for(int i = 1 ; i<1000;i++){
            String value=i+"";
            shop_code.add(value);
            city_code.add(value);
            /*if(i<10){
                value = "00"+i;
                shop_code.add(value);
                city_code.add(value);
            }else if(i<100){
                value = "0"+i;
                shop_code.add(value);
                city_code.add(value);
            }else {
                value = i+"";
                shop_code.add(value);
                city_code.add(value);
            }*/
        }
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        firebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        firebaseRemoteConfig.fetch(0).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) firebaseRemoteConfig.activate();
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        contentRecyclerView = findViewById(R.id.content_lotto_recycler);
        app_button = findViewById(R.id.app_button);
        face_book_button = findViewById(R.id.face_button);
        twitter_button = findViewById(R.id.twitter_button);
        kakao_story_button = findViewById(R.id.kakao_story_button);
        app_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://band.us/band/79639928/post/139"));
                startActivity(intent);
            }
        });
        face_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/doendakai"));
                startActivity(intent);
            }
        });
        twitter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://twitter.com/q1r7LBYrfaGi19u"));
                startActivity(intent);
            }
        });
        kakao_story_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://story.kakao.com/_JREsy9"));
                startActivity(intent);
            }
        });


        contentRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        MyDecoration decoration = new MyDecoration();
        ContentDecoration contentDecoration = new ContentDecoration();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        contentRecyclerView.addItemDecoration(contentDecoration);
        recyclerView.addItemDecoration(decoration);

        //contentRecycler make

        ArrayList<Content> contentList = new ArrayList<>();
        Content content8 = new Content("300,000",3);
        Content content7 = new Content("30,000",9);
        Content content6 = new Content("10,000",9);
        Content content5 = new Content("5,000",27);
        Content content4 = new Content("1,500",81);
        Content content3 = new Content("1,000",243);
        Content content2 = new Content("500",729);
        Content content1 = new Content("150",2187);
        contentList.add(content8);
        contentList.add(content7);
        contentList.add(content6);
        contentList.add(content5);
        contentList.add(content4);
        contentList.add(content3);
        contentList.add(content2);
        contentList.add(content1);

        contentRecyclerView.setAdapter(new ContentAdapter(contentList));


        re_input = findViewById(R.id.re_input);
        findUser = findViewById(R.id.find_user);
        input_spinner_layout = findViewById(R.id.input_city_shop_code_layout);
        cafe_button = findViewById(R.id.cafe_button);
        message_line1 = findViewById(R.id.message_line1);
        message_line2 = findViewById(R.id.message_line2);
        kakao_11_textView = findViewById(R.id.kakao_c_button);
        kakao_11_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://open.kakao.com/o/sn1nYQJ"));
                startActivity(intent);
            }
        });
        /*String phone_message = "6561 행운 코드 문의전화 : 010-5738-9327";

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(phone_message);
        Linkify.TransformFilter transformFilter = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };

        //Pattern pattern = Pattern.compile("");
        Linkify.addLinks(spannableStringBuilder, Patterns.PHONE,"010-5738-9327");
        phone_textView.setText(phone_message);
        phone_textView.setMovementMethod(LinkMovementMethod.getInstance());*/
        sum_lp = findViewById(R.id.sum_lp);
        city_spinner = findViewById(R.id.city_spinner);
        shop_spinner = findViewById(R.id.shop_spinner);
        ArrayAdapter city_adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,city_code){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v =  super.getView(position,convertView,parent);
                ((TextView)v).setTextSize(16);
            return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = getView(position,convertView,parent);
                ((TextView)v).setGravity(Gravity.CENTER);
                return v;
            }
        };
        city_spinner.setAdapter(city_adapter);
        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                city_value =  city_spinner.getSelectedItem().toString();
                Log.d("main",city_value);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                city_value = "0";
            }
        });
        final ArrayAdapter shop_adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,shop_code){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v =  super.getView(position,convertView,parent);
                ((TextView)v).setTextSize(16);
                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = getView(position,convertView,parent);
                ((TextView)v).setGravity(Gravity.CENTER);
                return v;
            }
        };
        shop_spinner.setAdapter(shop_adapter);
        shop_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shop_value = shop_spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*city_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        shop_spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
        input_code_layout = findViewById(R.id.input_code_layout);
        input_after_Button_layout = findViewById(R.id.input_after_button_layout);
        checkBox = findViewById(R.id.function_checkbox);
        input_after_message = findViewById(R.id.input_after_message);
        checkBox.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.input_code_checkbox:
                        input_code_layout.setVisibility(View.VISIBLE);
                        init_main_message.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        sum_lp.setVisibility(View.GONE);
                        input_spinner_layout.setVisibility(View.VISIBLE);
                        input_after_message.setVisibility(View.GONE);
                        inputAfterViewSetting(false);

                        break;
                    case R.id.find_code_checkbox:
                        //
                        //확인
                        input_spinner_layout.setVisibility(View.GONE);

                        input_code_layout.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                        init_main_message.setVisibility(View.GONE);
                        input_after_Button_layout.setVisibility(View.GONE);
                        sum_lp.setVisibility(View.VISIBLE);
                        sum_lp.setText("");
                        break;
                }
            }
        });
        init_main_message = findViewById(R.id.init_message_view);
        round_text = findViewById(R.id.round_text);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://mytown.cafe24app.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
        findUserApiService = retrofit.create(FindUserApiService.class);
        final String round_value = getRemoteConfig("round6561");
        init_view(round_value, round_text);
        input_code = findViewById(R.id.input_code);
        input_phone = findViewById(R.id.input_phone);
        request_button = findViewById(R.id.request_button);
        homepage_button = findViewById(R.id.homepage_button);
        band_button = findViewById(R.id.band_button);
        open_kakao_button = findViewById(R.id.kakao_button);
        friend_company = findViewById(R.id.friend_company);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView_main);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        re_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_after_Button_layout.setVisibility(View.GONE);
                input_code.setText("");
                inputAfterViewSetting(false);
            }
        });
        findUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.check(R.id.find_code_checkbox);

            }
        });
        homepage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://vrtown.modoo.at/"));
                startActivity(intent);

            }
        });

        cafe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://band.us/band/79639928/post/21"));
                startActivity(intent);
            }
        });
        band_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://band.us/@6561membership"));
                startActivity(intent);
            }
        });

        open_kakao_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://open.kakao.com/o/gmAmx2ac"));
                startActivity(intent);
            }
        });
        friend_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, friend_company.class);
                startActivity(intent);
            }
        });
        request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.getCheckedRadioButtonId() == R.id.input_code_checkbox) {
                    Log.d("dd", input_code.getText().toString().length() + "");
                    if (input_code.getText().toString().length() == 8) {
                        request_button.setText("전송");
                        if (input_code.getText() != null) {
                            code = input_code.getText().toString();
                        } else {
                            code = "";
                        }
                        if (input_phone.getText() != null) {
                            phone = input_phone.getText().toString();
                        } else {
                            phone = "";
                        }

                    } else {
                        //오류 고지
                        input_after_Button_layout.setVisibility(View.VISIBLE);
                        input_after_message.setText("코드가 잘 못되었습니다.");

                    }
                    Log.d("zz", code + " : " + phone+" :"+city_value+" : "+shop_value);
                    /*Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://mytown.cafe24app.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();*/
                    //ApiService service = retrofit.create(ApiService.class);
                    Call<Mytown> call = service.inputCode(city_value, shop_value, phone, code);

                    call.enqueue(new Callback<Mytown>() {
                        @EverythingIsNonNull
                        @Override
                        public void onResponse(Call<Mytown> call, Response<Mytown> response) {
                            if (response.isSuccessful()) {
                                try {
                                    if (response.body() != null) {
                                        Mytown mytown = response.body();

                                        String value;

                                        if (mytown.getStatus() == 401) {
                                            value = "코드가 잘못되었습니다.";
                                        } else if (mytown.getStatus() == 200) {
                                            value = "6561개 중 " + mytown.getFu() + "번 째로 등록되었습니다.";
                                        } else if (mytown.getStatus() == 100) {
                                            value = "이미 입력된 코드입니다.";
                                        } else {
                                            value = "입력이 잘못되었습니다.";
                                        }

                                        inputAfterViewSetting(true);
                                        //input_code_layout.setVisibility(View.GONE);
                                        input_after_Button_layout.setVisibility(View.VISIBLE);
                                        init_view(round_value, round_text);
                                        input_after_message.setText(value);

                                        //실제 작동

                                    }


                                    //message.setText(String.valueOf(mytown.getFu()));
                                } catch (Error e) {
                                    Log.d("Main", "못 받음.");

                                }

                            }

                        }

                        @EverythingIsNonNull
                        @Override
                        public void onFailure(Call<Mytown> call, Throwable t) {

                        }
                    });
                } else if (checkBox.getCheckedRadioButtonId() == R.id.find_code_checkbox) {
                    request_button.setText("검색");
                    recyclerView.setVisibility(View.VISIBLE);
                    phone = input_phone.getText().toString();
                    Log.d("Main", " : " + phone);
                    /*Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://mytown.cafe24app.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();*/

                    Call<FindUser> call = findUserApiService.findUser(phone);
                    call.enqueue(new Callback<FindUser>() {
                        @EverythingIsNonNull
                        @Override
                        public void onResponse(Call<FindUser> call, Response<FindUser> response) {
                            if (response.body() != null) {
                                FindUser user = response.body();
                                List<User> users = user.getUser();
                                int sum_lp_value = 0;
                                for (int i = 0; i < users.size(); i++) {
                                    sum_lp_value += users.get(i).getLp();
                                    Log.d("point",i+"차"+users.get(i).getLp());
                                }
                                init_view(round_value, round_text);
                                String value = "당첨 포인트 합계 : " + sum_lp_value + " point";
                                sum_lp.setVisibility(View.VISIBLE);
                                sum_lp.setText(value);
                                if (users.size() != 0) {
                                    adapter = new FindUserAdapter(user, fu);
                                    recyclerView.setAdapter(adapter);


                                } else {
                                    recyclerView.setVisibility(View.GONE);
                                    sum_lp.setText("검색 결과가 없습니다.");
                                }

                                Log.d("hahaha", user + "");
                            /*
                            String message = "id = "+user.getId()+
                                    "lp = "+user.getLp()+
                                    "code = "+user.getCode()+
                                    "phone = "+user.getPhone();
                            message_main_view.setText(message);
*/
                            } else {
                                sum_lp.setVisibility(View.VISIBLE);
                                sum_lp.setText("검색 결과가 없습니다.");
                            }
                        }

                        @EverythingIsNonNull
                        @Override
                        public void onFailure(Call<FindUser> call, Throwable t) {
                            sum_lp.setVisibility(View.VISIBLE);
                            sum_lp.setText("검색 결과가 없습니다.");
                        }
                    });
                }
            }
        });


    }

    public void inputAfterViewSetting(boolean state) {

        if (state) {
            //입력 후
            input_after_message.setVisibility(View.VISIBLE);
            message_line1.setVisibility(View.GONE);
            message_line2.setVisibility(View.GONE);


        } else {
            //입력 전
            input_after_message.setVisibility(View.GONE);
            message_line1.setVisibility(View.VISIBLE);
            message_line2.setVisibility(View.VISIBLE);

        }
    }

    public static String getRemoteConfig(String key) {
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        return remoteConfig.getString(key);
    }
    public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        ArrayList<Content> list;

        private ContentAdapter(ArrayList<Content> list) {
            this.list = list;



        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.content_card, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Content content = list.get(position);
            MyHolder holder1 = (MyHolder) holder;
            String round = "";

            switch (position){
                case 0:
                    round = "8구간(6561)";
                    break;
                case 1:
                    round = "7구간(2187)";
                    break;
                case 2:
                    round = "6구간(729)";
                    break;
                case 3:
                    round = "5구간(243)";
                    break;
                case 4:
                    round = "4구간(81)";
                    break;
                case 5:
                    round = "3구간(27)";
                    break;
                case 6:
                    round = "2구간(9)";
                    break;
                case 7:
                    round = "1구간(3)";
                    break;

            }
            holder1.round.setText(round);
            String point= content.getPoint();

            if(position<3){
                point = content.getPoint()+"\n(당첨가맹점 전용)";
            }else{
                point = "            "+content.getPoint();

            }
            holder1.point.setText(point);
            holder1.count.setText(content.getCount());

        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        private class MyHolder extends RecyclerView.ViewHolder {
            private TextView round;
            private TextView point;
            private TextView count;



            private MyHolder(View itemView) {
                super(itemView);
                round = itemView.findViewById(R.id.round_content);
                point = itemView.findViewById(R.id.point_content);
                count = itemView.findViewById(R.id.count_content);

            }
        }

    }

    public class FindUserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<User> list;
        int fu;

        private FindUserAdapter(FindUser users, int fu) {
            this.list = users.getUser();
            this.fu = fu;


        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.find_user_card, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            User user = list.get(position);
            MyHolder holder1 = (MyHolder) holder;
            String value;

            //String code_1 = user.getCode().substring(0, 2);
            //String code_2 = user.getCode().substring(2, 5);

            /*value = (position + 1) + "번 코드  : " + user.getCode() + "\n" + "지점코드 : " + user.getCity() + " 가맹점코드 : " + user.getShop();
            holder1.code.setText(value);*/
            holder1.code.setText(user.getCode());
            holder1.city_code.setText(user.getCity());
            holder1.shop_code.setText(user.getShop());


            if (fu > 2 && fu < 9) {
                value = user.getR1() + " point";
                holder1.r1.setText(value);
            } else if (fu > 8 && fu < 27) {
                value = user.getR1() + " point";
                holder1.r1.setText(value);
                value = user.getR2() + " point";
                holder1.r2.setText(value);
            } else if (fu > 26 && fu < 81) {
                value = user.getR1() + " point";
                holder1.r1.setText(value);
                value = user.getR2() + " point";
                holder1.r2.setText(value);
                if (user.getR3() != 0) {
                    value = user.getR3() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r3.setText(value);
            } else if (fu > 80 && fu < 243) {
                value = user.getR1() + " point";
                holder1.r1.setText(value);
                value = user.getR2() + " point";
                holder1.r2.setText(value);
                if (user.getR3() != 0) {
                    value = user.getR3() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r3.setText(value);
                if (user.getR4() != 0) {
                    value = user.getR4() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r4.setText(value);
            } else if (fu > 242 && fu < 729) {
                value = user.getR1() + " point";
                holder1.r1.setText(value);
                value = user.getR2() + " point";
                holder1.r2.setText(value);
                if (user.getR3() != 0) {
                    value = user.getR3() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r3.setText(value);
                if (user.getR4() != 0) {
                    value = user.getR4() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r4.setText(value);
                if (user.getR5() != 0) {
                    value = user.getR5() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r5.setText(value);
            } else if (fu > 728 && fu < 2187) {
                value = user.getR1() + " point";
                holder1.r1.setText(value);
                value = user.getR2() + " point";
                holder1.r2.setText(value);
                if (user.getR3() != 0) {
                    value = user.getR3() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r3.setText(value);
                if (user.getR4() != 0) {
                    value = user.getR4() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r4.setText(value);
                if (user.getR5() != 0) {
                    value = user.getR5() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r5.setText(value);
                value = user.getR6() + " point";
                holder1.r6.setText(value);
            } else if (fu > 2186 && fu < 6561) {
                value = user.getR1() + " point";
                holder1.r1.setText(value);
                value = user.getR2() + " point";
                holder1.r2.setText(value);
                if (user.getR3() != 0) {
                    value = user.getR3() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);

                }
                holder1.r3.setText(value);
                if (user.getR4() != 0) {
                    value = user.getR4() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r4.setText(value);
                if (user.getR5() != 0) {
                    value = user.getR5() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r5.setText(value);
                value = user.getR6() + " point";
                holder1.r6.setText(value);
                value = user.getR7() + " point";
                holder1.r7.setText(value);
            } else if (fu == 6561) {
                value = user.getR1() + " point";
                holder1.r1.setText(value);
                value = user.getR2() + " point";
                holder1.r2.setText(value);
                if (user.getR3() != 0) {
                    value = user.getR3() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r3.setText(value);
                if (user.getR4() != 0) {
                    value = user.getR4() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r4.setText(value);
                if (user.getR5() != 0) {
                    value = user.getR5() + " point";
                } else {
                    value = getString(R.string.defeat_lotto);
                }
                holder1.r5.setText(value);
                value = user.getR6() + " point";
                holder1.r6.setText(value);
                value = user.getR7() + " point";
                holder1.r7.setText(value);
                value = user.getR8() + " point";
                holder1.r8.setText(value);
            }
            int countOfMore = user.getLp() % 10;
            int pointOfSet = user.getLp() - countOfMore;
            value = pointOfSet + " point";
            holder1.lp.setText(value);
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        private class MyHolder extends RecyclerView.ViewHolder {
            private TextView code;
            private TextView city_code;
            private TextView shop_code;
            private TextView r1;
            private TextView r2;
            private TextView r3;
            private TextView r4;
            private TextView r5;
            private TextView r6;
            private TextView r7;
            private TextView r8;
            private TextView lp;


            private MyHolder(View itemView) {
                super(itemView);
                code = itemView.findViewById(R.id.code);
                city_code = itemView.findViewById(R.id.city_code);
                shop_code = itemView.findViewById(R.id.shop_code);
                r1 = itemView.findViewById(R.id.r1);
                r2 = itemView.findViewById(R.id.r2);
                r3 = itemView.findViewById(R.id.r3);
                r4 = itemView.findViewById(R.id.r4);
                r5 = itemView.findViewById(R.id.r5);
                r6 = itemView.findViewById(R.id.r6);
                r7 = itemView.findViewById(R.id.r7);
                r8 = itemView.findViewById(R.id.r8);
                lp = itemView.findViewById(R.id.lp);
            }
        }

    }

    static class MyDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            ViewCompat.setElevation(view, 3.0f);
            outRect.set(20, 10, 20, 10);
        }
    }
    static class ContentDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            ViewCompat.setElevation(view, 3.0f);
            outRect.set(30, 8, 30, 8);
        }
    }
    class Content{
        String point;
        int count;


        public Content(String point, int count) {
            this.point = point;
            this.count = count;
        }

        public String getPoint() {
            return point+" P";
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getCount() {
            return "소계 "+count+" 장";
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}

