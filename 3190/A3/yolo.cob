identification division.
program-id. square.
environment division.
input-output section.
file-control.
    select standrad-input assign to keyboard.
    select standard-output assign to display.
data division.
file section.
fd input-file.
    01 standard-input picture x(80).
fd standard-output.
    01 out-line  picture x(80).
working-storage section.
77 diff picture v9(5).
77 z    picture 9(11)v9(6).
77 k    picture s9999.
77 x    picture 9(11)v9(6).
77 y    picture 9(11)v9(6).
77 temp picture 9(11)v9(6).