package com.example.quranonline.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quranonline.R;
import com.example.quranonline.adapter.AuthorAdapter;
import com.example.quranonline.data.local.AuthorClass;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.quranonline.data.local.HelperMethod.ReplaceFragment;


public class AuthorFragment extends BaseFragment {
    AuthorAdapter adapter;
    ArrayList<AuthorClass> data;
    @BindView(R.id.authorslv)
    ListView authorslv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setUpActivity();
        /////////////////
        View root = inflater.inflate(R.layout.fragment_author, container, false);
        ButterKnife.bind(this, root);
        addData();
        adapter = new AuthorAdapter(data, getActivity());
        authorslv.setAdapter(adapter);
        authorslv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SurahFragment fragment = new SurahFragment();
                fragment.server_name=data.get(position).name;
                fragment.server_num=data.get(position).servername;
                ReplaceFragment(getActivity().getSupportFragmentManager(),fragment,R.id.azkar_id , null , "medo");

            }
        });


        return root;
    }

    public void addData() {
        data = new ArrayList<>();
        data.add(new AuthorClass("11", "shatri", "ابو بكر الشاطري"));
        data.add(new AuthorClass("8", "ahmad_huth", "أحمد الحذيفي"));
        data.add(new AuthorClass("11", "hawashi", " أحمد الحواشي"));
        data.add(new AuthorClass("10", "trabulsi", " أحمد الطرابلسي"));
        data.add(new AuthorClass("10", "ajm", "أحمد بن علي العجمي "));
        data.add(new AuthorClass("10", "trablsi", " أحمد خضر الطرابلسي"));
        data.add(new AuthorClass("11", "saud", "أحمد سعود "));
        data.add(new AuthorClass("8", "saber", " أحمد صابر"));
        data.add(new AuthorClass("10", "Aamer", "أحمد عامر "));
        data.add(new AuthorClass("11", "ahmad_nu", " أحمد نعينع"));
        data.add(new AuthorClass("12", "akil", " أخيل عبدالحي روا "));
        data.add(new AuthorClass("9", "akrm", " أكرم العلاقمي"));
        ////put in store
        data.add(new AuthorClass("6", "akdr", " إبراهيم الأخضر"));
        data.add(new AuthorClass("10", "IbrahemSadan", " إبراهيم السعدان"));
        data.add(new AuthorClass("6", "abkr", " إدريس أبكر"));
        data.add(new AuthorClass("6", "jbreen", " ابراهيم الجبرين"));
        data.add(new AuthorClass("11", "jormy", " ابراهيم الجرمي"));
        data.add(new AuthorClass("10", "ibrahim_dosri_warsh", " ابراهيم الدوسري"));
        data.add(new AuthorClass("6", "3siri", "ابراهيم العسيري "));
        data.add(new AuthorClass("12", "zamri", " استاذ زامري "));
        data.add(new AuthorClass("8", "3zazi", "الحسيني العزازي "));
        data.add(new AuthorClass("7", "dokali", " الدوكالي محمد العالم"));
        data.add(new AuthorClass("9", "alzain", " الزين محمد أحمد"));
        data.add(new AuthorClass("9", "omran", "العشري عمران "));
        data.add(new AuthorClass("11", "koshi", " العيون الكوشي"));
        data.add(new AuthorClass("6", "fateh", " الفاتح محمد الزبير"));
        data.add(new AuthorClass("10", "qari", "القارئ ياسين "));

        ///new qura
        data.add(new AuthorClass("6", "twfeeq", " توفيق الصايغ")); //**
        data.add(new AuthorClass("6", "jamal", " جمال شاكر عبدالله"));
        data.add(new AuthorClass("6", "jaman", " جمعان العصيمي"));
        data.add(new AuthorClass("11", "hatem", " حاتم فريد الواع"));
        data.add(new AuthorClass("10", "qht", " خالد القحطاني"));
        data.add(new AuthorClass("11", "mohna", " خالد المهنا"));
        data.add(new AuthorClass("11", "kafi", " خالد عبدالكافي"));
        data.add(new AuthorClass("12", "tnjy", "خليفة الطنيجي"));
        data.add(new AuthorClass("9", "hamza", " داود حمزة"));
        data.add(new AuthorClass("12", "ifrad", " رشيد إفراد"));
        data.add(new AuthorClass("9", "zaki", " زكي داغستاني"));
        data.add(new AuthorClass("8", "sami_dosr", " سامي الدوسري"));
        data.add(new AuthorClass("7", "s_gmd", " سعد الغامدي")); //**
        data.add(new AuthorClass("7", "shur", "سعود الشريم"));  //**
        data.add(new AuthorClass("6", "shl", " سهل ياسين"));
        data.add(new AuthorClass("12", "sayed", " سيد رمضان"));
        data.add(new AuthorClass("12", "taher", "شيرزاد عبدالرحمن طاهر"));
        data.add(new AuthorClass("12", "hkm", " صابر عبدالحكم"));
        data.add(new AuthorClass("8", "sahood", " صالح الصاهود"));
        data.add(new AuthorClass("6", "s_bud", " صلاح البدير"));
        data.add(new AuthorClass("12", "salah_hashim_m", " صلاح الهاشم"));
        data.add(new AuthorClass("8", "bu_khtr", "صلاح بو خاطر"));//**
        data.add(new AuthorClass("10", "tareq", "طارق عبدالغني دعوب"));
        data.add(new AuthorClass("8", "a_klb", " عادل الكلباني"));
        data.add(new AuthorClass("8", "ryan", " عادل ريان"));
        data.add(new AuthorClass("6", "thubti", " عبدالبارئ الثبيتي"));
        data.add(new AuthorClass("12", "bari", " عبدالبارئ محمد"));
        data.add(new AuthorClass("10", "bari_molm", " عبدالبارئ محمد م"));
        data.add(new AuthorClass("7", "basit", " عبدالباسط عبدالصمد ح"));
        data.add(new AuthorClass("10", "basit_warsh", " عبدالباسط عبدالصمد و"));//**
        data.add(new AuthorClass("13", "basit_mjwd", " عبدالباسط عبدالصمد  م"));
        data.add(new AuthorClass("11", "sds", " عبدالرحمن السديس"));//**
        data.add(new AuthorClass("9", "soufi_klf", " عبدالرشيد صوفي"));
        data.add(new AuthorClass("9", "soufi", "عبدالرشيد صوفي س"));
        data.add(new AuthorClass("11", "a_ahmed", " عبدالعزيز الأحمد"));
        data.add(new AuthorClass("8", "brmi", " عبدالله البريمي"));
        data.add(new AuthorClass("10", "Abdullahk", " عبدالله الكندري"));
        data.add(new AuthorClass("8", "mtrod", " عبدالله المطرود"));
        data.add(new AuthorClass("6", "bsfr", " عبدالله بصفر"));
        data.add(new AuthorClass("12", "kyat", " عبدالله خياط"));
        data.add(new AuthorClass("13", "jhn", " عبدالله عواد الجهني"));
        data.add(new AuthorClass("6", "mohsin_harthi", " عبدالمحسن الحارثي"));
        data.add(new AuthorClass("12", "obk", " عبدالمحسن العبيكان"));
        data.add(new AuthorClass("8", "qasm", " عبدالمحسن القاسم"));
        data.add(new AuthorClass("6", "kanakeri", " عبدالهادي أحمد كناكري"));
        data.add(new AuthorClass("8", "wdod", " عبدالودود حنيف"));
        data.add(new AuthorClass("9", "abo_hashim", " علي أبو هاشم"));
        data.add(new AuthorClass("9", "huthifi_qalon", " علي الحذيفي"));
        data.add(new AuthorClass("9", "hthfi", " علي بن عبدالرحمن الحذيفي"));
        data.add(new AuthorClass("11", "a_jbr", " علي جابر"));
        data.add(new AuthorClass("9", "hajjaj", " علي حجاج السويسي"));
        data.add(new AuthorClass("6", "hafz", " عماد زهير حافظ"));
        data.add(new AuthorClass("8", "frs_a", " فارس عباد"));
        data.add(new AuthorClass("6", "lafi", " لافي العوني"));
        data.add(new AuthorClass("9", "zaml", " ماجد الزامل"));
        data.add(new AuthorClass("9", "shaibat", "مالك شيبة الحمد"));
        data.add(new AuthorClass("12", "maher", " ماهر المعيقلي ح"));
        data.add(new AuthorClass("10", "shaksh", " ماهر شخاشير"));
        data.add(new AuthorClass("8", "ayyub", " محمد أيوب"));
        data.add(new AuthorClass("13", "braak", " محمد البراك")); //**
        data.add(new AuthorClass("12", "tblawi", " محمد الطبلاوي"));
        data.add(new AuthorClass("11", "mhsny", " محمد المحيسني"));
        data.add(new AuthorClass("10", "monshed", " محمد المنشد"));
        data.add(new AuthorClass("8", "jbrl", " محمد جبريل"));
        data.add(new AuthorClass("10", "rashad", "محمد رشاد الشريف"));
        data.add(new AuthorClass("1", "shah", " محمد صالح"));
        data.add(new AuthorClass("10", "minsh", " محمد صديق المنشاوي"));//**
        data.add(new AuthorClass("11", "minsh_mjwd", "محمد صديق المنشاوي م"));
        data.add(new AuthorClass("12", "abdullah_dori", " محمد عبدالحكيم"));
        data.add(new AuthorClass("6", "khan", " محمد عثمان خان"));
        data.add(new AuthorClass("11", "mrifai", " محمود الرفاعي"));
        data.add(new AuthorClass("10", "sheimy", " محمود الشيمي"));
        data.add(new AuthorClass("13", "husr", " محمود خليل الحصري"));
        data.add(new AuthorClass("8", "bna_mjwd", " محمود علي البنا"));
        data.add(new AuthorClass("8", "afs", " مشاري العفاسي")); //**
        data.add(new AuthorClass("8", "mustafa", " مصطفى إسماعيل"));
        data.add(new AuthorClass("6", "lahoni", " مصطفى اللاهوني"));
        data.add(new AuthorClass("8", "ra3ad", " مصطفى رعد العزاوي"));
        data.add(new AuthorClass("8", "harthi", " معيض الحارثي"));
        data.add(new AuthorClass("11", "muftah_thakwan", " مفتاح السلطني"));
        data.add(new AuthorClass("11", "bilal", " موسى بلال"));
        data.add(new AuthorClass("6", "qtm", " ناصر القطامي"));
        data.add(new AuthorClass("9", "nabil", "  نبيل الرفاعي"));
        data.add(new AuthorClass("8", "namh", " نعمة الحسان"));//^^
        data.add(new AuthorClass("8", "hani", " هاني الرفاعي"));
        data.add(new AuthorClass("9", "waleed", " وليد النائحي"));
        data.add(new AuthorClass("11", "yasser", " ياسر الدوسري"));
        data.add(new AuthorClass("9", "qurashi", " ياسر القرشي"));
        data.add(new AuthorClass("9", "mzroyee", "ياسر المزروعي"));
        data.add(new AuthorClass("12", "yahya", " يحيى حوا"));
        data.add(new AuthorClass("9", "yousef", " يوسف الشويعي"));
        data.add(new AuthorClass("8", "noah", "يوسف بن نوح أحمد"));
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
