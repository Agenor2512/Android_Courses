package com.example.myapplication

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentFirstBinding.inflate(inflater, container, false)
      return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.textviewFirst.setOnClickListener{
            val builder = requireActivity().let{
                AlertDialog.Builder(it)
            }
            builder.setMessage("It hurts 2")
                .setTitle("Aie")
                .setPositiveButton("OUI"){ dialog, id ->
                    dialog.dismiss()
                }
                .setNegativeButton("NON"){ dialog, id ->
                    dialog.dismiss()
                }
            builder.create().show()
        }
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}