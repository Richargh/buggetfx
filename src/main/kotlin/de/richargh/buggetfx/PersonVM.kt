package de.richargh.buggetfx

import tornadofx.getProperty
import tornadofx.property
import java.time.LocalDate
import java.time.Period

class PersonVM(id: Int, name: String, birthday: LocalDate) {
    var id by property(id)
    fun idProperty() = getProperty(PersonVM::id)

    var name by property(name)
    fun nameProperty() = getProperty(PersonVM::name)

    var birthday by property(birthday)
    fun birthdayProperty() = getProperty(PersonVM::birthday)

    val age: Int get() = Period.between(birthday, LocalDate.now()).years
}