package com.newlinecode.scalewayautobackup

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import spock.lang.Ignore
import spock.lang.Specification

@Ignore
class ServerLookupSpec extends Specification {

    def "Get all servers from Scaleway"() {

        given:
        def token = "3--------6-d--7-4--d-a--f-7------4"
        def zone = "fr-par-1"

        when:
        def servers = new ServerLookup(token, zone).loadAllServers()
        def jsonSlurper = new JsonSlurper()
        def result = jsonSlurper.parseText(servers)

        then:
        result.servers.size() > 0
    }

    def "Find server with given name"() {

        given:
        def token = "3--------6-d--7-4--d-a--f-7------4"
        def zone = "fr-par-1"
        def id = "3__________f"

        when:
        def servers = new ServerLookup(token, zone).loadServersById(id)
        def jsonSlurper = new JsonSlurper()
        def result = jsonSlurper.parseText(servers)

        then:
        result.server.tags.size() > 0
    }

    def dispatchBackupAction() {
        given:
        def token = "3--------6-d--7-4--d-a--f-7------4"
        def zone = "fr-par-1"
        def id = "3__________f"

        when:
        def result = new ServerLookup(token, zone).dispatchBackup(id)

        then:
        result != null

    }

    def "buildAction"() {
        given:
        def builder = new JsonBuilder()
        builder action: "backup"

        when:
        def result = builder.toString()

        then:
        print result
        result != null
    }
}
