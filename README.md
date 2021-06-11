Suppose you are given a text file containing pairs of city names, one pair per line, with the names on each line separated by a comma. The file might look something like:

Philadelphia, Pittsburgh Boston, New York Philadelphia, New York Los Angeles, San Diego New York, Croton-Harmon St. Petersburg, Tampa

Each line of the file indicates that it is possible to travel between the two cities named. (More formally, if we think of the cities as nodes in a graph, each line of the file specifies an edge between two nodes.) In the example above it is possible to travel between Boston and New York, and also between New York and Philadelphia and between Philadelphia and Pittsburgh, so it follows that Boston and Pittsburgh are connected. On the other hand, there is no path between Boston and Tampa, so they are not connected.

The program will be invoked from the command line as:

java Connected

and will output a single line stating "yes" or "no".
