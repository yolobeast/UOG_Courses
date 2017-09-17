identification division.
program-id. squareootfun.
environment division.
input-output section.
file-control.
    select standard-output assign to display.
data division.
file section.
fd standard-output.
    01 out-line  picture x(80).
LINKAGE SECTION.
77 z    picture 9(11)v9(6).
77 x    picture 9(11)v9(6).
77 y    picture 9(11)v9(6).
77 temp picture 9(11)v9(6).   
procedure division using x,z,temp.

    compute y rounded = 0.5 * (x + z / x).
    *>compute temp = (y - x).
	 subtract x from y giving temp.
    if temp < 0 then 
	  compute temp = - temp
	end-if.


