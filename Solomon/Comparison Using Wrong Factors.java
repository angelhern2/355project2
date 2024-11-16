#include <iostream>
#include <string>

void compareValues(int id1, std::string name1, int id2, std::string name2) {
    // Incorrect comparison of unrelated factors
    if (id1 == name2.length()) { // Should compare id1 with id2 or name1 with name2
        std::cout << "Match found (incorrect comparison)\n";
    }
  if(name1 == name2)//Should be using the .equals method to compare string literals
     std::cout << "Match found (incorrect comparison)\n";
}

