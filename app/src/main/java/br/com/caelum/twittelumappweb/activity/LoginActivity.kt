package br.com.caelum.twittelumappweb.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.viewmodel.UsuarioViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var usuarioViewModel: UsuarioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuarioViewModel = ViewModelProviders.of(this, ViewModelFactory).get(UsuarioViewModel::class.java)

        login_criar.setOnClickListener { usuarioViewModel.criar(usuarioDaTela()) }
        login_entrar.setOnClickListener { usuarioViewModel.logar(usuarioDaTela()) }

        usuarioViewModel.usuarioLogado().observe(this, Observer { estaLogado ->

            if (estaLogado!!) {
                trocaTela()
            }
        })
    }

    private fun trocaTela() {

        val vaiParaMain = Intent(this, MainActivity::class.java)

        startActivity(vaiParaMain)

        finish()
    }

    private fun usuarioDaTela(): Usuario {

        val nome = login_campoNome.text.toString()
        val username = login_campoUsername.text.toString()
        val senha = login_campoSenha.text.toString()

        return Usuario(nome, username, senha, null)
    }
}