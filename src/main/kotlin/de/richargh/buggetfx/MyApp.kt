package de.richargh.buggetfx

import tornadofx.*
import java.time.LocalDate

fun main(args: Array<String>) {
    launch<MyApp>(args)
}

class MyApp: App(MyView::class)


