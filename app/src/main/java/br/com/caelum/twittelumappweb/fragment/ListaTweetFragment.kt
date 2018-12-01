package br.com.caelum.twittelumappweb.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.lista_tweets_fragment.view.*

class ListaTweetFragment : Fragment() {

    lateinit var viewModel: TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, ViewModelFactory).get(TweetViewModel::class.java)

        viewModel.buscaTweets()

    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.lista_tweets_fragment, container, false)

        view.swipe.setOnRefreshListener { viewModel.buscaTweets() }

        view.swipe.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE)

        viewModel.lista().observe(this, Observer { lista ->

            view.swipe.isRefreshing = false

            view.listaTweets.adapter = TweetAdapter(lista!!)

        })



        return view
    }


}