--=========================
-- Name : Sathi.Ranganathan
-- Student # : 0757619
-- Date : 28/01/2016
--=========================
-- It's a sodoku game made with Ada language that is uses recursion function and backtracking.
-- -----------------------------------------------------------------------------
-- How to compile 
-- 1. type gnatmake -Wall sodoku.adb
-- ----------------------------------------------------------------
-- How to run
-- 1../sodoku 
-- 2. input a file name as (.txt) file
-- 3. then input another (.txt) file to sore the solved sodoku
-- ----------------------------------------------------------------------
-- These are the website that i used to learn more about ada array, conversion between chacrater and integer and function
--http://www.infres.enst.fr/~pautet/Ada95/e_c08_p6.ada
--https://courses.cs.washington.edu/courses/cse505/99au/imperative/parameters.html
--http://www.infres.enst.fr/~pautet/Ada95/e_c18_p4.ada
--http://www.developpez.net/forums/d362796/autres-langages/autres-langages/ada/type-character-integer-value/
--http://www.isa.uniovi.es/docencia/TiempoReal/Recursos/textbook/aps13-4.htm
--http://codereview.stackexchange.com/questions/37430/sudoku-solver-in-c
--http://www.infres.enst.fr/~pautet/Ada95/e_c08_p6.ada
-- ----------------------------------------------------------------------------
-- Coding refrences
-- http://www.geeksforgeeks.org/backtracking-set-7-suduku/
-- https://openclassrooms.com/courses/le-backtracking-par-l-exemple-resoudre-un-sudoku
-- http://codereview.stackexchange.com/questions/37430/sudoku-solver-in-c
-- ------------------------------------------------------------------------------
-- Functions and procedures
-- Main,procedure : sodoku
-- To check all the column,function : colcheck
-- To check all the row,function : rowcheck
-- To check the 3x3 square,function : square check
-- To check wich spot need an number,function : fillemptysopt
-- printing unfinished sodoku,procedure : printincomplete
-- printing to a file, procedure : printtofile
-- printing the solved sodoku, procedure: Print_Sudoku

with Ada.Text_IO;use Ada.Text_IO;

