#GitHubJobs

###Run Demonstration
From the project root execute `./gradlew run`

###Run Tests
From the project root execute `./gradlew test`  
To view fake data use -i while running test: `./gradlew test -i`  
The fake data has more position types in it vs live data at the time of writing.

###Troubleshooting
The gradlew file in the project root must be executable.  
If tests do not re-execute instead run `./gradlew clean test`  
On windows do not include the `./` in front of `gradlew`


