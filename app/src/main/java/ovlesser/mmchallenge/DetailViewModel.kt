package ovlesser.mmchallenge

import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import kotlinx.android.parcel.Parcelize

@Parcelize
class DetailViewModel(
    var money: Int = 3000,
    var time: Int = 4
) : BaseObservable(), Parcelable {
}
