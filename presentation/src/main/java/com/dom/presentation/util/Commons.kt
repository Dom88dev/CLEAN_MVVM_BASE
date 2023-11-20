package com.dom.presentation.util

object Commons {

    /**
     * 버전 체크
     * d.d.d 형태의 버전 이름으로 업데이트 해야할지 구분
     * 각 단계별로 체크해서 현재 버전이 최신 버전보다 낮다면 true 반환
     */
    fun shouldUpdate(currentVersion: String, latestVersion: String) : Boolean {
        val current = currentVersion.split(".")
        val latest = latestVersion.split(".")
        var result = false
        var taskDone = false
        current.forEachIndexed { index, version ->
            if (!taskDone) {
                taskDone = version.toInt() != latest[index].toInt()
                if (version.toInt() < latest[index].toInt()) {
                    result = true
                }
            }
        }
        return result
    }

}