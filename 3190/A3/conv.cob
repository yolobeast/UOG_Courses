identification division.
program-id. conv.
environment division.
input-output section.
file-control.
    select standard-output assign to display.
data division.
file section.
fd standard-output.
    01 out-line  picture x(80).
working-storage section.
77 g    picture 9(11)v9(6).
77 h    picture 9(11)v9(6).
77 i    picture 9(11)v9(6).
77 j    picture 9(11)v9(6).
LINKAGE SECTION.
77 x    picture 9(11)v9(6). 
77 z    picture 9(11)v9(6).
77 y    picture 9(11)v9(6).
77 temp picture 9(11)v9(6).
procedure division using x,z,y,temp.
    move x to g.
    move z to h.
	move y to i.
	move temp to j.
	  
	compute i rounded = 0.5 * (g + h / g).
	 subtract g from i giving j.
    if j < 0 then 
	  compute j = - j
	end-if.
	  
	move g to x.
    move h to z.
	move i to y.
	move j to temp.
