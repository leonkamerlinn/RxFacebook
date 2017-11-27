package com.leon.rxfacebook

/**
 * Created by cesarferreira on 13/10/15.
 */


fun Array<String>.implode(peace: String): String {
    var str = ""
    this.forEach {
        if (this.last() == it) {
            str += it
        } else {
            str += it + peace
        }
        
       
    }
    return str
}


