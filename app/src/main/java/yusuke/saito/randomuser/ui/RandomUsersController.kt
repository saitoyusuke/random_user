package yusuke.saito.randomuser.ui

import android.view.View
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
                onClickListener(View.OnClickListener {
                    this@RandomUsersController.selectListener.onSelected(user)
                })
            }
        }
    }

    interface SelectListener {
        fun onSelected(user: RandomUserEntity)
    }
}