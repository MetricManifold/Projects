#ifndef __ENTRY_H__
#define __ENTRY_H__

#include <windows.h>
#include <string>
#include <iostream>
#include <conio.h>
#include <iomanip>

struct Entry
{
private:
	// Name of the entry
	std::string *name = new std::string;

	// Pointer to all the fields of the entry.
	std::string **field = new std::string*[10];
		
public:
	// Whether or not the entry has been edited.
	bool changed = false;

	Entry(std::string &);

	Entry * getEntry();

	// Sets the alias field.
	std::string setField(std::string &, int);

	// Prints field name corresponding to given int.
	static std::string printFieldName(int);

	// Prints all fields to stdout.
	void printFields();

	// Returns the selected field.
	const std::string *getField(int);

	// Gets the name of the entry.
	const std::string *getName();
	

	// Deconstructor, clears all the values.
	~Entry();
};

#endif