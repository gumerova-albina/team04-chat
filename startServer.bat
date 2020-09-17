if not exist ".\server\target\" mkdir .\server\target
javac -sourcepath ./server/src/main/java -d server/target/ server/src/main/java/com/chat/edu/Server.java
java -classpath ./server/target/ com/chat/edu/Server