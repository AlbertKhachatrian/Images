package aura.projects.images.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import aura.projects.core.BaseViewModel
import aura.projects.core.network.ActionResult
import aura.projects.domain.interactor.GetImagesInteractor
import aura.projects.domain.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getImagesInteractor: GetImagesInteractor
): BaseViewModel() {

    private val _images by lazy { MutableLiveData<List<Image>>() }
    val images: LiveData<List<Image>> get() = _images

    private val _error by lazy { MutableLiveData<String>() }
    val error: LiveData<String> get() = _error

    fun loadImages(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val data = getImagesInteractor()){
                is ActionResult.Success -> {
                    _images.postValue(data.data)
                }
                is ActionResult.Error -> {
                    _error.postValue(data.errors.message)
                }
            }
        }
    }


}