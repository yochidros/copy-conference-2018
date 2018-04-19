package com.yochio.copy_conference2018.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.yochio.copy_conference2018.App
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector
import dagger.android.support.AndroidSupportInjection

/**
 * Created by yochio on 2018/03/05.
 */


class AppInjector {
    companion object {
        fun init(app: App) {
            DaggerAppComponent.builder()
                    .application(app)
                    .networkModule(NetworkModule.instance)
                    .databaseModule(DatabaseModule.instance)
                    .build().inject(app)
            app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityDestroyed(activity: Activity?) {
                }

                override fun onActivityPaused(activity: Activity?) {
                }

                override fun onActivityResumed(activity: Activity?) {
                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                }

                override fun onActivityStarted(activity: Activity?) {
                }

                override fun onActivityStopped(activity: Activity?) {
                }
            })
        }

        private fun handleActivity(activity: Activity) {
            if (activity is HasSupportFragmentInjector) {
                AndroidInjection.inject(activity)
            }

            val fragmentActivity = activity as? FragmentActivity
            fragmentActivity?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                    object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
                            if ( f is Injectable) {
                                AndroidSupportInjection.inject(f)
                            }
                        }
                    }, true)
        }
    }
}