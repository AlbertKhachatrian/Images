package aura.projects.images.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import aura.projects.core.BaseFragment
import aura.projects.images.databinding.FragmentMainBinding
import aura.projects.images.ui.adapter.ImagesPagedAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment<FragmentMainBinding, MainFragmentViewModel>() {
    override val viewModel: MainFragmentViewModel by viewModel()
    private val adapter = ImagesPagedAdapter()

    override fun initBinding(inflater: LayoutInflater): FragmentMainBinding = FragmentMainBinding.inflate(inflater)

    override fun initView(savedInstanceState: Bundle?) {
        binding.recycler.adapter = adapter
    }

    override fun observes() {
        observe(viewModel.images){
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
    }
}