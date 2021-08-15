package com.example.customerrewards.Service

import android.content.Context
import com.example.customerrewards.Model.EligibilityModel
import com.example.customerrewards.Utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception

class EligibilityService() {
    fun checkEligibility(contex: Context, accountNumber: String): String {
        var response = ""
        try {
            response = eligibilityServiceBackend(contex, accountNumber)
        } catch (e: Exception){
            response = "Technical failure error"
        }
        return response
    }

    private fun eligibilityServiceBackend(
        contex: Context,
        accountNumber: String
    ): String {
        // API call would happen here
        val eligibilityJson = getJsonDataFromAsset(contex, "eligibilityData.json")
        val eligibilities: List<EligibilityModel> =
            Gson().fromJson(eligibilityJson, object : TypeToken<List<EligibilityModel>>() {}.type)
        var response = "Invalid account number error"

        for (eligibility in eligibilities) {
            if (eligibility.account_number == accountNumber) {
                if (eligibility.reward_eligibility == true) {
                    response = "CUSTOMER_ELIGIBLE"
                    break
                } else {
                    response = "CUSTOMER_INELIGIBILE"
                    break
                }
            }
        }
        return response
    }
}