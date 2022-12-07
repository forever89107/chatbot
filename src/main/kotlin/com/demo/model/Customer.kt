package com.demo.model

import kotlinx.serialization.*

@Serializable
data class Customer(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)
