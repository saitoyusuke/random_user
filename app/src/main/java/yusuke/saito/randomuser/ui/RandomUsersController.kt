package yusuke.saito.randomuser.ui

import com.airbnb.epoxy.EpoxyDataBindingLayouts
import com.airbnb.epoxy.TypedEpoxyController
import yusuke.saito.randomuser.R
import yusuke.saito.randomuser.entity.RandomUserEntity
import yusuke.saito.randomuser.epoxyViewHolderRandomUser

@EpoxyDataBindingLayouts(
    R.layout.epoxy_view_holder_random_user
)
class RandomUsersController(
    private val selectListener: SelectListener
) : TypedEpoxyController<List<RandomUserEntity>>() {
    override fun buildModels(users: List<RandomUserEntity>?) {
        users?.forEach { user ->
            epoxyViewHolderRandomUser {
                id(user.phone)
                phone(user.phone)
            }
        }
    }

    interface SelectListener {
        fun onSelected(user: RandomUserEntity)
    }
}