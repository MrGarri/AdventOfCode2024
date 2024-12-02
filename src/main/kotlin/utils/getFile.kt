package utils

import java.io.File
import java.io.FileNotFoundException
import java.net.URL

fun getFileFromResources(path: String): File =
    object {}::class.java.getResource(path)?.toFile() ?: throw FileNotFoundException()

fun getLinesFromFileInResources(path: String) = getFileFromResources(path).readLines().asSequence()

private fun URL.toFile(): File = File(this.file)