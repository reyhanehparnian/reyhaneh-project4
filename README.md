# Project 4: Call to Order

This project implements a custom sortable ArrayList and uses it to organize and analyze Bowdoin College student directory data. It showcases Java concepts like inheritance, interfaces, comparators, inner classes, and file handling.

## Features

- `SortableArrayList`: A custom list class that supports selection sort using user-defined comparators.
- `Student`: A class representing individual students from a directory.
- `DirectorySort`: Reads from a directory file and sorts students by:
    - SU box (smallest/largest)
    - Last name (first/last)
    - Number of vowels in full name (most/least)
    - (Extra credit) Fancy phone numbers with most repeated digits

## File Structure

- `src/`: Java source files
- `directory-files/`: Contains `directory.txt` with student data
- `README.md`: This file
- `LICENSE`: License information

## How to Run

Compile and run `DirectorySort.java`. It will read from `directory-files/directory.txt`, build a list of students, and print answers to required questions.

## Requirements

- Java 8+
- IntelliJ or another Java IDE
