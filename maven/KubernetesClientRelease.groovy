def updateFabric8ReleaseDeps = ""
try {
  updateFabric8ReleaseDeps = UPDATE_FABRIC8_RELEASE_DEPENDENCIES
} catch (Throwable e) {
  updateFabric8ReleaseDeps = "${env.UPDATE_FABRIC8_RELEASE_DEPENDENCIES ?: 'false'}"
}

def isRelease = ""
try {
  isRelease = IS_RELEASE
} catch (Throwable e) {
  isRelease = "${env.IS_RELEASE ?: 'true'}"
}

stage 'wait for kubernetes-model to be synced with maven central'
waitUntilArtifactSyncedWithCentral {
  artifact = 'kubernetes-model'
}

stage 'canary release kubernetes-client'
releaseKubernetesClient{
  updateDeps = updateFabric8ReleaseDeps
  isRelease = isRelease
}

stage 'wait for kubernetes-client to be synced with maven central'
waitUntilArtifactSyncedWithCentral {
  artifact = 'kubernetes-client'
}