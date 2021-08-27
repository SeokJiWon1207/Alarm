package com.example.alarm

data class AlarmDisplayModel(
    val hour: Int,
    val minute: Int,
    var onOff: Boolean
) {
    // 위에서 받은 인자 hour를 getter를 통해 조건에 의해 다시 반환함
    val timeText: String
        get() {
            val h = "%02d".format(if (hour < 12) hour else hour - 12) // 앞자리 두자리로 포맷
            val m = "%02d".format(minute) // 분 단위는 그대로 format

            return "$h:$m"
        }

    // View의 AM/PM을 구분하기 위해 다시 return
    val ampmText: String
        get() {
            return if (hour < 12) "AM" else "PM"
        }

    val onOffText: String
        get() {
            return if (onOff) "알람 끄기" else "알람 켜기"
        }
    fun makeDataForDB() = "$hour:$minute"
}
