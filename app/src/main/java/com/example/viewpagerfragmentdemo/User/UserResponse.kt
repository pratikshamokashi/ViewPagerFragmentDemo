package com.example.viewpagerfragmentdemo.User

//import javax.annotation.Generated
import androidx.room.Entity
import com.example.viewpagerfragmentdemo.User.Address
import com.example.viewpagerfragmentdemo.User.Company
import com.google.gson.annotations.SerializedName

//@Generated("com.robohorse.robopojogenerator")
@Entity
data class UserResponse(

    @field:SerializedName("website")
	val website: String? = null,

    @field:SerializedName("address")
	val address: Address? = null,

    @field:SerializedName("phone")
	val phone: String? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("company")
	val company: Company? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("email")
	val email: String? = null,

    @field:SerializedName("username")
	val username: String? = null

	//val isFavorute: Boolean = false

)