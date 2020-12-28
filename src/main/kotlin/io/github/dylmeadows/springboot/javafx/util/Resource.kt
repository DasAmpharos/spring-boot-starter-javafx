package io.github.dylmeadows.springboot.javafx.util

import java.io.InputStream
import java.net.URL

class Resource(
    val path: String
) {
    val url: URL
        get() = loader.getResource(path)
            ?: error("Unable to find resource for $path")
    val inputStream: InputStream
        get() = loader.getResourceAsStream(path)
            ?: error("Unable to find resource for $path")
    val contents: String by lazy {
        String(inputStream.readBytes())
    }

    private companion object {
        val loader: ClassLoader by lazy {
            Resource::class.java.classLoader
        }
    }
}