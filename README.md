# Java App for routine actions on your Scaleway account

## 1. Build 

`$ ./gradlew clean bootJar`

## 2. Copy the jar to your server (Or leave it in your local machine)

PS. The server needs to have a Java 8+ for the application to be able to run properly

`scp build/libs/scaleway-auto-backup-0.0.1-SNAPSHOT.jar root@[someserverip or hostname]:/var/your/path/`

## 3. Running a backup job

Edit your crontab

`crontab -e`

Eventually choose an editor to edit your cronjob file
In my crontab I added the following

```
0 5 * * 1 java -jar /var/tools/scaleway-auto-backup-0.0.1-SNAPSHOT.jar --token="YOUR-SCALEWAY-TOKEN-HERE" --serverId="YOUR-SERVER-ID-HERE" --zone="YOUR-ZONE-HERE"
```

Save the file and you're done.

## Reference

Scalewaz API [https://developers.scaleway.com/en/products/instance/api/#endpoints]
