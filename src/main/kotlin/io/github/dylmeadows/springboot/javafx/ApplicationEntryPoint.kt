package io.github.dylmeadows.springboot.javafx

import javafx.stage.Stage

interface ApplicationEntryPoint {
    fun init()
    fun start(stage: Stage)
    fun stop()
}