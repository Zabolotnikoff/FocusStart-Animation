package com.example.focusstart

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.TransitionManager

class MainActivity : AppCompatActivity() {

	private val layout: LinearLayout
		get() = findViewById(R.id.layout)

	private val button: Button
		get() = findViewById(R.id.button)

	private val textView: TextView
		get() = findViewById(R.id.textView)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		button.setOnClickListener {
			showLoading()
		}
	}

	private fun createTransition() {
		TransitionManager.beginDelayedTransition(layout)

		button.width = layout.width

		val newButton = Button(this)
		layout.addView(newButton, 1)
	}

	private fun moveButton() {
		ObjectAnimator.ofFloat(button, View.TRANSLATION_Y, 0F, -200F).apply {
			duration = 10000L
			interpolator = BounceInterpolator()
			start()
		}
	}

	private fun showLoading() {
		ValueAnimator.ofInt(0, 4).apply {
			addUpdateListener {
				val dotsCount = animatedValue as Int
				val text = "Loading" + ".".repeat(dotsCount)
				textView.text = text
			}
			interpolator = LinearInterpolator()
			repeatCount = ValueAnimator.INFINITE
			duration = 4000L
			start()
		}
	}
}