package com.leon.rxfacebook

import android.app.Activity
import android.os.Bundle
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.gson.Gson
import io.reactivex.Observable

/**
 * Created by Leon on 27.11.2017..
 */
class RxFacebook (private val activity: Activity){
    val callbackManager by lazy { CallbackManager.Factory.create() }
    private val accessTokenTracker: AccessTokenTracker by lazy {
        object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken) {
        
            }
        }
    }
    
    
    private val profileTracker: ProfileTracker by lazy {
        object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
            
            }
        }
    }
    
  


    
    fun request(requestArray: Array<String>, fieldsArray: Array<String>): Observable<FacebookUser> {
      
        return Observable.create({
        
            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onError(error: FacebookException?) {
                    it.onError(Throwable(error?.message))
                }
        
                override fun onSuccess(result: LoginResult?) {
                    val token = result!!.accessToken.token
            
                    val request = GraphRequest.newMeRequest(result.accessToken) { jsonObject, response ->
                        val user: FacebookUser = Gson().fromJson(jsonObject.toString(), FacebookUser::class.java)
                        it.onNext(user)
                        it.onComplete()
                    }
            
            
                    val parameters = Bundle()
                    parameters.putString("fields", fieldsArray.implode(","))
                    request.parameters = parameters
                    request.executeAsync()
                }
        
                override fun onCancel() {
                
                }
            })
    
            LoginManager.getInstance().logInWithReadPermissions(activity, requestArray.toList())
        })

    }

    
  
    
}