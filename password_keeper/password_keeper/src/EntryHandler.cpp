
#include "EntryHandler.h"


std::vector<Entry *> entryhandler::ENTRIES;

void entryhandler::getAllEntries()
{
	int num_entries = 0;

	std::ifstream entry_file;
	entry_file.open("entries.txt", std::ios::in);

	if (!entry_file.good()) return;

	for (std::string line = ""; !entry_file.eof(); std::getline(entry_file, line))
	{
		if (line == "")
		{
			continue;
		}

		if (line.at(0) != '\t')
		{
			Entry *e = new Entry(line);
			ENTRIES.push_back(e);
			num_entries++;
		}

		for (int i = 0; i < 10; i++)
		{
			if (line.substr(0, line.find(":")) == "\t" + Entry::printFieldName(i))
			{
				ENTRIES.back()->setField(
					line.substr(Entry::printFieldName(i).length() + 2, line.length()), i
					);
			}
		}
	}

	std::cout << "Successfully initialized "
		<< num_entries << " "
		<< ((num_entries == 1) ? "entry" : "entries")
		<< std::endl << std::endl;

	entry_file.close();
}

void entryhandler::saveAllEntries()
{
	int num_entries = 0;

	std::ofstream entry_file;
	entry_file.open("entries.txt", std::ios::app);

	if (!entry_file.good()) return;

	entry_file << "blah";
	/*
	for (Entry *e : ENTRIES)
	{
		if (!e->changed) continue;

		std::cout << *e->getName() << std::endl;

		for (int i = 0; e->getField(i) != NULL; i++)
		{
			std::cout << "\t" << Entry::printFieldName(0) << ":" << *e->getField(i) << std::endl;
		}

		num_entries++;
	}

	entry:mimi
	alias:ESilber
	password:p123abc
	entry:random email
	alias:joehotrod@hotmail.com
	email:joenothot
	password:enthusiast
	END
	*/
	std::cout << "Successfully saved "
		<< num_entries << " "
		<< ((num_entries == 1) ? "entry" : "entries")
		<< std::endl << std::endl;

	entry_file.close();
}


