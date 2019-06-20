package ovlesser.mmchallenge

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_quote_calculator.*
import kotlinx.android.synthetic.main.fragment_your_quote.*
import ovlesser.mmchalenge.R
import ovlesser.mmchalenge.databinding.DialogLoginBinding
import ovlesser.mmchalenge.databinding.FragmentQuoteCalculatorBinding

class QuoteCalculatorFragment: Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel = arguments?.getParcelable<Parcelable>(ARG_VIEW_MODEL)
            ?.let { it as? DetailViewModel } ?: return
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val dataBinding: FragmentQuoteCalculatorBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_quote_calculator, container, false)
        dataBinding.viewModel = detailViewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_calculate_quote.setOnClickListener { showLoginDialog() }
    }

    fun showLoginDialog() {
        val dataBinding: DialogLoginBinding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_login, null, false)
        dataBinding.fragment = this
        dialog = AlertDialog.Builder(context!!)
            .setView(dataBinding.root)
            .create()
            .apply { setCancelable(false)}
        dialog.show()
    }


    fun newUser() {
        dialog.dismiss()
        val user = User(name="John Doe", mobile = "04778095252", email = "johndow@test.com")
        loadYourQuoteFragment( user)
    }
    fun login() {
        dialog.dismiss()
        val user = User(name="John Doe", mobile = "04778095252", email = "johndow@test.com")
        loadYourQuoteFragment( user)
    }

    private fun loadYourQuoteFragment( user: User) {
        val userViewModel = UserViewModel(user)
        val yourQuoteFragment = YourQuoteFragment.newInstance(detailViewModel, userViewModel)
        activity?.run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_content, yourQuoteFragment)
                .disallowAddToBackStack()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }

    }
    companion object {
        private const val ARG_VIEW_MODEL = "view-model"

        @JvmStatic
        fun newInstance(viewModel: DetailViewModel) = QuoteCalculatorFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_VIEW_MODEL, viewModel)
            }
        }
    }
}
