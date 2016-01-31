#include "Entry.h"

/*
 *	Field keys:
 *	0. alias, 1. DOB, 2. email, 3. password, 4. pin, 5, card#, 6. expdate, 7. seccode, 8. Q, 9. A
 */

Entry::Entry(std::string &name)
{
	*this->name = name;

	for (int i = 0; i < 10; i++)
	{
		field[i] = NULL;
	}
}

Entry * Entry::getEntry()
{
	return this;
}

std::string Entry::setField(std::string &text, int index)
{
	if (*(field + index))
	{
		std::cout << "This value has already been set, would you like to change it? (y/N)" << std::endl;
		if (_getch() != 'y')
		{
			return "Field unchanged.\n";
		}
	}

	this->field[index] = new std::string;
	*this->field[index] = text;

	return "Field added.\n";
}

void Entry::printFields()
{
	std::cout << "The fields currently added to the entry are:" << std::endl;
	for (int i = 0; i < 10; i++)
	{
		if (*(field + i))
		{
			std::cout <<
				std::setw(15) << std::left << printFieldName(i) <<
				std::setw(2) << std::left << ":" <<
				std::setw(10) << std::left << **(field + i) <<
				std::endl;
		}
	}

	std::cout << std::endl;
}

const std::string *Entry::getField(int i)
{
	return *(field + i);
}

std::string Entry::printFieldName(int i)
{
	switch (i)
	{
		case 0:
			return "alias"; break;
		case 1:
			return "DOB"; break;
		case 2:
			return "email"; break;
		case 3:
			return "password"; break;
		case 4:
			return "PIN"; break;
		case 5:
			return "card number"; break;
		case 6:
			return "card exp date"; break;
		case 7:
			return "card sec code"; break;
		case 8:
			return "secret question"; break;
		case 9:
			return "secret answer"; break;
		default:
			return "ERROR";
	}
}

const std::string *Entry::getName()
{
	return name;
}


Entry::~Entry()
{
	std::cout << "destructor is called" << std::endl;
	for (int i = 0; i < 10; i++)
	{
		if (*(field + i))
		{
			delete field[i];
		}
	}

	if (name)
	{
		delete name;
	}
}