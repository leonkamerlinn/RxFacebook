package com.leon.rxfacebook

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    
    val facebookLogin by lazy { findViewById<Button>(R.id.facebookLogin) }
    val rxFacebook by lazy { RxFacebook(this) }


    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     
 
        facebookLogin.setOnClickListener {
            rxFacebook.request(
                arrayOf(Request.EMAIL, Request.PUBLIC_PROFILE),
                arrayOf(Fields.EMAIL, Fields.BIRTHDAY, Fields.GENDER)
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { Toast.makeText(this, it.email, Toast.LENGTH_LONG).show() }
        }
    }
    

    

    
    
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        rxFacebook.callbackManager.onActivityResult(requestCode, resultCode, data)
    }
    
    
}
