package Controller

import Fragments.HomeFragment
import Fragments.NotificationsFragment
import Fragments.ProfileFragment
import Fragments.SearchFragment
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ninjas.instagram.myapplication.R

class MainActivity : AppCompatActivity() {

    internal var selectedFragment: Fragment? = null


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {

                selectedFragment = HomeFragment()

            }
            R.id.nav_search -> {

                selectedFragment = SearchFragment()


            }
            R.id.nav_add_post -> {


                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_notifications -> {

                selectedFragment = NotificationsFragment()

            }
            R.id.nav_profile -> {

                selectedFragment = ProfileFragment()

            }
        }

        if(selectedFragment != null){

            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,

                selectedFragment!!

            ).commit()


        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)



    }
}