# 355project2
This is the second project for it 355. This will showcase the use of the 24 CWE rules we've selected

Sean's Implemented CWEs
Dead code https://cwe.mitre.org/data/definitions/561.html 
Description – Refers to code in the program that is never executed under any circumstances, this could be because of logic or control flow prevents it from executing 

Use of redundant code https://cwe.mitre.org/data/definitions/1041.html 
Description – Duplicate or unnecessary code that serves no purpose, this type of code can ruin clarirty and even increase overhead 

Inaccurate comments https://cwe.mitre.org/data/definitions/1116.html 
Description – Misleading or outdated annotations in code that don’t properly reflect the code’s current functionality or purpose 

Inappropriate white space style https://cwe.mitre.org/data/definitions/1114.html 
Description -  Poorly formatted white space like spaces, tabs, or blank lines  

Use of same variable for multiple purposes https://cwe.mitre.org/data/definitions/1109.html 
Description – Contains a block or other code element in which the same variable iused to control more than one unique task or store more than one instance of data 

Function call with incorrect specified arguments https://cwe.mitre.org/data/definitions/628.html 
Description – Calls a function with arguments not correctly specified, leading to  incorrect behavior 

Solomon's Implemented CWEs

Comparison Using Wrong Factors - (1025) 
Description: When performing a comparison between two entities or variables, we need to make sure the comparison operator is comparing the correct characteristics of the entities. 

Omitted Break Statement In Switch - (484) 
Description: When aiming to only execute associated with one condition, if you omit a break statement it could result in important code being executed when the switch statement should be broken out of. 

Excessive Reliance on Global Variables - (1108) 
Description: The overuse of global variables throughout multiple points in the code can lead to difficulty maintaining the code/project, this can lead to security issues because it complicates the process of finding which part of the code needs to be fixed. 

Unchecked Input for Loop Condition - (606) 
Description: When the product doesn’t correctly check the inputs that are used for exiting a loop condition it could result in excessive looping or possibly a denial of service. 

Uncaught Exception - (248) 
Description: Occurs when an exception is thrown from a function but is not caught. This could lead to the exposure of sensitive information or a program crash. 
 
Access to Critical Private Variable via Public Method - (767) 
Description: When a public method has the ability to read or modify a private variable. This could lead to the exposure of sensitive information or the use of unexpected values throughout the program leading to mass errors. 
