package de.richargh.buggetfx.shared_kernel

abstract class SealedEnum {
    override fun toString(): String =
            javaClass.simpleName

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return javaClass == other?.javaClass
    }

    override fun hashCode() = javaClass.hashCode()
}