package com.example.customerrewards.Model

import com.google.gson.annotations.SerializedName

data class RewardModel(


    @SerializedName("channel")
    val channel: String? = null,

    @SerializedName("reward")
    val reward: String? = null

)