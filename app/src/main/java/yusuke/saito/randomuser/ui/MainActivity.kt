package yusuke.saito.randomuser.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.progressindicator.LinearProgressIndicator
import yusuke.saito.randomuser.R
import yusuke.saito.randomuser.repository.RandomUserRepositoryImpl
import yusuke.saito.randomuser.usecase.GetRandomUsersUseCaseImpl
import yusuke.saito.randomuser.viewmodel.RandomUserViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel = RandomUserViewModel(
        GetRandomUsersUseCaseImpl(RandomUserRepositoryImpl("https://randomuser.me/"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        viewModel.results.observe(this) {
            findViewById<TextView>(R.id.text_view).text = it
        }
        viewModel.isLoading.observe(this) {
            findViewById<LinearProgressIndicator>(R.id.progress_bar).isVisible = it
        }
        return super.onCreateView(name, context, attrs)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getResults()
    }
}
