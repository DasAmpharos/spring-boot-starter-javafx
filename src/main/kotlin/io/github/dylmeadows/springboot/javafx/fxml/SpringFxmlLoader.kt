package io.github.dylmeadows.springboot.javafx.fxml

import io.github.dylmeadows.commonkt.core.io.Resource
import io.github.dylmeadows.commonkt.core.io.url
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.util.Callback
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import java.io.InputStream
import java.net.URL
import java.util.*

@Component
class SpringFxmlLoader @Autowired constructor(
    private val ctx: ApplicationContext) {

    fun <T : Parent> load(resource: Resource): T {
        return load(resource.url!!)
    }

    fun <T : Parent> load(resource: Resource, resources: ResourceBundle): T {
        return load(resource.url!!, resources)
    }

    fun <T : Parent> load(location: URL): T {
        val loader = FXMLLoader()
        loader.location = location
        loader.controllerFactory = Callback { ctx.getBean(it) }
        return loader.load()
    }

    fun <T : Parent> load(location: URL, resources: ResourceBundle): T {
        val loader = FXMLLoader()
        loader.location = location
        loader.resources = resources
        loader.builderFactory
        loader.controllerFactory = Callback { ctx.getBean(it) }
        return loader.load()
    }

    fun <T : Parent> load(inputStream: InputStream): T {
        val loader = FXMLLoader()
        loader.controllerFactory = Callback { ctx.getBean(it) }
        return loader.load(inputStream)
    }

    fun <T : Parent> load(inputStream: InputStream, resources: ResourceBundle): T {
        val loader = FXMLLoader()
        loader.resources = resources
        loader.controllerFactory = Callback { ctx.getBean(it) }
        return loader.load(inputStream)
    }
}