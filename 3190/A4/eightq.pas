program eightqueens(output);

 var i: integer;
 var g: integer;
 a: array [1..8] of boolean;
 b: array [2..16] of boolean;
 c: array [-7..7] of boolean;
 x: array [1..8] of integer;

 procedure print;
 var k: integer;
 var i: integer;
 tfOut: TextFile;
 begin
 g:=g+1;
 Assign(tfOut, 'QueenP.txt');
 Append (tfOut);
 writeln(tfOut,' ');
 writeln(tfOut,'solution number' ,g);	
 for k := 1 to 8 do
 	begin
 		writeln(tfOut,' ');
 		for i := 1 to 8 do
 			begin
 			if ( x[k] = i ) then
 				write (tfOut,'Q' : 4)
 			else
 				write(tfOut,'.':4);
 			end;
 	end;
 writeln(tfOut,' ');
 close(tfOut);
 
 end; {print}

 procedure try(i: integer);
 	
	var j: integer;
	begin
	
 	for j := 1 to 8 do	
 		
		if a[j] and b[i+j] and c[i-j] then
 		begin
 		x[i] := j;
 		a[j] := false;
 		b[i+j] := false;
 		c[i-j] := false;
		if i < 8 then
 		begin
		try(i+1);
		writeln (i);
		end
 		else
 		//print;
 		a[j] := true;
 		b[i+j] := true;
 		c[i-j] := true;
 		end
 		end; {try}

 		begin
 		for i := 1 to 8 do
 			
			a[i] := true;
 			for i := 2 to 16 do
				b[i] := true;
 				for i := -7 to 7 do
 					c[i] := true;
 					try(1);
 		end.