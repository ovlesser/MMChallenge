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
import kotlinx.android.synthetic.main.fragment_your_quote.*
import ovlesser.mmchallenge.databinding.FragmentYourQuoteBinding

class YourQuoteFragment: Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var dataBinding: FragmentYourQuoteBinding
    private var editMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel = arguments?.getParcelable<Parcelable>(ARG_DETAIL_VIEW_MODEL)
            ?.let { it as? DetailViewModel } ?: return
        userViewModel = arguments?.getParcelable<Parcelable>(ARG_USER_VIEW_MODEL)
            ?.let { it as? UserViewModel } ?: return
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment and create data binding
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_your_quote, container, false)
        dataBinding.detailViewModel = detailViewModel
        dataBinding.userViewModel = userViewModel
        dataBinding.fragment = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_detail_edit.setOnClickListener {
            val fragment = QuoteCalculatorFragment.newInstance(detailViewModel)
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
    }

    //update the status of the personal info section
    fun updateInfo() {
        if (editMode) {
            userViewModel.update()
        }
        editMode = !editMode
        dataBinding.btInfoEdit.text = if (editMode) resources.getString(R.string.save) else resources.getString(R.string.edit)
        dataBinding.editName.isEnabled = editMode
        dataBinding.editMobile.isEnabled = editMode
        dataBinding.editEmail.isEnabled = editMode
    }

    companion object {
        private const val ARG_DETAIL_VIEW_MODEL = "detail_view-model"
        private const val ARG_USER_VIEW_MODEL = "user_view-model"

        @JvmStatic
        fun newInstance(detailViewModel: DetailViewModel, userViewModel: UserViewModel) = YourQuoteFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_DETAIL_VIEW_MODEL, detailViewModel)
                putParcelable(ARG_USER_VIEW_MODEL, userViewModel)
            }
        }
    }
}