package ovlesser.mmchallenge.view

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_your_quote.*
import ovlesser.mmchallenge.R
import ovlesser.mmchallenge.UserViewModel
import ovlesser.mmchallenge.databinding.FragmentYourQuoteBinding
import ovlesser.mmchallenge.viewModel.DetailViewModel

class YourQuoteFragment: Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var dataBinding: FragmentYourQuoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel = activity?.run {
            ViewModelProviders.of(this).get(DetailViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        userViewModel = arguments?.getParcelable<Parcelable>(ARG_USER_VIEW_MODEL)
            ?.let { it as? UserViewModel } ?: return
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment and create data binding
        dataBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_your_quote, container, false)
        dataBinding.lifecycleOwner = activity
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_detail_edit.setOnClickListener {
            val fragment = QuoteCalculatorFragment.newInstance()
            activity?.run {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .disallowAddToBackStack()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
        bt_apply.setOnClickListener {
            AlertDialog.Builder(context!!)
                .setMessage("Your Application is Successful.")
                .setPositiveButton("OK"){ _, _ -> }
                .create()
                .apply { setCancelable(false)}
                .show()
        }
        dataBinding.detailViewModel = detailViewModel
        dataBinding.userViewModel = userViewModel
    }

    companion object {
        private const val ARG_USER_VIEW_MODEL = "user_view-model"
        private lateinit var fragment: Fragment

        @JvmStatic
        fun newInstance(userViewModel: UserViewModel) : Fragment{
            fragment = if (!Companion::fragment.isInitialized) YourQuoteFragment() else fragment
            return fragment.apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER_VIEW_MODEL, userViewModel)
                }
            }
        }
    }
}