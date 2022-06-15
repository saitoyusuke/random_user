package yusuke.saito.randomuser.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import yusuke.saito.randomuser.databinding.FragmentRandomUsersBinding
import yusuke.saito.randomuser.entity.RandomUserEntity
import yusuke.saito.randomuser.repository.RandomUserRepositoryImpl
import yusuke.saito.randomuser.usecase.GetRandomUsersUseCaseImpl
import yusuke.saito.randomuser.viewmodel.RandomUsersViewModel

class RandomUserFragment : Fragment() {
    private var _viewBinding: FragmentRandomUsersBinding? = null
    private val viewBinding get() = _viewBinding!!

    // ViewModelはHiltで挿入する
    private val viewModel = RandomUsersViewModel(
        Dispatchers.IO,
        GetRandomUsersUseCaseImpl(RandomUserRepositoryImpl("https://randomuser.me/"))
    )

    private val controller = RandomUsersController(object : RandomUsersController.SelectListener {
        override fun onSelected(user: RandomUserEntity) {
            Toast.makeText(requireContext(), user.phone, Toast.LENGTH_SHORT).show()
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentRandomUsersBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()

        viewModel.getUsers()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _viewBinding = null
    }

    private fun setupView() {
        viewBinding.recyclerView.adapter = controller.adapter
    }

    private fun observeViewModel() {
        viewModel.uiModel.observe(viewLifecycleOwner) { uiModel ->
            viewBinding.progressBar.isVisible = uiModel.isLoading
            controller.setData(uiModel.users)
        }
    }
}