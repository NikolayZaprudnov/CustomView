package ru.netology.statsview

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.netology.statsview.ui.StatsView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<StatsView>(R.id.statsView)
//        view.data = 80F
            view.data = listOf(
            500F,
            500F,
            500F,
            500F
        )

       val textView = findViewById<TextView>(R.id.label)
//        view.startAnimation(
//            AnimationUtils.loadAnimation(this, R.anim.animation).apply {
//                setAnimationListener(object: Animation.AnimationListener{
//                    override fun onAnimationStart(animation: Animation?) {
//                        textView.setText("onAnimationStart")
//                    }
//
//                    override fun onAnimationEnd(animation: Animation?) {
//                        textView.setText("onAnimationEnd")
//                    }
//
//                    override fun onAnimationRepeat(animation: Animation?) {
//                        textView.setText("onAnimationRepeat")
//                    }
//
//                })
//            }
//        )
    }
}