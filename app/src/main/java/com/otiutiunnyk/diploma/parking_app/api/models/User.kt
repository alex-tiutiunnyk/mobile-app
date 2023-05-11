package com.otiutiunnyk.diploma.parking_app.api.models

import java.util.*

data class User(
    var login: String,
    var password: String,
    var systemLanguage: String,
    var email: String? = null,
    var birthDate: Date? = null
)
