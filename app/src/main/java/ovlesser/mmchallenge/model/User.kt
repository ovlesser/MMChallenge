package ovlesser.mmchallenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class User ( var name: String,
                 var token:String = "",
                 var mobile:String,
                 var email: String) : Parcelable{
}