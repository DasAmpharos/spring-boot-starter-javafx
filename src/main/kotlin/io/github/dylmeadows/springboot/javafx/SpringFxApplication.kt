package io.github.dylmeadows.springboot.javafx

import javafx.application.Application
import javafx.stage.Stage
import org.springframework.beans.factory.getBean
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext
import kotlin.reflect.KClass

class SpringFxApplication : Application() {
    private lateinit var entryPoint: ApplicationEntryPoint
    private lateinit var ctx: ConfigurableApplicationContext

    companion object {
        private lateinit var entryPointClass: KClass<out ApplicationEntryPoint>

        fun <T : ApplicationEntryPoint> launch(entryPointClass: KClass<T>, vararg args: String) {
            this.entryPointClass = entryPointClass
            launch(SpringFxApplication::class.java, *args)
        }
    }

    override fun init() {
        val args = parameters.raw.toTypedArray()
        ctx = SpringApplicationBuilder(entryPointClass.java)
            .web(WebApplicationType.NONE)
            .headless(false)
            .run(*args)
        entryPoint = ctx.getBean(ApplicationEntryPoint::class)
        entryPoint.init()
    }

    override fun start(stage: Stage) {
        entryPoint.start(stage)
    }

    override fun stop() {
        entryPoint.stop()
        ctx.close()
    }
}