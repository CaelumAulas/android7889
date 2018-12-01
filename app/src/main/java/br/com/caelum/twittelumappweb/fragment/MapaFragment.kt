package br.com.caelum.twittelumappweb.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaFragment : SupportMapFragment() {

    private lateinit var tweetViewModel: TweetViewModel

    override fun onCreate(p0: Bundle?) {
        super.onCreate(p0)
        tweetViewModel = ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()

        getMapAsync { googleMap ->

            tweetViewModel.lista().observe(this, Observer { tweets ->


                tweets?.forEach { tweet ->

                    val marcador = MarkerOptions()

                    val latLng = LatLng(tweet.latitude, tweet.longitude)

                    marcador.apply {
                        title(tweet.dono.nome)
                        snippet(tweet.conteudo)
                        position(latLng)
                    }
                    googleMap.addMarker(marcador)
                }

            })


        }
    }
}