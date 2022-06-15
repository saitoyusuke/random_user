package yusuke.saito.randomuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import yusuke.saito.randomuser.R
import yusuke.saito.randomuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        displayFragment()
    }

    private fun displayFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            RandomUserFragment(),
        ).commit()
    }
}
