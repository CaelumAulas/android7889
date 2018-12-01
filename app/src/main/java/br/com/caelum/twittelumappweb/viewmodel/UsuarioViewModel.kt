package br.com.caelum.twittelumappweb.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelumappweb.data.UsuarioRepository
import br.com.caelum.twittelumappweb.modelo.Usuario

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    fun usuarioLogado() = repository.estaLogado

    fun logar(usuario: Usuario) = repository.logar(usuario)

    fun criar(usuario: Usuario) = repository.criar(usuario)

    fun usuario(): Usuario = repository.usuarioLogado

}