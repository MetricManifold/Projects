#ifndef __COMMAND_HANDLER_H__
#define __COMMAND_HANDLER_H__

#include <iostream>
#include <string>

#include "EntryHandler.h"
#include "Entry.h"

namespace cmd
{
	// Display the help screen.
	void printMainHelp();
	// Prints general entry help.
	void printEntryHelp();
	// List all the entries.
	void listEntries();
	// Change the fields of an entry given prompts
	void changeFields(Entry *);
	// Adds an entry
	void addEntry(std::string &);
	// Edit an entry.
	void editEntry(std::string &);
	// Prints bad command message.
	void badCommand();

	// Returns the standard character indicating a command
	//	is supposed to be issued.
	char cmdEntryChar();

	// Copies the given string to the clipboard.
	void copyToClipboard(const std::string &);

}

#endif