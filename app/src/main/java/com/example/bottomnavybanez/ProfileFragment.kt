package com.example.bottomnavybanez

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.*

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val nameEditText = view.findViewById<EditText>(R.id.editTextName)
        val ageEditText = view.findViewById<EditText>(R.id.editTextAge)
        val maleRadioButton = view.findViewById<RadioButton>(R.id.radioButtonMale)
        val femaleRadioButton = view.findViewById<RadioButton>(R.id.radioButtonFemale)
        val agreeCheckBox = view.findViewById<CheckBox>(R.id.checkBoxAgree)

        val saveButton = view.findViewById<Button>(R.id.buttonSave)
        saveButton.setOnClickListener {
            // Handle saving logic here
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val gender = if (maleRadioButton.isChecked) "Male" else if (femaleRadioButton.isChecked) "Female" else "Other"
            val agreed = agreeCheckBox.isChecked

            // You can save this data or show a Toast
            Toast.makeText(context, "Profile Saved: $name, $age, $gender, Agreed: $agreed", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}