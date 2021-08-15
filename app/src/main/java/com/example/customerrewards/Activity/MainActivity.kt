package com.example.customerrewards.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.customerrewards.Service.RewardsService
import com.example.customerrewards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityMainBinding
    
    var rewardsService = RewardsService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        viewBinding.submitBnt.setOnClickListener {
            val subscriptions = viewBinding.subscriptions.text.toString().split(",")
            val response = rewardsService.getRewards(
                this,
                viewBinding.accountNumber.text.toString(),
                subscriptions
            )

            if (!response.errorMessage.isNullOrEmpty()) {
                viewBinding.result.text = response.errorMessage
            } else {
                viewBinding.result.text = response.rewards.toString()
            }
        }
    }
}