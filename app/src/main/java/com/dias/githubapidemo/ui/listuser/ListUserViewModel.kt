package com.dias.githubapidemo.ui.listuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dias.githubapidemo.data.User
import com.dias.githubapidemo.data.UserPagingSource
import com.dias.githubapidemo.data.UserPagingSource.Companion.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class ListUserViewModel : ViewModel() {

    val listUser: Flow<PagingData<User>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = PAGE_SIZE,
            maxSize = PAGE_SIZE * 3
        ),
        pagingSourceFactory = { UserPagingSource() }
    ).flow.cachedIn(viewModelScope)
}