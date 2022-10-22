package com.example.githubuserapp2.response

import com.google.gson.annotations.SerializedName

data class FragmentResponse(

	@field:SerializedName("Response")
	val response: List<ResponseItem>
)

data class ResponseItem(

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,
)
