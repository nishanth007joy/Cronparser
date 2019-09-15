# CRON Parser

This project accepts a cron expression as a program argument and then it will validate the expression and outputs parsed time into console.

## Getting Started

Clone project to your local machine. Then import as a maven project and this is created as a standalone spring boot project

### Prerequisites

Install maven on your machine and then you can run project from maven. Install IDE like eclipse if you need to run from IDE.


### Installing

Please build project using maven

```
mvn clean install
```

## Running application

You can run the executable spring boot jar from the target folder.

```
java -jar Cronparser-0.0.1-SNAPSHOT.jar 
```

Where first argument is the location of input file and second argument is location where output of processed input to be saved.

## Input argument sample

```
*/15 0 1,15 * 1-5 /usr/bin/find
```
Here above is the CRON expression to be parsed and second part is the command to be executed with specified CRON expression

## Output sample for above input


| Unit        | Are           | 
| ------------- | -----:|
|minute|				|0 15 30 45|
|hour|					|0|
|day of month|			|1 15|
|month	|				|1 2 3 4 5 6 7 8 9 10 11 12|
|day of week	|		|1 2 3 4 5|
|command|				|/usr/bin/find|

The above output will be printed to the console

## Authors

* **Nishanth Mathew Joy**
