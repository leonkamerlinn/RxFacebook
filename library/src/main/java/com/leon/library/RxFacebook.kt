package com.leon.library

import android.app.Activity
import android.os.Bundle
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


/**
 * Created by Leon on 27.11.2017..
 */
class RxFacebook (private val activity: Activity){
    val callbackManager: CallbackManager by lazy { CallbackManager.Factory.create() }
    private val accessTokenSubject: PublishSubject<String> by lazy { PublishSubject.create<String>() }
    private val userSubject: PublishSubject<FacebookUser> by lazy { PublishSubject.create<FacebookUser>() }
    val accessTokenObserver: Observable<String> by lazy { accessTokenSubject }
    val userObserver: Observable<FacebookUser> by lazy { userSubject }
    
    
    
    private val profileTracker: ProfileTracker by lazy {
        object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
            
            }
        }
    }
    
    private val accessTokenTracker: AccessTokenTracker by lazy {
        object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken) {
            
            }
        }
    }
    
    
    
    fun request(requestArray: Array<String>, fieldsArray: Array<String>) {
      
        
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onError(error: FacebookException?) {
                userSubject.onError(Throwable(error?.message))
            }
    
            override fun onSuccess(result: LoginResult?) {
                
                if (result != null && result.accessToken != null) {
                    accessTokenSubject.onNext(result.accessToken.token)

                    val request = GraphRequest.newMeRequest(result.accessToken) { jsonObject, _ ->
                        val user: FacebookUser = Gson().fromJson(jsonObject.toString(), FacebookUser::class.java)
                        userSubject.onNext(user)
                        userSubject.onComplete()
                    }

                    val parameters = Bundle()
                    parameters.putString("fields", fieldsArray.implode(","))
                    request.parameters = parameters
                    request.executeAsync()
                    
                    
                } else {
                    accessTokenSubject.onError(Throwable("Cannot get access token"))
                    userSubject.onError(Throwable("Cannot get access token"))
                }
                
               
            }
    
            override fun onCancel() {
            
            }
        })

        LoginManager.getInstance().logInWithReadPermissions(activity, requestArray.toList())
        

    }

    
  
    
}