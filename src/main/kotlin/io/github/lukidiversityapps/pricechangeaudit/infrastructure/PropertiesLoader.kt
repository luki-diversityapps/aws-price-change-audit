package io.github.lukidiversityapps.pricechangeaudit.infrastructure

import java.io.IOException
import java.util.*

object PropertiesLoader {
    @Throws(IOException::class)
    fun loadProperties(resourceFileName: String = "application.yml"): Properties {
        val configuration = Properties()
        val inputStream =
            PropertiesLoader::class.java
                .getClassLoader()
                .getResourceAsStream(resourceFileName)
        configuration.load(inputStream)
        inputStream!!.close()
        return configuration
    }
}
