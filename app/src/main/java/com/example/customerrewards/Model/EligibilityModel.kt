package com.example.customerrewards.Model

import com.google.gson.annotations.SerializedName

data class EligibilityModel(

    @SerializedName("account_number")
    val account_number: String? = null,

    @SerializedName("reward_eligibility")
    val reward_eligibility: Boolean? = null
)