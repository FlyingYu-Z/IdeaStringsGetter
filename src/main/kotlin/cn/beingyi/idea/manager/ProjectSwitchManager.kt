package cn.beingyi.idea.manager

import cn.beingyi.idea.listeners.psiTreeChangeListener
import cn.beingyi.idea.model.ProjectInfo
import com.intellij.ide.impl.ProjectUtil
import com.intellij.openapi.Disposable
import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.update.ScopeInfo.FILES
import com.intellij.psi.PsiManager
import java.nio.file.Paths
import java.util.concurrent.ConcurrentHashMap

/**
 * author: zhengyu
 * date: 2021/8/6 16:10
 *
 */
class ProjectSwitchManager {

    private val stateMap = ConcurrentHashMap<Project, Boolean>()
    private val projectMap = ConcurrentHashMap<Project, ProjectInfo>()

    init {

    }


    fun getProjectInfo(project: Project?): ProjectInfo? {
        return projectMap.getOrDefault(project, null)
    }

    @Synchronized
    fun setProjectEnabled(project: Project?, enabled: Boolean) {
        if (project == null) {
            return
        }
        stateMap.put(project, enabled)
        if (enabled) {
            val basePath = project.basePath
            val projectInfo = ProjectInfo(basePath)
            projectMap.put(project, projectInfo)
//            PsiManager.getInstance(project).addPsiTreeChangeListener(psiTreeChangeListener,object : Disposable{
//                override fun dispose() {
//
//                }
//            })
        } else {
            projectMap.remove(project)
//            PsiManager.getInstance(project).removePsiTreeChangeListener(psiTreeChangeListener)
        }
    }

    @Synchronized
    fun isProjectEnabled(project: Project?): Boolean {
        if (project == null) {
            return false
        }
        return stateMap.getOrDefault(project, false)
    }

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { ProjectSwitchManager() }
    }

}