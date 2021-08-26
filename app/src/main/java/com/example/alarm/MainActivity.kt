package com.example.alarm

import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

/*
 step1 View를 초기화해주기
 step2 데이터 가져오기
 step3 View에 데이터 뿌려주기
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initOnOffButton()
        initChangeAlarmTimeButton()


    }

    private fun initOnOffButton() {
        val onOffButton = findViewById<Button>(R.id.onOffButton)
        onOffButton.setOnClickListener {
            // 데이터를 확인
            // On/Off 에 따라 작업을 처리 Off -> 제거, On -> 알람을 등록

        }
    }

     /*1. 현재시간을 가져온다
     2. TimePickDialog 띄워줘서 시간을 설정을 하도록 하게끔 하고, 그 시간을 가져와서
     3. 데이터를 저장한다.
     4. View를 업데이트한다.
     5. 기존에 있던 알람을 삭제한다.*/
    private fun initChangeAlarmTimeButton() {
        val changeAlarmButton = findViewById<Button>(R.id.changeAlarmTimeButton)
        changeAlarmButton.setOnClickListener {

            val calendar = Calendar.getInstance()
            TimePickerDialog(this, { picker, hour, minute ->

                val model = saveAlarmModel(hour, minute, false)
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false )
                .show()
        }
    }

    private fun saveAlarmModel(hour: Int, minute: Int, onOff: Boolean): AlarmDisplayModel {
        val model = AlarmDisplayModel(
            hour = hour,
            minute = minute,
            onOff = false
        )

        val sharedPreference = getSharedPreferences("time", Context.MODE_PRIVATE)

        with(sharedPreference.edit()) {
            putString("alarm",model.makeDataForDB())
            putBoolean("onOff",model.onOff)
            commit() // or apply()
        }
        return model
    }
}