version: 1

//name: Container Build
description: "This will create a multibranch pipeline job for container builds"

type: pipeline-template
templateType: MULTIBRANCH

parameters:
  - name: github_organization
    type: string
    displayName: Specify the GitHub Organisation e.g. lot2learn (mandatory)

  - name: github_repo
    type: string
    displayName: Specify the GitHub Repository e.g. nodejs-project (mandatory)

  - name: headWildcardFilterIncludes
    type: string
    displayName: "Space-separated list of name patterns to consider. You may use * as a wildcard; for example: master release*"

  - name: headWildcardFilterExcludes
    type: string
    displayName: "Space-separated list of name patterns to ignore even if matched by the includes list. For example: release alpha-* beta-*"

  - name: github_creds
    type: credentials
    displayName: Specify the GitHub Credentials ID name for the source code repository (mandatory)

  - name: dockerfiles
    type: string
    displayName: Specify the path to the Dockerfile(s) as semicolon delimited string e.g. app01/Dockerfile (optional)
    
  - name: build_arguments
    type: string
    displayName: Specify the container build arguments as semicolon delimited string e.g. VAR1=FOO;VAR2=BAR (optional)

  - name: docker_tag
    type: string
    displayName: Specify the container tagname e.g. arnabdnany1706/smartdb:latest (mandatory)

multibranch:
  branchSource:
    github:
      repoOwner: ${github_organization}
      repository: ${github_repo}
      credentialsId: ${github_creds}
      traits:
        - gitHubBranchDiscovery:
            strategyId: 3
        - headWildcardFilter:
            includes: ${headWildcardFilterIncludes}
            excludes: ${headWildcardFilterExcludes}

    strategy:
      $class: DefaultBranchPropertyStrategy # All branches get the same properties
      props:
        - $class: NoTriggerBranchProperty # Suppress automatic SCM triggering

  orphanedItemStrategy:
    daysToKeep: 60
  scanRepositoryInterval: 15 minutes
