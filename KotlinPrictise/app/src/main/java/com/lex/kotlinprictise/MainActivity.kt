package com.lex.kotlinprictise

import android.app.Activity
import android.os.Bundle
import android.view.View




/**
 * Created by Lex lex on 2017/6/1.
 */

class MainActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
    }

    override fun onClick(v: View) {

    }
}
