package br.com.caelum.twittelumappweb.modelo

class Usuario(val nome: String,
              val username: String,
              val senha: String,
              val foto: String?,
              val id: Int = 0) {

    constructor() : this("", "", "", null)
}