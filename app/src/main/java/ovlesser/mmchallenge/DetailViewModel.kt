package ovlesser.mmchallenge

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DetailViewModel(
    var money: Int = 3000,
    var time: Int = 4) : Parcelable {
}
