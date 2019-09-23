package com.newlinecode.scalewayautobackup

import groovy.json.JsonBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ServerLookup {
    @Value('${token}')
    private String token

    @Value('${serverId}')
    private String serverId

    @Value('${zone}')
    private String zone

    ServerLookup(def token, def zone) {
        this.token = token
        this.zone = zone
    }

    String loadAllServers() {
        String postResult

        ((HttpURLConnection)new URL("https://api.scaleway.com/instance/v1/zones/${zone}/servers").openConnection()).with({
            requestMethod = 'GET'
            doOutput = true
            setRequestProperty('X-Auth-Token', token)
            postResult = inputStream.text
        })
    }

    String loadServersById() {
        String postResult

        ((HttpURLConnection)new URL("https://api.scaleway.com/instance/v1/zones/${zone}/servers/${serverId}").openConnection()).with({
            requestMethod = 'GET'
            doOutput = true
            setRequestProperty('X-Auth-Token', token)
            postResult = inputStream.text
        })
    }

    String dispatchBackup() {

        print "\n-----------------Starting backup------------------\n"
        print "For server id: " + serverId
        print "\n-----------------------------------\n"
        print "In zone: " + zone
        print "\n-----------------------------------\n"
        print "With access token: " + token
        print "\n-----------------------------------\n"

        String postResult

        ((HttpURLConnection)new URL("https://api.scaleway.com/instance/v1/zones/${zone}/servers/${serverId}/action").openConnection()).with({
            requestMethod = 'POST'
            doOutput = true
            setRequestProperty('X-Auth-Token', token)
            setRequestProperty('Content-Type', 'application/json')

            outputStream.withPrintWriter({ printWriter ->

                def builder = new JsonBuilder()
                builder action: "backup"

                printWriter.write(builder.toString())
            })

            postResult = inputStream.text
        })
    }
}
