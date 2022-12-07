package com.example.german2021goracing

import android.app.Activity
import android.widget.TextView
import android.annotation.SuppressLint
import android.os.Bundle
import com.example.german2021goracing.R
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : Activity(), View.OnClickListener {
    var twresult: TextView? = null
    var btdrive: Button? = null
    var btstart: Button? = null
    var btretry: Button? = null
    var iwplayercar: ImageView? = null
    var iwenemycar: ImageView? = null
    var playerY = 1418f
    var enemyY = 1418f
    @SuppressLint("MissingInflatedId")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        twresult = findViewById<View>(R.id.twresult) as TextView
        btdrive = findViewById<View>(R.id.btdrive) as Button
        btstart = findViewById<View>(R.id.btstart) as Button
        btretry = findViewById<View>(R.id.btretry) as Button
        iwplayercar = findViewById<View>(R.id.iwplayercar) as ImageView
        iwenemycar = findViewById<View>(R.id.iwenemycar) as ImageView
        btdrive!!.setOnClickListener(this)
        btstart!!.setOnClickListener(this)
        btretry!!.setOnClickListener(this)
        btdrive!!.visibility = View.INVISIBLE
        btretry!!.visibility = View.INVISIBLE
        twresult!!.visibility = View.INVISIBLE
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btstart -> {
                btstart!!.visibility = View.INVISIBLE
                twresult!!.visibility = View.VISIBLE
                iwplayercar!!.y = playerY
                iwenemycar!!.y = enemyY
                object : CountDownTimer(3000, 1000) {
                    override fun onTick(l: Long) {
                        twresult!!.text = java.lang.Long.toString(1 + l / 1000)
                    }

                    override fun onFinish() {
                        twresult!!.text = "GO!"
                        btdrive!!.visibility = View.VISIBLE
                    }
                }.start()
                object : CountDownTimer(14000, 500) {
                    override fun onTick(l: Long) {
                        if (enemyY != 318f) {
                            if (l <= 11000) {
                                enemyY = enemyY - 50
                                iwenemycar!!.y = enemyY
                            }
                        }
                    }

                    override fun onFinish() {
                        if (twresult!!.text !== "You've won!") {
                            twresult!!.text = "You've lost!"
                        }
                        btretry!!.visibility = View.VISIBLE
                    }
                }.start()
            }
            R.id.btdrive -> {
                playerY = playerY - 50
                iwplayercar!!.y = playerY
                if (playerY == 318f) {
                    if (twresult!!.text !== "You've lost!") {
                        twresult!!.text = "You've won!"
                    }
                    btdrive!!.visibility = View.INVISIBLE
                    btretry!!.visibility = View.VISIBLE
                }
            }
            R.id.btretry -> {
                btretry!!.visibility = View.INVISIBLE
                btstart!!.visibility = View.VISIBLE
                playerY = 1418f
                enemyY = 1418f
                iwplayercar!!.y = playerY
                iwenemycar!!.y = enemyY
                twresult!!.text = ""
            }
        }
    }
}