# RxFacebook
```xml
<uses-permission android:name="android.permission.INTERNET"/>
	
<application>

  <activity
    android:name="com.facebook.FacebookActivity"
    android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
    android:label="@string/app_name" />

  <meta-data
    android:name="com.facebook.sdk.ApplicationId"
    android:value="@string/facebook_app_id"/>

</application>
```

```xml
<resources>
	<string name="facebook_app_id">134300883957443</string>
	<string name="fb_login_protocol_scheme">fb134300883957443</string>
</resources>
```

```kotlin
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
```
