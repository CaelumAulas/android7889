package br.com.caelum.twittelumappweb.webservices

import br.com.caelum.twittelumappweb.modelo.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

class UsuarioApi(private val retrofit: Retrofit) {

    private val service = retrofit.create(UsuarioService::class.java)

    fun cria(usuario: Usuario, sucesso: (Usuario) -> Unit) {

        val chamadaProServidor = service.cria(usuario)


        chamadaProServidor.enqueue(object : Callback<Usuario?> {
            override fun onFailure(call: Call<Usuario?>, t: Throwable) {
            }

            override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {
                response.body()?.let(sucesso)
            }
        })
    }

    fun loga(usuario: Usuario, sucesso: (Usuario) -> Unit) {

        service.loga(usuario).enqueue(object : Callback<Usuario?> {
            override fun onFailure(call: Call<Usuario?>, t: Throwable) {

            }

            override fun onResponse(call: Call<Usuario?>, response: Response<Usuario?>) {
                response.body()?.let(sucesso)
            }
        })
    }


    private interface UsuarioService {
        @POST("usuario/")
        fun cria(@Body usuario: Usuario): Call<Usuario>

        @POST("usuario/login")
        fun loga(@Body usuario: Usuario): Call<Usuario>

    }
}