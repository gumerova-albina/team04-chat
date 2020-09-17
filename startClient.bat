if not exist ".\cient\target\" mkdir .\client\target
javac -sourcepath ./client/src -d client/target/ client/src/main/java/com/chat/edu/Client.java
java -classpath ./client/target/ main/java/com/chat/edu/Client