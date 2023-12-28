package com.example.amphibians.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable // 記得加這一行 !! 不然會出錯
data class AmphibianPhoto(
    val name: String,
    val type: String,
    val description: String,
    //val id: String,

    @SerialName(value = "img_src")
    val imgSrc: String

)


