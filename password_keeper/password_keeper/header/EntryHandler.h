#ifndef __ENTRY_HANDLER_H__
#define __ENTRY_HANDLER_H__

#include <vector>
#include <fstream>
#include <string>

#include "Entry.h"


namespace entryhandler
{
	extern std::vector<Entry *> ENTRIES;

	void getAllEntries();
	void saveAllEntries();
}

#endif