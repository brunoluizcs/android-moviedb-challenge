package com.arctouch.codechallenge.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arctouch.codechallenge.R

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        /*
        api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                br.com.fiap.mob18.data.remote.cache.Cache.cacheGenres(it.genres)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            },{

            }
            )

         */
    }
}
