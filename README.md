# JAVA


# X_O Chat Application: 
Utilised Docker, Gradle, Java, RSA, AES, and Sockets to establish a containerized multi-threaded master server and slave server that controlled/handled client-side chat application encrypted requests.
# Stone Age Runner Problem Solver:
Designed a 2D grid game and a grid solver; that uses blind and informed search strategies to find optimal solutions for random grids; while maintaining best optimization techniques to guarantee the code runs in minimal time and memory space using Java.
 # sec-project / Dukress
 is a blockchain simulation project simulating the proceess of generating and brodcasting a block to the chain 

 
 # Bachelor project :- GSNePs2.8
GSNePs is an implementation of LogA_g in SNePs; SNePs is a knowledge representation ,reasoning and acting system implemented in Lisp. LogA_g is a  logic for reasoning with uncertain beliefs. In LogA_g classical logical formulas could be associated with ordered grades representing measures of uncertainty. When a contradiction is detected the grades of information is computed and the information with the highest grade survives.
Technologies used :- Lisp,Semantic networks,belief revision and relevance logic.
____________________________________________________
# StoneAgeRunner Problem solver
This project was an implementation of a problem solver that given a grid of tiles the solver had to come up with a minimal cost plan to reach the goal.
First a problem is generated with a random grid then the solver injects this state to the root node and start the solve function.
Technologies used :- Java,Trees,queues,blind search, informed  search,and hashing
____________________________________________________
# X_O  secure chat app
This project is a chat application that can send public and private messages to online users; by connecting to one of the available servers. 
App design 
The servers were designed in a master/slave architecture; the master server is responsible for maintaining a consistent list of online users; and responsible for handling communication between slave servers. Slave servers were responsible for handling users requests and log in/out events.
The register process works by first identifying the user with a unique id to handle communication with the user then it sends a request to the master server asking whether the selected username is unique or not; if yes the master notifies all servers and the server assigns the username to the connection.Else if no the server asks the user to choose a different one.
When the users are connected to the chat app they can send public or private message by clicking on a radio button if private the user select the receiver from the online users.Then the message body is encrypted using AES and the key is encrypted using RSA. If the message is public the master server broadcast the message to all servers then servers broadcast the message to all users. 
 
Technologies used :- Java,socket programming, mulit-threading ,and AES,RSA  encryption.

