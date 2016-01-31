#ifndef __DOS_WINDOW_H__
#define __DOS_WINDOW_H__

#include <windows.h>
#include <stdio.h>
#include <fcntl.h>
#include <io.h>
#include <iostream>
#include <fstream>
#include <string>

namespace cmd
{
	void redirectIOToConsole();
}

#endif