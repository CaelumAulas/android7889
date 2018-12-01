package br.com.caelum.twittelumappweb.gps

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class GPS(context: Context) : LocationCallback() {

    private val client = LocationServices
            .getFusedLocationProviderClient(context)

    private var ultimaLocalizacao: Location? = null

    fun iniciaBusca() {
        val request = LocationRequest()

        request.apply {
            smallestDisplacement = 10.0F
            interval = 1000 * 60
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        client.requestLocationUpdates(request, this, null)
    }

    override fun onLocationResult(result: LocationResult) {
        ultimaLocalizacao = result.lastLocation
    }

    fun getCoordenada(): Pair<Double, Double> {
        return Pair(ultimaLocalizacao?.latitude ?: 0.0,
                ultimaLocalizacao?.longitude ?: 0.0)
    }
}