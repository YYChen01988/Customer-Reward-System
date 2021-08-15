package com.example.customerrewards.Model

import com.google.gson.annotations.SerializedName

data class RewardsServiceResponse(

    @SerializedName("rewards")
    val rewards: List<String>? = null,

    @SerializedName("errorMessage")
    val errorMessage: String? = null
)