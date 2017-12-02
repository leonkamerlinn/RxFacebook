package com.leon.library

import android.app.Activity
import android.os.Bundle
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject


/**
 * Created by Leon on 27.11.2017..
 */
class RxFacebook (private val activity: Activity){
    val callbackManager: CallbackManager by lazy { CallbackManager.Factory.create() }
    private val accessTokenTracker: AccessTokenTracker by lazy {
        object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken?, currentAccessToken: AccessToken) {
        
            }
        }
    }
    
    private val accessToken: PublishSubject<String> by lazy { PublishSubject.create<String>() }
    
    
    private val profileTracker: ProfileTracker by lazy {
        object : ProfileTracker() {
            override fun onCurrentProfileChanged(oldProfile: Profile?, currentProfile: Profile?) {
            
            }
        }
    }
    
    fun getAccessToken(): Observable<String> = accessToken
    
    
    fun request(requestArray: Array<String>, fieldsArray: Array<String>): Observable<FacebookUser> {
      
        return Observable.create({
        
            LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onError(error: FacebookException?) {
                    it.onError(Throwable(error?.message))
                }
        
                override fun onSuccess(result: LoginResult?) {
                    
                    if (result != null && result.accessToken != null) {
                        accessToken.onNext(result.accessToken.token)
    
                        val request = GraphRequest.newMeRequest(result.accessToken) { jsonObject, _ ->
                            val user: FacebookUser = Gson().fromJson(jsonObject.toString(), FacebookUser::class.java)
                            it.onNext(user)
                            it.onComplete()
                        }
    
                        val parameters = Bundle()
                        parameters.putString("fields", fieldsArray.implode(","))
                        request.parameters = parameters
                        request.executeAsync()
                        
                        
                    } else {
                        accessToken.onError(Throwable("Cannot get access token"))
                        it.onError(Throwable("Cannot get access token"))
                    }
                    
                   
                }
        
                override fun onCancel() {
                
                }
            })
    
            LoginManager.getInstance().logInWithReadPermissions(activity, requestArray.toList())
        })

    }

    
  
    
}