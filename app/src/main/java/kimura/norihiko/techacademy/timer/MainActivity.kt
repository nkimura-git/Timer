package kimura.norihiko.techacademy.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mTimer: Timer? = null
    private var mTimerSec = 0.0    // タイマー用の時間のための変数
    private var mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_button.setOnClickListener {

            if (mTimer == null) {

                mTimer = Timer()    // タイマーの作成

                mTimer!!.schedule(object : TimerTask() {    // タイマーの起動
                    override fun run() {
                        mTimerSec += 0.1
                        mHandler.post {
                            timer.text = String.format("%.1f", mTimerSec)
                        }
                    }
                }, 100, 100)    // 最初に始動させるまで100ミリ秒、ループの間隔を100ミリ秒に設定
            }
        }

        pause_button.setOnClickListener {
            if (mTimer != null) {
                mTimer!!.cancel()
                mTimer = null
            }
        }

        reset_button.setOnClickListener {
            mTimerSec = 0.0
            timer.text = String.format("%.1f",mTimerSec)
        }
    }
}
