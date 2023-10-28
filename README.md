# Natural Numbers Interpretation
An application that checks for possible ambiguities in number spelling and returns any possible number interpretations.

# Brief Explanation of the Algorithm
There are 3 cases with which the algorithm identifies possible ambiguities in an array of numbers:
1. It checks if the current number can be split to its addends to form a new number. The first number must be divisible
  by 10 otherwise no new combination will be formed.</br>For example, the number 69 can have two possible interpretations 69 itself or 609(60+9).

   Note, one extra piece of logic that I added to this algorithm which I did not see in the business requirements is
   checking wether an addend of the current number can be combined with future numbers to create more combinations.</br>
   Let's assume we have 2 numbers: 420 and 2.
   In Greek the possible interpretations are 4202, 422, 400202 and finally 40022.
   The combination 40022 is achieved by splitting the first number to each addends (400+20) and then combining 20 and 2.

2. It checks if the number at the current index can be combined with future numbers.</br>
   For example, the sequence 40 5 can have two possible interpretations 405 and 45.

3.  It simply extends the current phone number with the number at the current index.</br>
    For example, 2 and 1 would have only one combination: 21.

As a final note, I will mention that even though the application follows the business requirement which limits
the numbers of the array to at most 3 digits, the algorithm that I developed works for n digits.
For example, 1000 42, would result in the combinations:
100042, 1042, 1000402.
The appropriate exception handling guarantees that the phone number combination generator will not get called with more than 3 digits.

# How to run the program
The program includes the Maven Wrapper mvnw.</br>
Thus, a .jar file can be generated in the root/target folder without the need of installing Maven on the device.</br>
Then assuming that Java is installed on the device, the command that needs to be executed in order create the .jar file is:
</br><b>mvnw clean package</b></br>
The application can be started by executing the command:</br><b>java -jar path_to_the_name_of_the_jar_file.jar</b>



