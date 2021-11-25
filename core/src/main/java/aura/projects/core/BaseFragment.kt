package aura.projects.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<ViewBind : ViewBinding, ViewModel: BaseViewModel> : Fragment() {

    protected lateinit var binding: ViewBind

    protected abstract val viewModel: ViewModel

    private lateinit var navController: NavController

    protected abstract fun initBinding(inflater: LayoutInflater): ViewBind

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = initBinding(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        observes()
        initView(savedInstanceState)
        initViewClickListeners()
    }

    protected fun <T> observe(liveData: LiveData<T>, action: (T) -> Unit) = view?.run {
        if (!this@BaseFragment.isAdded) return@run
        liveData.observe(viewLifecycleOwner, Observer { action(it ?: return@Observer) })
    }



    protected abstract fun initView(savedInstanceState: Bundle?)

    protected open fun initViewClickListeners() {}

    protected open fun observes() {}

    protected fun navigateFragment(
        destinationId: Int,
        bundle: Bundle? = null,
    ) {
        navController.navigate(destinationId, bundle)
    }


}