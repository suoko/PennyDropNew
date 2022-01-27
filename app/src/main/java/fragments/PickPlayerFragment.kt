package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.isVisible
import com.example.pennydropnew.R
import com.example.pennydropnew.databinding.FragmentPickPlayerBinding
/*import com.example.pennydropnew.databinding.FragmentPickPlayerBindingImpl*/


class PickPlayerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPickPlayerBinding
            .inflate(inflater,container, false)
            return binding.root


}
    // Inflate the layout for this fragment
    /*return inflater.inflate(
        R.layout.fragment_pick_player,
        container,
        false)*/
    /*override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    val view = inflater.inflate(
        R.layout.fragment_pick_player,
        container,
    )

    with(view?.findViewById<View>(R.id.mainPlayer)) {
        this?.findViewById<CheckBox>(R.id.checkbox_player_active)?.let {
            it.isVisible = false
            it.isChecked = true
        }
         }
    return view
}*/
  /*  companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PickPlayerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PickPlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}