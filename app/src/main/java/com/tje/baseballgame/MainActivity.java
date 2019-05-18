package com.tje.baseballgame;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tje.baseballgame.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;

    int[] computerExamArray = new int[3]; // 741 = 7,4,1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        makeExam();

    }

    void makeExam() {

        while (true) {
            int randomNum = (int) (Math.random() * 899 + 100);

//        각각의 자리수를 쪼개서 배열에 나눠 담아보기
            int[] tempNumber = new int[3];

            tempNumber[0] = randomNum / 100;
            tempNumber[1] = randomNum / 10 % 10;
            tempNumber[2] = randomNum % 10;

            boolean isDuplicate = true;

            if (tempNumber[0] == tempNumber[1] || tempNumber[1] == tempNumber[2] || tempNumber[0] == tempNumber[2]) {
//            중복인 경우. 문제를 다시 출제함. 랜덤을 다시
                isDuplicate = false;
            }

            boolean isZeroOk = true;

            if (tempNumber[0] == 0 || tempNumber[1] == 0 || tempNumber[2] == 0) {
//            0을 사용하고 있는 경우. 문제 다시 출제. 랜덤 다시
                isZeroOk = false;
            }

            if (isDuplicate && isZeroOk) {
                computerExamArray[0] = tempNumber[0];
                computerExamArray[1] = tempNumber[1];
                computerExamArray[2] = tempNumber[2];

                break;
            }

        }

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}
