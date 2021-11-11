package com.example.caculator;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import com.example.caculator.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        myViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(MyViewModel.class);
        //获取数据类型
        myViewModel.getMainNum().observe(this, new Observer<String>() {//事件监听
            @Override
            public void onChanged(String s) {
                binding.myTextView.setText(myViewModel.getMainNum().getValue());//让myTextView显示mainNum的数据
                //让TextView显示算子
                if (myViewModel.sign2.equals("")){
                if (myViewModel.sign1.equals("")){
                binding.textView.setText(myViewModel.getMainNum().getValue());
            }else {//a+b
                   binding.textView.setText(myViewModel.num[0]+myViewModel.sign1+myViewModel.getMainNum().getValue());
                }
            }else {
                binding.textView.setText(myViewModel.num[0]+myViewModel.sign1+myViewModel.num[1]+myViewModel.sign2+myViewModel.getMainNum().getValue());
                }
            }
        });
        //-----------------------------------------------------------------------------
        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("0");
            }
        });
        binding.button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("1");
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("2");
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("3");
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("4");
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("5");
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("6");
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("7");
            }
        });
        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("8");
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.setMainNum("9");
            }
        });
        binding.button17.setOnClickListener(new View.OnClickListener() {//小数点按钮
            @Override
            public void onClick(View v) {
                if (!myViewModel.havePoint){
                    myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue()+".");
                    myViewModel.havePoint = true;
                }
            }
        });
        binding.button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewModel.sign1.equals("")){
                    myViewModel.sign1="+";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if (myViewModel.sign2.equals("")){
                    //如果是像a+b这种形式的式子
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                    myViewModel.sign1 = "+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint =false;
                }else {
                    //a+b*c
                    myViewModel.getMainNum().setValue(myViewModel.mainNumwithNum_1_tocal());
                    myViewModel.sign2 = "";
                    myViewModel.num[1] = "";
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                    myViewModel.sign1 = "+";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        //减法按钮的监听
        binding.button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewModel.sign1.equals("")){
                    myViewModel.sign1="-";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }else if (myViewModel.sign2.equals("")){
                    //如果是像a-b这种形式的式子
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                    myViewModel.sign1 = "-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint =false;
                }else {
                    //a+b*c
                    myViewModel.getMainNum().setValue(myViewModel.mainNumwithNum_1_tocal());
                    myViewModel.sign2 = "";
                    myViewModel.num[1] = "";
                    myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                    myViewModel.sign1 = "-";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;
                }
            }
        });
        //乘法按钮的监听
        binding.button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "*";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if (myViewModel.sign2.equals("")){
                    if (myViewModel.sign1.equals("*")||myViewModel.sign1.equals("/")){
                        //按顺序进行计算
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                        myViewModel.sign1 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }else {
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "*";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                }else{
                    //如果是a+b*c这种模式
                    myViewModel.num[1] = myViewModel.mainNumwithNum_1_tocal();
                    myViewModel.sign2 = "*";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;

                }
            }
        });
        binding.button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myViewModel.sign1.equals("")){
                    myViewModel.sign1 = "/";
                    myViewModel.num[0] = myViewModel.getMainNum().getValue();
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint=false;
                }else if (myViewModel.sign2.equals("")){
                    if (myViewModel.sign1.equals("*")||myViewModel.sign1.equals("/")){
                        //按顺序进行计算
                        myViewModel.num[0] = myViewModel.mainNumWithNum_0_Total();
                        myViewModel.sign1 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }else {
                        myViewModel.num[1] = myViewModel.getMainNum().getValue();
                        myViewModel.sign2 = "/";
                        myViewModel.getMainNum().setValue("0");
                        myViewModel.havePoint = false;
                    }
                }else{
                    //如果是a+b*c这种模式
                    myViewModel.num[1] = myViewModel.mainNumwithNum_1_tocal();
                    myViewModel.sign2 = "/";
                    myViewModel.getMainNum().setValue("0");
                    myViewModel.havePoint = false;

                }
            }
        });
        binding.button15.setOnClickListener(new View.OnClickListener() {//清零
            @Override
            public void onClick(View v) {
                myViewModel.sign2="";
                myViewModel.num[1] = "";
                myViewModel.sign1 = "";
                myViewModel.num[0] = "";
                myViewModel.getMainNum().setValue("0");
                myViewModel.havePoint = false;
            }
        });
        binding.button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //计算功能
                if (myViewModel.sign2.equals("")) {
                    if (!myViewModel.sign1.equals("")){
                        myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Total());
                        if (myViewModel.getMainNum().getValue().contains("."))
                            myViewModel.havePoint =true;
                        else
                            myViewModel.havePoint = false;
                        myViewModel.num[0] = "";
                        myViewModel.sign1="";
                    }
                }else {
                    //如果a+b*c
                    myViewModel.getMainNum().setValue(myViewModel.mainNumwithNum_1_tocal());
                    myViewModel.num[1] = "";
                    myViewModel.sign2 = "";
                    myViewModel.getMainNum().setValue(myViewModel.mainNumWithNum_0_Total());
                    if (myViewModel.getMainNum().getValue().contains("."))
                        myViewModel.havePoint =true;
                    else
                        myViewModel.havePoint = false;
                    myViewModel.num[0] = "";
                    myViewModel.sign1="";
                }
                binding.textView.setText(myViewModel.getMainNum().getValue());
            }
        });
        binding.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myViewModel.getMainNum().getValue().equals("")){
                   myViewModel.getMainNum().setValue(myViewModel.getMainNum().getValue().substring(0,myViewModel.getMainNum().getValue().length()-1));
                    if (myViewModel.getMainNum().getValue().equals("")){
                        myViewModel.getMainNum().setValue("0");
                    }
                }
            }
        });
    }
}