package com.example.tablesmanagement.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tables(
    val total: Int,
    val withOrderSheet: Int,
    val withoutOrderSheet: Int,
    @SerialName("checkpads") val checkPads: List<CheckPads>
)

@Stable
@Serializable
data class CheckPads(
    val id: Int,
    val status: Boolean,
    val hash: String,
    val title: Int,
    val hasPdv: Boolean,
    val lastOrderCreated: String? = null,
    val hasOrderSheets: Boolean,
    val hasOrder: Boolean,
    val idleTime: Int,
    val activity: String,
    val pdvDevices: List<PdvDevice> = emptyList(),
    val orderSheets: List<OrderSheet> = emptyList()
)

@Serializable
data class OrderSheet(
    val id: Int,
    val info: String? = null,
    val user: User? = null,
    val opened: String,
    val seller: Seller? = null,
    val contact: String? = null,
    val hasPaid: Boolean,
    val customer: Customer? = null,
    val idleTime: Int,
    @SerialName("subtotal") val subTotal: Int,
    val hasOrders: Boolean,
    val customerName: String? = null,
    val lastOrderCreated: String? = null,
    val numberOfCustomers: Int? = null
)

@Serializable
data class PdvDevice(
    val id: Int,
    val model: String,
    val serial: String
)

@Serializable
data class Seller(
    val id: Int,
    val name: String
)

@Serializable
data class User(
    val id: Int,
    val name: String
)

@Serializable
data class Customer(
    val id: Int,
    val doc: String? = null,
    val name: String,
    val email: String,
    val phone: String,
    val status: Int,
    val birthDate: String? = null,
    val additionalPhone: String? = null,
    val customerAccount: CustomerAccount? = null
)

@Serializable
data class CustomerAccount(
    val status: Int,
    val creditLimit: Int,
    val currentBalance: Int,
    @SerialName("allowOnCustomerAccount") val allowOnCustomAccount: Int
)

