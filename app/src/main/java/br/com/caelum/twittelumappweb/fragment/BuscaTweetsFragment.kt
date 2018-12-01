package br.com.caelum.twittelumappweb.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.adapter.TweetAdapter
import br.com.caelum.twittelumappweb.modelo.Tweet
import br.com.caelum.twittelumappweb.viewmodel.TweetViewModel
import br.com.caelum.twittelumappweb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.lista_tweets_fragment.*

class BuscaTweetsFragment : Fragment() {

    lateinit var viewModel: TweetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = ViewModelProviders.of(activity!!, ViewModelFactory).get(TweetViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.lista_tweets_fragment, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_buscador, menu)

        val botaoBusca: MenuItem? = menu?.findItem(R.id.menu_busca_tweets)

        val viewSearch = botaoBusca?.actionView as SearchView


        viewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(texto: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(texto: String?): Boolean {

                val tweetsFiltrados = filtraTweetPelo(texto)

                listaTweets?.adapter = TweetAdapter(tweetsFiltrados)

                return true
            }
        })
    }

    private fun filtraTweetPelo(texto: String?): ArrayList<Tweet> {

        val lista = ArrayList<Tweet>()

        val tweets = viewModel.lista().value

        if (!texto.isNullOrEmpty()) {
            val tweetsFiltrados = tweets!!.filter { tweet -> tweet.conteudo.contains(texto!!, true) }
            lista.addAll(tweetsFiltrados)
        }

        return lista
    }
}