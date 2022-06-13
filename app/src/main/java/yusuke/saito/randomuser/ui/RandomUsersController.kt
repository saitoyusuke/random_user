package yusuke.saito.randomuser.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyDataBindingLayouts
import com.airbnb.epoxy.TypedEpoxyController
import com.bumptech.glide.Glide
import yusuke.saito.randomuser.R
import yusuke.saito.randomuser.entity.RandomUserEntity
import yusuke.saito.randomuser.epoxyViewHolderRandomUser

object GlideApp {
    @BindingAdapter("thumbnail")
    @JvmStatic
    fun loadThumbnail(view: ImageView, url: String) =
        Glide.with(view).load(url).into(view)
}
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
                thumbnail(user.thumbnail)
                gender(user.gender)
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