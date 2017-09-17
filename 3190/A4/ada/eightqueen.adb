--=========================
-- Name : Sathi.Ranganathan
-- Student # : 0757619
-- Date : 05/04/2016
--=========================
-- How to run
-- 1. type gnatmake -Wall eightqueen.adb
-- 1../eightqueen
-- 3. QueenA.txt will created
-- 4. In the file there will be 92 solutions
-- ----------------------------------------------------------------------

with Ada.Text_IO;use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;

-- The program begins here
procedure eightqueen is 
 
 -- initializing the global vaiable
 checkboolone: array (1..8) of boolean;
 checkbooltwo: array (2..16) of boolean;
 checkboolthree: array (-7..7) of boolean;
 x: array (1..8) of integer;
 
 sum : integer;
 My_File   : File_Type;
 
-- The printing function
procedure print is
 
	begin
	
	-- This function is too check the file being opend is proper and to appand to that file
	begin
   	
	Open (File => My_File, Mode => Append_File, Name => "QueensA.txt");
	exception
   	when Name_Error =>
      Create (File => My_File, Mode => Out_File, Name => "QueensA.txt");
	
	end;
	-- counts how many time the function is running 
	sum:=sum+1;
	put(My_File,"solution number");
	put(My_File,sum);
	put_line(My_File," ");
	
	-- checking how many where to put the Q 
	for z in 1..8 loop
		
		for i in 1..8 loop
		-- making sure where to put the Queens
		 if (x(i) = z) then
		 	put(My_File,"Q");
		else 
			put(My_File,".");
		end if;
	   end loop;
	   
	   put_line(My_File," ");
	end loop;
	 
	put_line(My_File," ");
	Close(My_File);

end print;

-- The recursive function  
procedure try(i:integer) is

	begin
	-- looping to check the arries
	for j in 1..8 loop
		-- checking the arries are the same
		if ( checkboolone(j) and checkbooltwo(i+j) and checkboolthree(i-j) ) then
			x(i) := j;
			checkboolone(j) := false;
			checkbooltwo(i+j) := false;
			checkboolthree(i-j) :=false;
			-- calling the function again
			if (i < 8 ) then
				try(i+1);
			else
				print;
			end if;
			
			checkboolone(j) := true;
			checkbooltwo(i+j) := true;
			checkboolthree(i-j) :=true;
		end if;
	end loop;
end try;

begin 
  -- inorder to avoid appanding to already existing file i had to delete the old one
  Create (File => My_File, Mode => Out_File, Name => "QueensA.txt");
  Delete(File => My_File);

 -- looping and initilizing the variable to ture
 for i in 1..8 loop
 	checkboolone(i) := true;
 end loop;
 for i in 2..16 loop
 	checkbooltwo(i) := true;
 end loop;
 for i in -7..7 loop
 	checkboolthree(i) :=true;
 end loop;
 sum:=0;
 try(1);
 -- lettign the user knwo what is happeing
 put_line("QueenA.txt has been created with slution to 8 queens");
 
 
end eightqueen;			