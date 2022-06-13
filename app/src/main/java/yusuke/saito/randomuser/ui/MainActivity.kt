package yusuke.saito.randomuser.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.Dispatchers
import yusuke.saito.randomuser.R
import yusuke.saito.randomuser.entity.RandomUserEntity
import yusuke.saito.randomuser.repository.RandomUserRepositoryImpl
import yusuke.saito.randomuser.usecase.GetRandomUsersUseCaseImpl
import yusuke.saito.randomuser.viewmodel.RandomUsersViewModel

class MainActivity : AppCompatActivity() {
    // ViewModelはHiltで挿入する
    private val viewModel = RandomUsersViewModel(
        Dispatchers.IO,
        GetRandomUsersUseCaseImpl(RandomUserRepositoryImpl("https://randomuser.me/"))
    )

    private val controller = RandomUsersController(object : RandomUsersController.SelectListener {
        override fun onSelected(user: RandomUserEntity) {
            Toast.makeText(this@MainActivity, user.phone, Toast.LENGTH_SHORT).show()
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<RecyclerView>(R.id.recycler_view).adapter = controller.adapter
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        viewModel.uiModel.observe(this) { uiModel ->
            findViewById<LinearProgressIndicator>(R.id.progress_bar).isVisible = uiModel.isLoading
            controller.setData(uiModel.users)
        }
        return super.onCreateView(name, context, attrs)
    }
    override fun onResume() {
        super.onResume()
        viewModel.getUsers()
    }
}
