package fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pennydropnew.R
import com.example.pennydropnew.databinding.FragmentPickPlayerBinding
import com.example.pennydropnew.viewmodels.GameViewModel
import com.example.pennydropnew.viewmodels.PickPlayersViewModel

/*import com.example.pennydropnew.databinding.FragmentPickPlayerBindingImpl*/


class PickPlayerFragment : Fragment() {

    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPickPlayerBinding
            .inflate(inflater,container, false)
            return binding.root
}*/

    private val pickPlayersViewModel
            by activityViewModels<PickPlayersViewModel>()
    private val gameViewModel
            by activityViewModels<GameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPickPlayerBinding
            .inflate(inflater, container, false)
            .apply {
                this.vm = pickPlayersViewModel

                this.buttonPlayGame.setOnClickListener {
                    gameViewModel.startGame(
                        pickPlayersViewModel.players.value  //since players is actually LiveData, we need to use pickPlayersViewModel.players.value to get out the List<NewPlayer>.
                            ?.filter { newPlayer ->
                                newPlayer.isIncluded.get() // filter each NewPlayer instance to make sure isIncluded is true
                            }?.map { newPlayer ->
                                newPlayer.toPlayer()  //convert each NewPlayer to a Player instance via the toPlayer function
                            } ?: emptyList()
                    )
                    findNavController().navigate(R.id.gameFragment) //grab the current NavController then navigate to the GameFragment via ID
                }
            }


        return binding.root
    }
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
