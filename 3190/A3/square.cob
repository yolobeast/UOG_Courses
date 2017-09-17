identification division.
program-id. square.
environment division.
input-output section.
file-control.
    select standrad-input assign to keyboard.
    select standard-output assign to display.
data division.
file section.
fd standrad-input.
    01 stdin-record picture x(80).
fd standard-output.
    01 out-line  picture x(80).

working-storage section.
77 diff picture v9(5).
77 z    picture 9(11)v9(6).
77 k    picture s9999.
77 x    picture 9(11)v9(6).
77 y    picture 9(11)v9(6).
77 g    picture s9(1).
77 temp picture 9(11)v9(6).
77 result picture z(11)9.9(6).
01 in-z     picture x(50).
01 print-line.
   02 filler picture x value space.
   02 out-z  picture z(11)9.9(6).
   02 filler picture x(5) value spaces.
   02 out-y  picture z(11)9.9(6).

procedure division.
    open input standrad-input, output standard-output.
    display "----------------------------------------------".
	display "           square root approximations".
	display "----------------------------------------------".

    perform until in-z is equal to "Q"	  
    	display "-------------------------------------"
	
		display "enter number to get the square root or Q to quit"
		accept in-z
	    move 0 to g
		if in-z is equal to "Q"
	  		stop run
		end-if
	
		if in-z < 1 then
	  		display in-z "invalid input"
		else
	  		move 0 to diff
	  		move in-z to z
      		compute x rounded = z/2
     
	 		perform until g equal to 1
	   			call "conv" using x,z,y,temp
	  			if temp / (y + x) > diff then
      				move y to x
      			else 
	  				move in-z to out-z 
      				move y to out-y
      				display "----------------------------------"
	  				display "Babylion squre roots estimations"
	  				display out-z out-y
      				compute result = function sqrt(in-z)
	  				display "COBOL square root function"
	  				display out-z result
	                move 0 to result
	                move 1 to g
	  				*>perform s1 until in-z equal to "Q"
	  			end-if
	 		end-perform
		end-if
   	end-perform. 
	
