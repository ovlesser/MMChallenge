package ovlesser.mmchallenge

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User( var name: String,
                 var token:String = "",
                 var mobile:String,
                 var email: String) : Parcelable{
}