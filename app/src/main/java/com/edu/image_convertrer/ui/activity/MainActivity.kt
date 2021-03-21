package com.edu.imageconverter.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edu.imageconverter.*
import com.edu.imageconverter.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        presenter = MainPresenter(MainModel(applicationContext), this)

        setSupportActionBar(findViewById(R.id.toolbar))
        vb?.button?.setOnClickListener { presenter.buttonClicked() }

    }

    override fun showSnackbar(text: String) {
        vb?.constraintLayout?.let { it1 ->
            Snackbar.make(it1 , text,
                Snackbar.LENGTH_SHORT).show()
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        //Log.d(TAG, "onDestroy()")
    }
}
