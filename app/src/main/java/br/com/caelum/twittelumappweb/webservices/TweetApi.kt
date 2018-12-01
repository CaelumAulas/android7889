package br.com.caelum.twittelumappweb.webservices

import br.com.caelum.twittelumappweb.modelo.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class TweetApi(private val retrofit: Retrofit) {

    private val tweetService = retrofit.create(TweetService::class.java)

    fun buscaTweets(sucesso: (List<Tweet>) -> Unit) {
        tweetService.buscaTweets().enqueue(object : Callback<List<Tweet>?> {
            override fun onFailure(call: Call<List<Tweet>?>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Tweet>?>, response: Response<List<Tweet>?>) {
                response.body()?.let(sucesso)
            }
        })
    }

    fun salva(tweet: Tweet) {

        tweetService.salva(tweet).enqueue(object : Callback<String?> {
            override fun onFailure(call: Call<String?>, t: Throwable) {

            }

            override fun onResponse(call: Call<String?>, response: Response<String?>) {
            }
        })

    }

    private interface TweetService {

        @GET("tweet")
        fun buscaTweets(): Call<List<Tweet>>


        @POST("/tweet")
        fun salva(@Body tweet: Tweet): Call<String>
    }
}