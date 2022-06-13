package yusuke.saito.randomuser.viewmodel

import yusuke.saito.randomuser.entity.RandomUserEntity

data class RandomUsersUiModel(
    val isLoading: Boolean = false,
    val users: List<RandomUserEntity> = listOf()
)