procedure sodoku is 

      -- declearation file type and string to open and close files
	  My_File   : File_Type;
      name_of_file : String (1..50);
      last: natural;
      
	  -- declearation of array to store sodoku information 
	  type str is array(1..20,1..20) of character;
	  type numbers is array (1..20, 1..20) of integer;
	  
	  -- a record to keep track of the row number and column number
	  type given is
		record
         row : integer;
         col : integer;
      end record;
	  
	  -- setting the record to 1 since the arry in ada starts with 1
	  arrayelement : constant given := (row => 1, col =>1);
	  
	  -- setting the arries to an variable , and bollean to bollean variable
	  checkifdone : Boolean;
	  Sudokucopy : numbers;
	  num:numbers;
	  str1 : str;
	  str2 : str;
	
    -- this function will check the column from the current empty spot with the given number.
	function colcheck(col:integer;Number : integer) return Boolean is
		
		begin
		-- this will allow the function go from left to right and check the given number fits there
		for i in 1 .. 9 loop
			if Sudokucopy(i,col) = Number then
				return false;
			end if;
		end loop;
		return true;
    
	end colcheck;
   
    -- this function will check the row from the current empty spot with the given number.
    function rowcheck(row:integer; Number : integer) return Boolean is
		
		begin
		-- this will allow the function go from up and down and check the given number fits there
		for i in 1 .. 9 loop
			if Sudokucopy(row,i) = Number then
				return false;
			end if;
		end loop;
		return true;
    
	end rowcheck;
    
    -- this function will check the each 9 square from the current empty sopt with the given number.
	function squarecheck (arrayelement : given;Number:integer) return boolean is
		
		row:constant integer := integer((((arrayelement.row-1)/3)*3) +1);
		col:constant integer := integer((((arrayelement.col-1)/3)*3) +1);
	  
		begin
		-- This will loop go from 0 to 2 to cehck all the value that is in the square to given number  
		for j in 0..2 loop
			for i in 0 .. 2 loop
				if Sudokucopy(row+j,col + i ) = Number then
					return false;
				end if;
			end loop;
		end loop;
		return true;
    
	end squarecheck;
   
    -- This is the main function that controlls the everything 
	-- This function will get the next value that can be in the soduku
	-- It uses backtracking and recursive algrothim to get the value
    function fillemptysopt(arrayelement : given) return Boolean is
		
		nextarrayelement : given := arrayelement;
	
		begin
        -- check to see if the row and column are less than 9.
		if (arrayelement.row > 9) then
			return True;
		elsif (arrayelement.col >9) then
			return true;
		end if;
		
		-- this will check to see there is no empty spot in the sodoku
		if (Sudokucopy(arrayelement.row,arrayelement.col) /= 0) then
			if nextarrayelement.row < 9 then
				nextarrayelement.row := nextarrayelement.row + 1;
				return fillemptysopt(nextarrayelement);
			else
				nextarrayelement.row := 1;
				nextarrayelement.col := nextarrayelement.col + 1;
				return fillemptysopt(nextarrayelement);
			end if;
		end if;
		-- this is where the checking the right to left , up and down and squre check function are called 
		for Number in 1 .. 9 loop
			if(squarecheck (arrayelement,Number) and rowcheck(arrayelement.row, Number) and colcheck(arrayelement.col, Number)) then
				-- this will set the picked the choosen valu for the the empty spot
				Sudokucopy(arrayelement.row,arrayelement.col) := Number;
				if (fillemptysopt(nextarrayelement)) then
					return True;
				end if;
			end if;
		end loop;
		Sudokucopy(arrayelement.row,arrayelement.col) := 0;
		return false;
   
    end fillemptysopt;
   
	-- this prints the incomplete sodoku to the command line
    procedure printincomplete is
		
		begin
		New_Line(1);
		put_line("Incompleted sodoku");
		New_Line(1);
		put_line("+-----+-----+-----+");
		-- this will print the grid for the soduku and the orginal file values
		for i in 1..9 loop
			if(i = 4) then
				put_line("+-----+-----+-----+");
			elsif (i=7)then
				put_line("+-----+-----+-----+");
			end if;
			
			for j in 1..9 loop
				put("|" & str1(i,j));
			end loop;
				put("|");
				New_Line(1);
		end loop;
		put_line("+-----+-----+-----+");
	
	end printincomplete;   
   
	-- This will print the solved soduko to the file with given name
	procedure printtofile(num : numbers) is
		
		begin
		for i in 1..9 loop
			for j in 1..9 loop
				str2(i,j) := character'val(num(i,j)+16#30#);
			end loop;
		end loop;
	  
		Put_Line("please input a file name ");
		Get_Line(name_of_file,last);
		-- gettign the name of the file
		Create(My_File,Out_File,name_of_file(1..last));
	  
		-- this will print the grid for the soduku and the solved values 
		put_line(My_File,"+-----+-----+-----+");
		for i in 1..9 loop
			if(i = 4) then
				put_line(My_File,"+-----+-----+-----+");
			elsif (i=7)then
				put_line(My_File,"+-----+-----+-----+");
			end if;
			for j in 1..9 loop
				put(My_File,"|" & str2(i,j));
			end loop;
			put(My_File,"|");
			put_line(My_File,"");
		end loop;
	  
	  put_line(My_File,"+-----+-----+-----+");
      Close(My_File);
	  Put_Line("The file " & name_of_file(1..last) & " is created");
	
	end printtofile;
	
	-- This will print the solved sodoku to the prompt
    procedure Print_Sudoku(num : in numbers) is

		begin
		for i in 1..9 loop
			for j in 1..9 loop
				str2(i,j) := character'val(num(i,j)+16#30#);
			end loop;
		end loop;
	  
		printincomplete;
		New_Line(1);
		put_line("Completed sodoku");
		New_Line(1);
		put_line("+-----+-----+-----+");
		-- this will print the grid for the soduku and the solved values 
		for i in 1..9 loop
			if(i = 4) then
				put_line("+-----+-----+-----+");
			elsif (i=7)then
				put_line("+-----+-----+-----+");
			end if;
			for j in 1..9 loop
				put("|" & str2(i,j));
			end loop;
			put("|");
			New_Line(1);
		end loop;
		
		put_line("+-----+-----+-----+");
		printtofile(num);
      
	end Print_Sudoku;

   
	begin
		New_Line(1);
		Put_Line("please input a file name ");
		Get_Line(name_of_file,last);
		Open(My_File,In_File,name_of_file(1..last));
	  
		-- change chacrater values to integers
		for i in 1..9 loop
			for j in 1..9 loop
				get(My_File, str1(i,j));
				num(i,j) := Character'pos(str1(i,j))- 16#30#;
			end loop;
		end loop;
		Close(My_File);
	  
		Sudokucopy := num;
		-- this value will check to see if the sodoku is solvealbel
		checkifdone := fillemptysopt(arrayelement);
		num := Sudokucopy;
	 
		if checkifdone then
			Print_Sudoku(num);
		else
			Put_Line("This sodoku is unslovable");
		end if;
	  
end sodoku;
