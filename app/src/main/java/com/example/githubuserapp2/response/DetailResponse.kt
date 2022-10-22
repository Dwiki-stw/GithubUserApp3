package com.example.githubuserapp2.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("html_url")
	val url: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("public_repos")
	val publicRepos: Int? = null,

	@field:SerializedName("followers")
	val followers: Int? = null,

	@field:SerializedName("following")
	val following: Int? = null,

	@field:SerializedName("login")
	val login: String
)
