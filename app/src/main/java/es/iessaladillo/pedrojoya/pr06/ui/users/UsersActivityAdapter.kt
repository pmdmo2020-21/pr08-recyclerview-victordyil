package es.iessaladillo.pedrojoya.pr06.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.pr06.R
import es.iessaladillo.pedrojoya.pr06.data.model.User
import es.iessaladillo.pedrojoya.pr06.utils.loadUrl
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.users_activity_item.view.*

class UsersActivityAdapter : ListAdapter<User, UsersActivityAdapter.ViewHolder>(UserDiffCallback) {
    var onEditListener: ((Int) -> Unit)? = null
    var onDeleteListener: ((Int) -> Unit)? = null

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        init {
            //rama
            containerView.editButton.setOnClickListener {
                onEditListener?.invoke(bindingAdapterPosition)
            }
            containerView.editDelete.setOnClickListener {
                onDeleteListener?.invoke(bindingAdapterPosition)
            }
        }

        fun bind(user: User) {
            user.run {
                containerView.lblName.text = nombre
                containerView.lblEmail.text = email
                containerView.lblTelefono.text = phoneNumber
                containerView.cardImage.loadUrl(photoUrl)
            }
        }
    }

    object UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflate = layoutInflater.inflate(R.layout.users_activity_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = currentList[position]
        holder.bind(user)
    }

}
