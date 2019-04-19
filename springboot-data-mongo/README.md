 spring-boot-mongodb
----
##### 1. install mongo db by docker <br>
<code>
docker run --name mongodb \<br>
 &nbsp;&nbsp;&nbsp;&nbsp;-p 27017:27017 \<br>
 &nbsp;&nbsp;&nbsp;&nbsp;--privileged=true \<br>
 &nbsp;&nbsp;&nbsp;&nbsp;--restart=always \<br>
 &nbsp;&nbsp;&nbsp;&nbsp;-e MONGO_INITDB_ROOT_USERNAME=root \<br>
 &nbsp;&nbsp;&nbsp;&nbsp;-e MONGO_INITDB_ROOT_PASSWORD=Pioneer@2017 \<br>
 &nbsp;&nbsp;&nbsp;&nbsp;-v /data/mongodb:/data/db \<br>
 &nbsp;&nbsp;&nbsp;&nbsp;-d mongo:4.0<br>
 </code>

#### 2. config db & db user <br>
<code>docker exec -it mongodb bash</code><br>
 &nbsp;>mongodb<br>
 &nbsp;>use admin<br>
 &nbsp;switch to db admin<br>
 &nbsp;>db.auth("root","Pioneer@2017")<br>
 &nbsp;1
 &nbsp;>use dcim_cmdb<br>
 &nbsp;switch to db admin<br>
 &nbsp;>db.createUser({user:"dcim",pwd:"123456",roles:\[{ role:"dbOwner",db:"dcim_cmdb" }\]})<br>
 &nbsp;Successfully added user: {<br>
 	&nbsp;&nbsp;&nbsp;"user" : "dcim",<br>
 	&nbsp;&nbsp;&nbsp;"roles" : \[<br>
 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br>
 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"role" : "dbOwner",<br>
 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"db" : "dcim_cmdb"<br>
 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}<br>
 	&nbsp;&nbsp;&nbsp;\]<br>
 &nbsp;}<br>
 
 ##### 3. use spring-boot-start-data-mongodb to implement module function.


#### 4. Ansible document https://docs.ansible.com/