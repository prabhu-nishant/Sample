# Sample

1. The project basically reads a Json Or CSV file into memory. Once done it would ask for an expression from user in the format attr1 = ( attr2 * attr3 ) / attr4 . The format should be syntactically correct with space after every word or character.Operators currently suported include *,+,-,/ and it should evaluate to math value. String concatenation is not supported for now.

The right hand side expression can change based on user needs. 

For example it can take this expression as well ( attr2  + attr7 ) / (attr4 + attr7) . The attributes mentioned in the right hand side of expression should be present in the object file which is parsed else it won't return desired results.

2. You can refer to sample.json and sample.csv files under /src/test/resources folder if required. The file which is read into memory is stored as an arrayList of map.

3. The right hand side expression is being evaluated based on the shunting yard algorithm which evaluates the expression with the help of two stack data structures.

5. The project has been created using maven to import all required dependencies. The manifest details etc are part of the pom.xml.

6. Main.java is the main class and you can start with the help of following command.

	java -jar target/sample-0.0.1-SNAPSHOT-jar-with-dependencies.jar