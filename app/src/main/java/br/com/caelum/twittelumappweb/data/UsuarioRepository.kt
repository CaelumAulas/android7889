package br.com.caelum.twittelumappweb.data

import android.arch.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.webservices.UsuarioApi

class UsuarioRepository(val api: UsuarioApi) {

    val estaLogado = MutableLiveData<Boolean>()

    var usuarioLogado: Usuario = Usuario()

    fun logar(usuario: Usuario) {
        api.loga(usuario, sucesso())
    }

    fun criar(usuario: Usuario) {
        api.cria(usuario, sucesso())
    }

    private val sucesso = {
        { usuario: Usuario ->
            usuarioLogado = usuario
            estaLogado.postValue(true)
        }
    }


}