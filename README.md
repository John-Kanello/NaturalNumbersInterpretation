# Natural Numbers Interpretation
An application that checks for possible ambiguities in number spelling and returns any possible number interpretations. Also, 
it checks if a given sequence of numbers forms a valid Greek Number.

# Phone number validation
Given an input provided by the user, the program evaluates the given input and outputs if it is a valid Greek phone number. Greek phone numbers may have 10 or 14 digits. 
If they have 10 digits, they must start with ‘2’ or ‘69’. If they have 14 digits, the must start with ‘00302’ or ‘003069’.

# Identify natural number ambiguities
There are 3 cases with which the algorithm identifies possible ambiguities in an array of numbers:
1. It checks if the current number can be split to its addends to form a new number. The first and greater number must be divisible
  by ‘10’ otherwise no new combination will be formed.</br>For example, the number 69 can have two possible interpretations 69 itself or 609(60+9).

   Note, one extra piece of logic that I added to this algorithm is checking wether an addend of
   the current number can be combined with future numbers to create more combinations.</br>
   Let's assume we have 2 numbers: 420 and 2.
   In English, the possible interpretations are 4202, 422, 400202 and finally 40022.
   The combination 40022 is achieved by splitting the first number to its addends (400+20) and then combining 20 and 2.

2. It checks if the number at the current index can be combined with future numbers.</br>
   For example, the sequence 40 5 can have two possible interpretations 405 and 45.

   Note, another piece of logic that i added to this step is check if the next number can be
   split to its addends to concatenate the current number with the larger addend of the next number.</br>
   Let's assume we have 2 numbers: 400 22.
   In English, the possible interpretations are 40022, 422, 400202 and finally 4202.
   The combination 4202 is achieved by splitting the next number to its addends (20+2) and then combining 400 and 20.

3.  It simply extends the current phone number with the number at the current index.</br>
    For example, 2 and 1 would have only one combination: 21.

The appropriate exception handling guarantees that the phone number combination generator will not get called with invalid input
ensuring that the input will always be a sequnce of space separated numbers with a maximum length of 3.

# How to run the application
Clone the program to your local Git Repository by running the command:</br>
<b>git clone https://github.com/John-Kanello/NaturalNumbersInterpretation.git</b></br></br>
The program includes the Maven Wrapper mvnw.</br>
Thus, a .jar file can be generated in the root/target folder without the need of installing Maven on the device.</br>
Then, assuming that Java is installed on the device, you can create a .jar file by navigating to the root directory of the project and execute the command:
</br><b>mvnw clean package</b></br></br>
The application can be started by executing the command:</br><b>java -jar path_to_the_name_of_the_jar_file.jar</b>

The application contains Unit Tests for both phone number validation and identifying natural number ambiguities.



