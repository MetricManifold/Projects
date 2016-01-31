
#include "Commands.h"

void cmd::printMainHelp()
{
	std::cout <<
		"********************************************************************\n"
		"add [name]\t\t - Adds a new entry to the password list.\n"
		"remove [name]\t\t - Removes the selected entry from the password list.\n"
		"edit [name]\t\t - Edits the selected entry in the password list.\n"
		"list\t\t\t - Lists all the password entries.\n"
		"********************************************************************\n"
		<< std::endl;
}

void cmd::listEntries()
{
	bool printed = false;

	for (Entry *e : entryhandler::ENTRIES)
	{
		std::cout << *e->getName() << std::endl;
		printed = true;
	}

	if (!printed)
	{
		std::cout << "No entries exist." << std::endl;
	}

	std::cout << std::endl;
}

void cmd::changeFields(Entry *e)
{
	for (char c = 'h'; c != 'q'; c = _getch())
	{
		if (c == 'h')
		{
			printEntryHelp();
		}
		else if (c == 'f')
		{
			e->printFields();
		}
		else if (c >= '0' && c <= '9')
		{
			std::cout << "Enter value for " << Entry::printFieldName(c - '0') << ": ";

			std::string field;
			std::getline(std::cin, field);
			std::string output = e->setField(field, c - '0');
			e->changed = true;

			std::cout << output << std::endl;
		}
		else
		{
			badCommand();
		}
	}
}

void cmd::addEntry(std::string &name)
{
	while (name == "")
	{
		std::cout << "Choose a name for the entry: ";
		std::getline(std::cin, name);
	}

	Entry *e = new Entry(name);

	std::cout << std::endl;
	std::cout << "You are starting a new entry.\n" << std::endl;

	changeFields(e);
	entryhandler::ENTRIES.push_back(e);

	std::cout << "Finished editing entry\n" << std::endl;
}

void cmd::editEntry(std::string &name)
{
	while (name == "")
	{
		std::cout << "Choose a name for the entry: ";
		std::getline(std::cin, name);
	}

	bool found_entry = false;

	for (Entry *e : entryhandler::ENTRIES)
	{
		if (*e->getName() == name)
		{
			changeFields(e);
			found_entry = true;
		}	
	}

	if (!found_entry) std::cout << "Entry not found.\n" << std::endl;
	else std::cout << "Finished editing entry\n" << std::endl;
}

void cmd::badCommand()
{
	std::cout << "That command is not recognized." << std::endl;
}

char cmd::cmdEntryChar()
{
	return '>';
}

void cmd::copyToClipboard(const std::string &text)
{
	OpenClipboard(GetConsoleWindow());
	EmptyClipboard();

	HGLOBAL hg = GlobalAlloc(GMEM_MOVEABLE, text.size());

	if (!hg)
	{
		CloseClipboard();
		return;
	}

	memcpy(GlobalLock(hg), text.c_str(), text.size() + 1);
	GlobalUnlock(hg);
	SetClipboardData(CF_TEXT, hg);
	CloseClipboard();
	GlobalFree(hg);
}

void cmd::printEntryHelp()
{
	std::cout <<
		"********************************************************************\n"
		"To create or edit a field, simply type the corresponding number.\n"
		"The following fields may be created/edited:\n\n"
		"0. alias\t\t1. DOB\t\t\t2. email\n"
		"3. password\t\t4. PIN\t\t\t5. card number\n"
		"6. card exp date\t7. card sec code\t8. secret question\n"
		"9. secret answer\n\n"
		"Type \"h\""
		" to get information about the fields you can use"
		" or \"q\" to stop editing the entry or \"f\" to print"
		" all the created fields.\n"
		"********************************************************************\n"
		<< std::endl;
}
