package ovlesser.mmchallenge

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class DetailViewModel(
    var amount: Int = 3000,
    var month: Int = 4) : Parcelable {

    private val rate = 0.1581 // guessed with the example in the description, amount=5000, month=24, weekly payment=56.15
    fun pmt() : Double{
        val v = 1 + rate / 52.0
        val t = -(month.toDouble() / 12.0) * 52.0
        return if (rate.equals(0.0)) -amount / t else amount * (rate / 52.0) / (1.0 - Math.pow(v, t))
    }
}
