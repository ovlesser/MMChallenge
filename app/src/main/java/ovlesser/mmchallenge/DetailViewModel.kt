package ovlesser.mmchallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.pow

//LiveData example
class DetailViewModel : ViewModel() {
    private val rate = 0.1581 // guessed with the example in the description, amount=5000, month=24, weekly payment=56.15

    val amount = MutableLiveData(3000)
    val month = MutableLiveData(4)
    val pmt = MutableLiveData(pmt())

    init {
// updating ViewModel periodically example
//        Timer().schedule(1000, 1000) {
//            amount.postValue( Random().nextInt(13000) + 2000)
//            month.postValue( Random().nextInt(33) + 3)
//        }
    }

    fun pmt() : Double{
        val v = 1 + rate / 52.0
        val t: Int = month.value?.also { -(it.toDouble() / 12.0) * 52.0 }!!
        return if (rate.equals(0.0)) -amount.value?.toDouble()!! / t else (amount.value?.toDouble()
            ?: 0.0) * (rate / 52.0) / (1.0 - v.pow(
            t.toDouble()
        ))
    }
}
