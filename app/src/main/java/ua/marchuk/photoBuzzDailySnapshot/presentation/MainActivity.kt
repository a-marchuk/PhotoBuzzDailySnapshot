package ua.marchuk.photoBuzzDailySnapshot.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ua.marchuk.photoBuzzDailySnapshot.presentation.mainFragment.MainFragment
import ua.marchuk.photobuzz_dailyshapshot.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager


        if (savedInstanceState == null) {
            val transaction = fragmentManager.beginTransaction()
            val mainFragment = MainFragment()
            transaction.replace(R.id.fragment_container_main, mainFragment)
            transaction.commit()
        }
    }
}
