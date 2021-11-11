package com.example.caculator;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
//储存一些数据/
public class MyViewModel extends ViewModel {
        public String num[] = {"",""};//存储参与计算的数值
        private MutableLiveData<String> mainNum;//主数值，用户正在操作的数
        public boolean havePoint = false;//主数值中是否有小数点
        public String sign1 = "";//储存符号
        public String sign2 = "";

        public MutableLiveData<String> getMainNum() {
            if (mainNum == null){
                mainNum = new MutableLiveData<>();
                mainNum.setValue("0");
            }
            return mainNum;
        }
        public void setMainNum(String str){
            if(mainNum.getValue().equals("0")){
                mainNum.setValue(str);
            }else {
                mainNum.setValue(mainNum.getValue()+str);
            }
        }
        public String mainNumWithNum_0_Total(){
            String value = "0";
            if(mainNum.getValue().contains(".")||num[0].contains(".")){
                //如果两个数其中有一个有小数点？
                switch (sign1){
                    case "+":
                        value = String.valueOf(Double.valueOf(num[0])+Double.valueOf(mainNum.getValue()));
                        break;
                    case "-":
                        value = String.valueOf(Double.valueOf(num[0])-Double.valueOf(mainNum.getValue()));
                        break;
                    case "*":
                        value = String.valueOf(Double.valueOf(num[0])*Double.valueOf(mainNum.getValue()));
                        break;
                    case "/":
                        if (mainNum.getValue().equals("0")){
                            mainNum.setValue("1");
                        }else {
                            value = String.valueOf(Double.valueOf(num[0])/Double.valueOf(mainNum.getValue()));
                        }
                }
            } else {//如果两个数都是整数？
                switch (sign1) {
                    case "+":
                        value = String.valueOf(Integer.valueOf(num[0]) + Integer.valueOf(mainNum.getValue()));
                        break;
                    case "-":
                        value = String.valueOf(Integer.valueOf(num[0]) - Integer.valueOf(mainNum.getValue()));
                        break;
                    case "*":
                        value = String.valueOf(Integer.valueOf(num[0]) * Integer.valueOf(mainNum.getValue()));
                        break;
                    case "/":
                        if (mainNum.getValue().equals("0")) {
                            mainNum.setValue("1");
                        } else {
                            value = String.valueOf(Double.valueOf(num[0]) / Double.valueOf(mainNum.getValue()));
                        }
                }
            }
            return value;
        }
        public String mainNumwithNum_1_tocal(){//a+b*c
            String value = "0";
            if(mainNum.getValue().contains(".")||num[1].contains(".")){
                //如果两个数其中有一个有小数点？
                switch (sign2){
                    case "*":
                        value = String.valueOf(Double.valueOf(num[1])*Double.valueOf(mainNum.getValue()));
                        break;
                    case "/":
                        if (mainNum.getValue().equals("0")){
                            mainNum.setValue("1");
                        }else {
                            value = String.valueOf(Double.valueOf(num[1])/Double.valueOf(mainNum.getValue()));
                        }
                }
            } else {//如果两个数都是整数？
                switch (sign2) {
                    case "*":
                        value = String.valueOf(Integer.valueOf(num[1]) * Integer.valueOf(mainNum.getValue()));
                        break;
                    case "/":
                        if (mainNum.getValue().equals("0")) {
                            mainNum.setValue("1");
                        } else {
                            value = String.valueOf(Double.valueOf(num[1]) / Double.valueOf(mainNum.getValue()));
                        }
                }
            }
            return value;
        }
}
