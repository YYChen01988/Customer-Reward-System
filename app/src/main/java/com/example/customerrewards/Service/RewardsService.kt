package com.example.customerrewards.Service

import android.content.Context
import com.example.customerrewards.Model.RewardModel
import com.example.customerrewards.Model.RewardsServiceResponse
import com.example.customerrewards.Utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RewardsService {
    fun getRewards(
        contex: Context,
        accountNumber: String,
        subscriptions: List<String>
    ): RewardsServiceResponse {

        var rewardsList = mutableListOf<String>()
        val elibilityService = EligibilityService()
        val eligibility: String =
            elibilityService.checkEligibility(contex = contex, accountNumber = accountNumber)

        when (eligibility) {
            "CUSTOMER_ELIGIBLE" -> {
                rewardsList = rewardsServiceBackend(contex, subscriptions)
                return RewardsServiceResponse(rewardsList.toList())
            }
            "CUSTOMER_INELIGIBILE" -> {
                return RewardsServiceResponse(rewardsList)
            }
            "Technical failure error" -> {
                return RewardsServiceResponse(rewardsList)
            }
            "Invalid account number error" -> {
                return RewardsServiceResponse(rewardsList, "Account number is invalid")
                //TODO toast message
            }
        }
        return RewardsServiceResponse(rewardsList)

    }

    private fun rewardsServiceBackend(
        contex: Context,
        subscriptions: List<String>
    ): MutableList<String> {
        var rewardsList = mutableListOf<String>()
        val rewardsJson = getJsonDataFromAsset(contex, "rewardsData.json")
        val rewards: List<RewardModel> =
            Gson().fromJson(rewardsJson, object : TypeToken<List<RewardModel>>() {}.type)
        rewards.forEach { reward ->
            if (subscriptions.contains(reward.channel)) {
                reward.reward?.let { rewardsList.add(it) }
            }
        }
        return rewardsList
    }
}