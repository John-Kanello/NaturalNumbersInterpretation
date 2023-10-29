# Natural Numbers Interpretation
An application that checks for possible ambiguities in number spelling and returns any possible number interpretations. Also, 
it checks if a given sequence of numbers forms a valid Greek Number.

# Ambiguities In Number Spelling
There are 3 cases with which the algorithm identifies possible ambiguities in an array of numbers:
1. It checks if the current number can be split to its addends to form a new number. The first number must be divisible
  by 10 otherwise no new combination will be formed.</br>For example, the number 69 can have two possible interpretations 69 itself or 609(60+9).

   Note, one extra piece of logic that I added to this algorithm is checking wether an addend of
   the current number can be combined with future numbers to create more combinations.</br>
   Let's assume we have 2 numbers: 420 and 2.
   In English, the possible interpretations are 4202, 422, 400202 and finally 40022.
   The combination 40022 is achieved by splitting the first number to its addends (400+20) and then combining 20 and 2.

3. It checks if the number at the current index can be combined with future numbers.</br>
   For example, the sequence 40 5 can have two possible interpretations 405 and 45.

   Note, another piece of logic that i added to this step is check if the next number can be
   split to its addends to concatenate the current number with the larger addend of the next number.</br>
   Let's assume we have 2 numbers: 400 22.
   In English, the possible interpretations are 40022, 422, 400202 and finally 4202.
   The combination 4202 is achieved by splitting the next number to its addends (20+2) and then combining 400 and 20.

5.  It simply extends the current phone number with the number at the current index.</br>
    For example, 2 and 1 would have only one combination: 21.

The appropriate exception handling guarantees that the phone number combination generator will not get called with invalid input
ensuring that the input will always be a sequnce of space separated numbers.

# How to run the program
Clone the program to your local Git Repository by running the command:</br>
<b>git clone https://github.com/John-Kanello/NaturalNumbersInterpretation.git</b></br></br>
The program includes the Maven Wrapper mvnw.</br>
Thus, a .jar file can be generated in the root/target folder without the need of installing Maven on the device.</br>
Then, assuming that Java is installed on the device, you can create a .jar file by navigating to the root directory of the project and execute the command:
</br><b>mvnw clean package</b></br></br>
The application can be started by executing the command:</br><b>java -jar path_to_the_name_of_the_jar_file.jar</b>



