/*
 * File: Boggle.cpp
 * ----------------
 * Name: [TODO: enter name here]
 * Section: [TODO: enter section leader here]
 * This file is the main starter file for Assignment #4, Boggle.
 * [TODO: extend the documentation]
 */

#include <iostream>
#include <string>
#include "gboggle.h"
#include "graphics.h"
#include "grid.h"
#include "lexicon.h"
#include "random.h"
#include "simpio.h"
#include "set.h"
using namespace std;

/* Constants */

const int BOGGLE_WINDOW_WIDTH = 1000;
const int BOGGLE_WINDOW_HEIGHT = 600;

 string STANDARD_CUBES[16]  = {
   "AAWEGN", "ABMJOO", "ACSOPS", "AFNKPS",
   "AOITTW", "CIDOTU", "DEALRX", "DEIRVY",
   "DILTTY", "EERHNW", "EEANSU", "EHSTVW",
   "EIOSST", "ELTTTY", "HISNQU", "HLRNRZ"
};

const string BIG_BOGGLE_CUBES[25]  = {
   "AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM",
   "AEEGMU", "AEGMNN", "AFIRSY", "BJKQXZ", "CCNSTW",
   "CEIILT", "CEILPT", "CEIPST", "DDLNOR", "DDHNOT",
   "DHHLOR", "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU",
   "FIPRSY", "GORRVW", "HIPRRY", "NOOTUW", "OOOTTU"
};

/* Function prototypes */

void welcome();
void giveInstructions();
void setFaceLetters();
bool HumanTurn(string Input, int Position, string Final, string InputSafe);
bool ComputerTurn(int Position, string Final, Lexicon &lex);
bool checkPosition(int Position, int Offset);
int getRow(int Position);
int getColumn(int Position);
int CurrentPos(string Input);
Lexicon lex("EnglishWords.dat");
Set<string> ComputerSet;
//int r = 0;
int TrackKeys[16] = {0};
void changeInput();

/* Main program */

int main() {
	 changeInput();
	string Input = "SOLU";
  initGraphics(BOGGLE_WINDOW_WIDTH, BOGGLE_WINDOW_HEIGHT);
  welcome();
  giveInstructions();
  drawBoard(4, 4);

  setFaceLetters();
   Lexicon lex("EnglishWords.dat");

  for(int i=0; i<16; i++)
  {
	  ComputerTurn(i, STANDARD_CUBES[i].substr(2, 1), lex);
	 for(int i=0; i<16; i++)
	 {
		 TrackKeys[i] = 0;
	 }
	 cout << endl;
  }

 //  cout << checkPosition(7, 1);
   return 0;
}

/*
 * Function: welcome
 * Usage: welcome();
 * -----------------
 * Print out a cheery welcome message.
 */

void welcome() {
   cout << "Welcome!  You're about to play an intense game ";
   cout << "of mind-numbing Boggle.  The good news is that ";
   cout << "you might improve your vocabulary a bit.  The ";
   cout << "bad news is that you're probably going to lose ";
   cout << "miserably to this little dictionary-toting hunk ";
   cout << "of silicon.  If only YOU had a gig of RAM..." << endl << endl;
}

/*
 * Function: giveInstructions
 * Usage: giveInstructions();
 * --------------------------
 * Print out the instructions for the user.
 */

void giveInstructions() {
   cout << endl;
   cout << "The boggle board is a grid onto which I ";
   cout << "I will randomly distribute cubes. These ";
   cout << "6-sided cubes have letters rather than ";
   cout << "numbers on the faces, creating a grid of ";
   cout << "letters on which you try to form words. ";
   cout << "You go first, entering all the words you can ";
   cout << "find that are formed by tracing adjoining ";
   cout << "letters. Two letters adjoin if they are next ";
   cout << "to each other horizontally, vertically, or ";
   cout << "diagonally. A letter can only be used once ";
   cout << "in each word. Words must be at least four ";
   cout << "letters long and can be counted only once. ";
   cout << "You score points based on word length: a ";
   cout << "4-letter word is worth 1 point, 5-letters ";
   cout << "earn 2 points, and so on. After your puny ";
   cout << "brain is exhausted, I, the supercomputer, ";
   cout << "will find all the remaining words and double ";
   cout << "or triple your paltry score." << endl << endl;
   cout << "Hit return when you're ready...";
  // cout << TrackKeys[0][3];
   getLine();
}

