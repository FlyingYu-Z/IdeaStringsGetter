package com.github.flyingyuz.ideastringsgetter.services

import com.github.flyingyuz.ideastringsgetter.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
