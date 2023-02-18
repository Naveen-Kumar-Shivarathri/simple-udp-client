# Simple Reactive UDP Publisher and Subscriber

Run 2 instances of this client with below runtime configurations under same local area network:

**Publisher/Receiver instance 1**
````
java -DRECEIVER_ADDR=230.0.0.0 -DRECEIVER_PORT=4546 -DSENDER_ADDR=230.0.0.0 -DSENDER_PORT=4545 -jar simple-udp-client-0.0.1-SNAPSHOT.jar
````

**Publisher/Receiver instance 2**
````
java -DRECEIVER_ADDR=230.0.0.0 -DRECEIVER_PORT=4545 -DSENDER_ADDR=230.0.0.0 -DSENDER_PORT=4546 -jar simple-udp-client-0.0.1-SNAPSHOT.jar
````

