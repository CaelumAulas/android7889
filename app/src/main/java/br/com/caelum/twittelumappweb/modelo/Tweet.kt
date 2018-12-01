package br.com.caelum.twittelumappweb.modelo

data class Tweet(val conteudo: String,
                 val dono: Usuario,
                 val latitude: Double,
                 val longitude: Double,
                 val foto: String?) {

    constructor() : this("", Usuario(), 0.0, 0.0, null)

    override fun toString(): String {
        return conteudo
    }

}