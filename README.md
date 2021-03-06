#Pathfinder
Pathfinding algorithm testing framework in a grid of nodes.
#Pathfinding Testing Framework
Various implementations of path finding algorithms with a node field construction and rendering framework to use them.

#Uses
This framework can be used for experimenting with new forms of pathfinding. Game designers can use the base algorithms and modify them to the requirements of their game.

#Features
Field class can be used to model a grid of nodes with various compositions.
FieldBuilder is an abstract class that can be used to generate Fields for testing, possible implementations are maze generators, clusters, barriers, etc.
PathFinder is an abstract class that can be used as a base for designing and testing various algorithms.

#FieldBuilder
FieldBuilder is a functional interface that can be implemented to write various algorithms that generate a field of nodes. FieldBuilders take in a width, height, and any number of double parameters.

#FieldRenderer
A class that can render a representation of a field/path in a canvas window. Multiple render windows can be opened by the same program and will function independent of each other(except when closing).
