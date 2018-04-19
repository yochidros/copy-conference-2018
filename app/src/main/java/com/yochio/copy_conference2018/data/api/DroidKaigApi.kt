package com.yochio.copy_conference2018.data.api

import android.support.annotation.CheckResult
import com.yochio.copy_conference2018.data.api.response.Response
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

/**
 * Created by yochio on 2018/03/06.
 */

@Singleton
interface DroidKaigApi {
    @GET("sessionize/all.json")
    fun getSessions(): Single<Response>

}