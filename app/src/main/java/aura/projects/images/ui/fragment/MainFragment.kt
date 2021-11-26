package aura.projects.images.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import aura.projects.core.BaseFragment
import aura.projects.images.databinding.FragmentMainBinding
import aura.projects.images.ui.adapter.FeedAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>() {
    override val viewModel: MainFragmentViewModel by viewModel()
    private val adapter = FeedAdapter()

    override fun initBinding(inflater: LayoutInflater): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater)

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.loadImages()
        binding.recycler.adapter = adapter
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if(!recyclerView.canScrollVertically(1)){
                    viewModel.loadImages()
                    binding.progress.visibility = View.VISIBLE
                }
            }
        })

    }

    override fun observes() {
        observe(viewModel.firstLaunch){
            binding.loading.visibility = View.VISIBLE
        }
        observe(viewModel.images) {
            adapter.submitList(it.toMutableList())
            binding.progress.visibility = View.GONE
            binding.loading.visibility = View.GONE
        }
        observe(viewModel.error){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            binding.progress.visibility = View.GONE
        }
    }
}