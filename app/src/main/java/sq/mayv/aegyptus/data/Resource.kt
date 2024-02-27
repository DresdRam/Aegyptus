package com.mayv.ctgate.data

class Resource<Template>(
    var data: Template? = null,
    var statusCode: Int = 0,
    var exception: Exception = Exception("Resource Exception!")
)