void setFaceLetters()
{
	int k = 0;
	for(int i=0; i<4; i++)
	{
		for(int j=0; j<4; j++)
		{
			labelCube(i, j, STANDARD_CUBES[k][2]);
			k++;
		}
	}
}

bool HumanTurn(string Input, int Position, string Final, string InputSafe)
{
	TrackKeys[Position] = 1;
	if(Input.length() == 0 && Final == InputSafe)
	{
		//highlightCube(getRow(Position), getColumn(Position), 1);
		return true;
	}
	for(int i=-5; i<6; i++)
	{

		if(checkPosition(Position, i) && (i != 0) && (i != -2) && (i != 2) && (Input[0] == STANDARD_CUBES[i+Position][2]) && (TrackKeys[i+Position] == 0))
		{
			Final = Final + STANDARD_CUBES[i+Position][2];
			TrackKeys[i+Position] = 1;

			if(HumanTurn(Input.substr(1), i+Position, Final, InputSafe))
			{
				//highlightCube(getRow(Position), getColumn(Position), 1);
				return true;
			}
		/*	Final = Final.substr(0, Final.length()-1);
			TrackKeys[i+Position] = 0;*/
		}
	}
	return false;
}

int CurrentPos(string Input)
{
	for(int i=0; i<16; i++)
	{
		if(Input[0] == STANDARD_CUBES[i][2])
			return i;
	}
	return 0;
}

bool checkPosition(int Position, int Offset)
{
	if(Position+Offset < 0 || Position+Offset > 15)
		return false;

	if(Position == 0 || Position == 4 || Position == 8 || Position == 12)
	{
		if(Offset == -1 || Offset == 3)
			return false;
	}
	if(Position == 3 || Position == 7 || Position == 11 || Position == 15)
	{
		if(Offset == 1 || Offset == -3)
			return false;
	}
		return true;
}

int getRow(int Position)
{
	if(Position >= 0 && Position <= 3)
		return 0;
	else if(Position >= 4 && Position <= 7)
		return 1;
	else if(Position >= 8 && Position <= 11)
		return 2;
	else
		return 3;
}

int getColumn(int Position)
{
	if(Position == 0)
		return 0;
	else
	{
		if(Position%4 == 0)
			return 0;
		else if(Position%4 == 1)
			return 1;
		else if(Position%4 == 2)
			return 2;
		else
			return 3;
	}
	return -1;
}

bool ComputerTurn(int Position, string Final, Lexicon &lex)
{
	TrackKeys[Position] = 1;
	if(lex.contains(Final))
	{
		if(!ComputerSet.contains(Final))
		{
			//cout << Final << " ";
			ComputerSet.add(Final);
			recordWordForPlayer(Final, COMPUTER);
		}

	}
	for(int i=-5; i<6; i++)
	{
		if(i != 0 && i != -2 && i != 2 && TrackKeys[i+Position] == 0 && checkPosition(Position, i))
		{
			Final = Final + STANDARD_CUBES[i+Position][2];
			TrackKeys[Position+i] = 1;
			if(lex.containsPrefix(Final) && ComputerTurn(i+Position, Final, lex))
			{
				highlightCube(getRow(Position), getColumn(Position), 1);
				return true;
			}
			Final = Final.substr(0, Final.length()-1);
			TrackKeys[i+Position] = 0;
		}
	}
	return false;
}

void changeInput()
{
	char getLetter;
	for(int i=0; i<16; i++)
	{
		cout << "Enter Character: " ;
		cin >> getLetter;
		STANDARD_CUBES[i][2] = getLetter;
	}
}
