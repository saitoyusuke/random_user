package yusuke.saito.randomuser.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.Dispatchers
import yusuke.saito.randomuser.R
import yusuke.saito.randomuser.repository.RandomUserRepositoryImpl
import yusuke.saito.randomuser.usecase.GetRandomUsersUseCaseImpl
import yusuke.saito.randomuser.viewmodel.RandomUsersViewModel

class MainActivity : AppCompatActivity() {
    // ViewModelはHiltで挿入する
    private val viewModel = RandomUsersViewModel(
        Dispatchers.IO,
        GetRandomUsersUseCaseImpl(RandomUserRepositoryImpl("https://randomuser.me/"))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        viewModel.uiModel.observe(this) { uiModel ->
            findViewById<TextView>(R.id.text_view).text = uiModel.users.toString()
            findViewById<LinearProgressIndicator>(R.id.progress_bar).isVisible = uiModel.isLoading
        }
        return super.onCreateView(name, context, attrs)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUsers()
    }
}
