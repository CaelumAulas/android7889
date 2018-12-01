package br.com.caelum.twittelumappweb.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.caelum.twittelumappweb.data.TweetRepository
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.webservices.InicializadorDoRetrofit
import br.com.caelum.twittelumappweb.webservices.TweetApi
import br.com.caelum.twittelumappweb.webservices.UsuarioApi

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private fun getRetrofit() = InicializadorDoRetrofit.retrofit

    private fun usuarioApi() = UsuarioApi(getRetrofit())
    private fun tweetApi() = TweetApi(getRetrofit())

    private fun tweetRepository() = TweetRepository(tweetApi())
    private fun usuarioRepository() = UsuarioRepository(usuarioApi())

    private val tweetVM = TweetViewModel(tweetRepository())
    private val usuarioVM = UsuarioViewModel(usuarioRepository())

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == TweetViewModel::class.java) {
            tweetVM as T
        } else {
            usuarioVM as T
        }
    }

}