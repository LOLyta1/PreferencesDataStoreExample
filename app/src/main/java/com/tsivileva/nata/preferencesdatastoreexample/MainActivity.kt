package com.tsivileva.nata.preferencesdatastoreexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tsivileva.nata.preferencesdatastoreexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<GameViewModel>()
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel.prefsLiveData.observe(this){
            binding.prefsContentTV.text = "Настройки: ${it}\n\r"
        }

        binding.saveBtn.setOnClickListener {
            try {
                val soundVolume = binding.soundVolumeET.text.toString().toInt()
                val musicVolume = binding.musicVolumeET.text.toString().toInt()
                val movementDuration = binding.movementDurationET.text.toString().toInt()
                val isAuthorized = binding.isAuthorizedSwith.isChecked
                val gamePrefs = GamePrefs(
                    soundVolume = soundVolume,
                    musicVolume = musicVolume,
                    movementDuration = movementDuration,
                    isUserAuthorize = isAuthorized
                )
                viewModel.saveGamePrefs(gamePrefs)
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this,"Ошибка ${e.message}",Toast.LENGTH_SHORT).show()
            }
        }

        binding.readBtn.setOnClickListener {
            viewModel.getPrefs()
        }
    }
}