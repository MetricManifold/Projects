
#include <windows.h>
#include <string>
#include <conio.h>
#include <iostream>

#include "DosWindow.h"
#include "Commands.h"
#include "Entry.h"
#include "EntryHandler.h"

const char g_szClassName[] = "mainWindowClass";

// Window procedure. Responds to messages.
LRESULT CALLBACK WndProc(HWND hwnd, UINT msg, WPARAM wParam, LPARAM lParam)
{

	switch (msg)
	{
	case WM_LBUTTONDOWN:
	{
		std::cout << "clicked window" << std::endl;
		/*char szFileName[MAX_PATH];
		HINSTANCE hInstance = GetModuleHandle(NULL);

		GetModuleFileName(hInstance, szFileName, MAX_PATH);
		MessageBox(hwnd, szFileName, "This program is:", MB_OK | MB_ICONINFORMATION);*/
	}
	break;

	case WM_CLOSE:
		DestroyWindow(hwnd);
		break;
	case WM_DESTROY:
		PostQuitMessage(0);
		break;
	default:
		return DefWindowProc(hwnd, msg, wParam, lParam);
	}
	return 0;
}

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance,
	LPSTR lpCmdLine, int nCmdShow)
{
	WNDCLASSEX wc;
	HWND hwnd;
	MSG Msg;

	// Size of the structure.
	wc.cbSize = sizeof(WNDCLASSEX);
	wc.style = 0;
	wc.lpfnWndProc = WndProc;
	wc.cbClsExtra = 0;
	wc.cbWndExtra = 0;
	wc.hInstance = hInstance;
	wc.hIcon = LoadIcon(NULL, IDI_APPLICATION);
	wc.hCursor = LoadCursor(NULL, IDC_ARROW);
	wc.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
	wc.lpszMenuName = NULL;
	wc.lpszClassName = g_szClassName;
	wc.hIconSm = LoadIcon(NULL, IDI_APPLICATION);

	// Then we finally register the window.
	if (!RegisterClassEx(&wc))
	{
		MessageBox(NULL, "Window Registration Failed!", "Error!",
			MB_ICONEXCLAMATION | MB_OK);
		return 0;
	}

	// Create window function.
	hwnd = CreateWindowEx(
		WS_EX_CLIENTEDGE,
		g_szClassName,
		"Password Keeper",
		WS_OVERLAPPEDWINDOW,
		CW_USEDEFAULT, CW_USEDEFAULT, 500, 500,
		NULL, NULL, hInstance, NULL);

	// Error checking for window creation.
	if (hwnd == NULL)
	{
		MessageBox(NULL, "Window Creation Failed!", "Error!",
			MB_ICONEXCLAMATION | MB_OK);
		return 0;
	}

	ShowWindow(hwnd, nCmdShow);
	UpdateWindow(hwnd);
	cmd::redirectIOToConsole();

	/*
	 Functions to send to command window.
	 */
	std::cout << "Welcome to PasswordKeeper 1000! Type \"help\" for help.\n" << std::endl;

	entryhandler::getAllEntries();
	entryhandler::saveAllEntries();

	for (std::string input = "help"; input != "quit"; std::getline(std::cin, input))
	{
		std::cout << std::endl;
		std::string cmd = input.substr(0, input.find_first_of(" "));

		if (cmd == "add")
		{
			cmd::addEntry((input.length() > 3) ? input.substr(4, input.length()) : "");
		}
		else if (cmd == "edit")
		{
			cmd::editEntry((input.length() > 4) ? input.substr(5, input.length()) : "");
		}
		else if (cmd == "list")
		{
			cmd::listEntries();
		}
		else if (cmd == "help")
		{
			cmd::printMainHelp();
		}
		else
		{
			cmd::badCommand();
		}

		std::cout << cmd::cmdEntryChar();
	}

	//entryhandler::saveAllEntries();
	exit(0);


	// This handles the application's message queue. Program loop.
	while (GetMessage(&Msg, NULL, 0, 0) > 0)
	{
		TranslateMessage(&Msg);
		DispatchMessage(&Msg);
	}


	return Msg.wParam;
}