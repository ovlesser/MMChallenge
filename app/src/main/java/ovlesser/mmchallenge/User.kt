package ovlesser.mmchallenge

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User( val name: String,
                 val token:String = "",
                 val mobile:String,
                 val email: String) : Parcelable{
}