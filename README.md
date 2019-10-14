Design Question: Design A Google Analytic like Backend System. We need to provide Google Analytic like services to our customers. Pls provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.
  
The design of the system would highly depend on what exactly we need to provide to costumers. 
From the given description, I assume that we have a large number of incoming messages and we need to provide an ability 
to request these data and/or build aggration over it. We also have to reproduce the order of events in case some processing went wrong.

For the given requirements I think a set of Apache Kafka/RabbitMq + Clickhouse would fit the best. 
You can see approximate architecture on the image below.  

![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Architecture design")

1) Nginx - Webserver that will handle http requests from the users and will put body of the requests into Apache Kafka/RabbitMq. I assume all the messages will be in json/csv format.  
2) Apache Kafka/RabbitMq - Message broker and a queue that guarantees integrity of the messages and provides asynchronous behavoir for the whole system.  
3) Kubernetes cluster - Cluster of Java (doesn't really matter what language though) microservices that would process incoming messages.
Basically, microservices have to do three things a) Put raw messages into Elasticsearch so we can reproduce them later if something goes wrong.
b) Process the messages c) Put proccessed messages into Clickhouse. We might need to add something like Redis/PostgreSQL etc to this cluster in case we need sessions 
or some additional data required for processing, it highly depends on what processing we have to do.  
4) Elasticsearch - NoSql database, a backup. In case we need to reprocess some messages again, we can take raw messages from here. 
It shouldn't happen often and we don't have to be super efficient here, that's why Elasticsearch or any other NoSql databases should fit well.  
5) Clickhouse - Is an open-source column-oriented DBMS (columnar database management system) for online analytical processing. 
It was developed for analytical systems that's why I think it would fit the best for the given requirements. 
Clickhouse can handle billions of records and make an aggregation out of them in less than a millisecond. 
You can read about a small test of Clickhouse [here](https://tech.marksblogg.com/billion-nyc-taxi-clickhouse.html). 
The only requirement is that the data has to be denormalized which is acceptable for analytical data.

The whole workflow for the user will be asynchronous. We will get a request, put it into the queue, process in microservices and build a request to Clickhouse,
put the result back to queue and send a response over websockets. The whole process should take less than a few seconds.
