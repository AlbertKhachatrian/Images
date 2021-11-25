package aura.projects.images.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import aura.projects.core.BaseViewModel
import aura.projects.domain.interactor.GetImagesInteractor
import aura.projects.domain.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getImagesInteractor: GetImagesInteractor
): BaseViewModel() {

    private var _images = getImagesInteractor().liveData.cachedIn(viewModelScope)
    val images: LiveData<PagingData<Image>> get() = _images


}