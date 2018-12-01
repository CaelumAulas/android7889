package br.com.caelum.twittelumappweb.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.caelum.twittelumappweb.R
import br.com.caelum.twittelumappweb.fragment.BuscaTweetsFragment
import br.com.caelum.twittelumappweb.fragment.ListaTweetFragment
import br.com.caelum.twittelumappweb.fragment.MapaFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listenerBottomNavigation()

        fab.setOnClickListener { startActivity(Intent(this, TweetActivity::class.java)) }

    }

    private fun listenerBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomLista -> {
                    exibe(ListaTweetFragment())
                    true
                }
                R.id.bottomBusca -> {
                    exibe(BuscaTweetsFragment())
                    true
                }
                R.id.bottomMapa -> {
                    exibe(MapaFragment())
                    true
                }
                else -> {
                    true
                }
            }
        }

        bottomNavigation.selectedItemId = R.id.bottomLista
    }

    private fun exibe(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()

        transaction.replace(R.id.frame, fragment)

        transaction.commit()

    }
}
