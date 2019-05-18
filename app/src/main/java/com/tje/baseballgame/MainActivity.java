package com.tje.baseballgame;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tje.baseballgame.databinding.ActivityMainBinding;
import com.tje.baseballgame.datas.Chat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ActivityMainBinding act;

    int[] computerExamArray = new int[3]; // 741 = 7,4,1

    List<Chat> chatList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        act.inputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chatList.add(new Chat(true, act.userInputEdt.getText().toString()));

                checkStrikeAndBalls();
            }
        });

    }

    void checkStrikeAndBalls() {

        int[] userInputArray = new int[3];
        int number = Integer.parseInt(act.userInputEdt.getText().toString());

        userInputArray[0] = number / 100;
        userInputArray[1] = number / 10 % 10;
        userInputArray[2] = number % 10;

        int strikeCount = 0;
        int ballCount = 0;

        for (int i=0; i < 3; i++) {
            for (int j=0; j < 3; j++) {

                if (userInputArray[i] == computerExamArray[j]) {
                    if (i == j) {
                        strikeCount++;
                    }
                    else {
                        ballCount++;
                    }
                }

            }
        }

        if (strikeCount == 3) {
//            Toast.makeText(mContext, "정답입니다! 축하합니다!", Toast.LENGTH_SHORT).show();

            chatList.add(new Chat(false, "정답입니다! 축하합니다!!"));
        }
        else {
//            Toast.makeText(mContext, String.format("%d S, %d B 입니다.", strikeCount, ballCount), Toast.LENGTH_SHORT).show();

            chatList.add(new Chat(false, String.format("%d S %d B 입니다.", strikeCount, ballCount)));
        }

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

//                중복도 없고, 0도 없는 경우. 문제를 출제하고 반복문을 빠져나간다.
                computerExamArray[0] = tempNumber[0];
                computerExamArray[1] = tempNumber[1];
                computerExamArray[2] = tempNumber[2];

                Log.d("정답 숫자 :", randomNum + "입니다.");

                break;
            }

        }

    }

    @Override
    public void bindViews() {

        act = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}
