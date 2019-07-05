package ovlesser.mmchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_quote_calculator.*
import ovlesser.mmchallenge.*
import ovlesser.mmchallenge.databinding.DialogLoginBinding
import ovlesser.mmchallenge.databinding.FragmentQuoteCalculatorBinding
import ovlesser.mmchallenge.di.DaggerMyApplicationComponent
import ovlesser.mmchallenge.model.User
import ovlesser.mmchallenge.viewModel.DetailViewModel
import javax.inject.Inject


class QuoteCalculatorFragment: Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel = activity?.run {
            ViewModelProviders.of(this).get(DetailViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    lateinit var dataBinding: FragmentQuoteCalculatorBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment and create data binding
        dataBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_quote_calculator, container, false)
        dataBinding.lifecycleOwner = activity
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_calculate_quote.setOnClickListener { showLoginDialog() }
        dataBinding.viewModel = detailViewModel

// updating ViewModel periodically example
//        Timer().schedule(1000, 1000) {
//            (dataBinding.viewModel as DetailViewModel).run {
//                amount.postValue( Random().nextInt(13000) + 2000)
//                month.postValue( Random().nextInt(33) + 3)
//                pmt.postValue( pmt())
//            }
//        }
    }

    private fun showLoginDialog() {
        // data binding
        val dataBinding: DialogLoginBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.dialog_login, null, false)
        dataBinding.fragment = this
        dialog = AlertDialog.Builder(context!!)
            .setView(dataBinding.root)
            .create()
            .apply { setCancelable(false)}
        dialog.show()
    }


    @Inject
    lateinit var user : User

    fun newUser() {
        dialog.dismiss()
        user = DaggerMyApplicationComponent.create().inject()
        val userViewModel = UserViewModel(user)
        if (userViewModel.register()) {
            loadYourQuoteFragment(userViewModel)
        }
    }
    fun login() {
        dialog.dismiss()
        //TODO: mocking user
        val user = User(
            name = "John Doe",
            mobile = "04778095252",
            email = "johndow@test.com"
        )
        val userViewModel = UserViewModel(user)
        if (userViewModel.login()) {
            loadYourQuoteFragment(userViewModel)
        }
    }

    private fun loadYourQuoteFragment( userViewModel: UserViewModel) {
        // init user view model based on user info
        val yourQuoteFragment =
            YourQuoteFragment.newInstance(userViewModel)
        activity?.run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, yourQuoteFragment)
                .disallowAddToBackStack()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }

    }

    companion object {
        private lateinit var fragment: Fragment

        @JvmStatic
        fun newInstance() : Fragment{
            fragment = if (!Companion::fragment.isInitialized) QuoteCalculatorFragment() else fragment
            return fragment
        }
    }
}
