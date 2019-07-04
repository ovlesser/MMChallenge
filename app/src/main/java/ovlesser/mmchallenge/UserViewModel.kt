package ovlesser.mmchallenge

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import kotlinx.android.parcel.Parcelize

// Observable example
@Parcelize
class UserViewModel(val user: User) : Parcelable, BaseObservable() {
    private var _editMode : Boolean = false
    var editMode : Boolean
        @Bindable
        get() {
            return _editMode
        }
        set(value) {
            _editMode = value
            notifyPropertyChanged(BR._all)
        }

    //new user registration
    fun register() : Boolean {
        //TODO: call some backend API to register a new user, return true if new user is registered successfully at Backend
        return true
    }

    //user info validation
    fun login() : Boolean {
        //TODO: call some backend API to validate the user info, return true if the user is valid at Backend
        return true
    }

    //update user info
    fun update() : Boolean{
        //TODO: call some backend API to update the user info, return true if the user info is updated successfully at Backend
        return true
    }
}