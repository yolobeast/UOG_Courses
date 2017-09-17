!=========================
! Name : Sathi.Ranganathan
! Student # : 0757619
! Date : 28/01/2016
!=========================
! It's a tictactoe game reengineered to work with Fortran 95
! to compile gofortran tictactoe.f95 and to run ./a.out will make the game play
! There is total of 9 subroutine's for modularization 
!-------------------------------------------------------------------------------------------
! How to run
! 1. On command line call "gfortran tictactoe.f95"
! 2. Then call "./a.out" 
! 3. This will run the tic tac toe game
! 4. There you can input 1-9 to run the game
!-------------------------------------------------------------------------------------------
! SUBROUTINES
! TICTACTOE - A main program which plays the game of Tic-Tac-Toe between a human
! CHKOVR - A subroutine to check to see if the game is over.
! SAME - A function to check to see if three elements in a row, column or diagonal are the same.
! BOARDSETUP - A subroutine to setup the TicTacToe board.
! CHKPLAY - A subroutine that checks to make sure the human player cannot make a play in a square that is already occupied.
! COMPMOVE - A subroutine that plays for the computers move.
! declaringwinner - will delcare who is the winner is
! drawingboard - draw the board 
! chkplay - will the check the game if it's running properly

program tictactoe
  
  !variable declearation
  character, dimension(3,3) :: tictac
  character:: winner
  logical :: over,chkplay
  integer :: move, turn

  !lettign the user know how to play the game 
  write(*,*) "play tic-tac-toe. enter 1-9 to play"
  write(*,*) " "
  write(*,*) "        1 | 2 | 3 "
  write(*,*) "       ---+---+---"
  write(*,*) "        4 | 5 | 6 "
  write(*,*) "       ---+---+---"
  write(*,*) "        7 | 8 | 9 "
  write(*,*) " "
            
  call boardsetup(tictac)
  turn = 0
  
  do 
      write(*,*) "your move ?"
      read (*,*) move
   !this if statments will check the number that is inputed is between 0 and 9
   if (move > 0 .and. move <= 9) then
   ! this fuction willl chek to see if there is empty spot on board
     if (chkplay(tictac,move))then
        !calls all the subroutine
        call checkmove(move,tictac) 
        call playgame(tictac,turn,over,winner)
        call declaringwinner(over,winner)
     else 
            write(*,*) "invalid move, box already occupied."
            turn = 0
     end if
    else
        write(*,*) "invalid input please input a number between 1 to 9."
        turn = 0
      end if
  end do

end program

!=========================
! CHECK move
!=========================
subroutine checkmove(move,tictac) 
  
  integer :: move
  character, dimension(3,3) :: tictac
  
  !this how rhe human will play the game
  if (move == 1)then
      tictac(1,1) = "x"
  else if (move == 2)then 
      tictac(1,2) = "x"
  else if (move == 3)then
      tictac(1,3) = "x"
  else if (move == 4)then 
      tictac(2,1) = "x"
  else if (move == 5)then
      tictac(2,2) = "x"
  else if (move == 6)then
      tictac(2,3) = "x"
  else if (move == 7)then
      tictac(3,1) = "x"
  else if (move == 8)then
      tictac(3,2) = "x"
  else if (move == 9)then
      tictac(3,3) = "x"
  end if

end

!======================================
! THIS FUNCTION WILL DECLARE THE WINNER
!======================================
subroutine declaringwinner(over,winner) 
  
  logical :: over
  character:: winner

   if(over) then
    write(*,*) "the game is over!"
    if (winner == "d") then
       write(*,*) "the game is a draw. "
    else
       write(*,*) "the winner is: ", winner
    end if 
    ! I had to use the stop statment to exit end the program 
    stop
   end if

end 

!=========================
! PLAY GAME
!=========================
subroutine playgame(tictac,turn,over,winner) 
  
  character, dimension(3,3) :: tictac
  character:: winner
  logical :: over
  integer :: turn
    
  do
      call drawingboard(tictac,turn)
      !these set of if statement will determin who's turn is next
      if(turn ==1) then
        call chkovr(tictac,over,winner)
        turn =0
        exit
      else
        call chkovr(tictac,over,winner)
        !this statments will check if the game is over or not
        if (over) then
           exit  
        else
           turn =1
           call compmove(tictac)
        end if
      end if
    end do

end


!=====================
! TO DRAW THE BOARD
! ====================
subroutine drawingboard(tictac,turn) 
  
  character (80) :: fmt
  character, dimension(3,3) :: tictac
  integer :: i,turn

  do i=1,3
    if(i==1) then
      ! this if statment will determine who's trun is it 
      if(turn ==0)then
        write(*,*) "after your move..."
      else
        write(*,*) "after my move..."
      end if
    end if
    !this statment will draw the tic tac toe board
    fmt='(2x,a1,1x,"|",1x,a1,1x,"|",1x,a1,1x)'
    write(*,fmt)(tictac(i,j), j=1,3)
      
    if(i == 1 .or. i==2)then
       write(*,*) "---+---+---"
    end if
  
  end do

end

!==========================================
!subroutine to set up the tic-tac-toe board  
!==========================================  
subroutine boardsetup(tictac)
  
  character, dimension(3,3) :: tictac
   
   ! this two loops will go through the 2d array and set the boar up
   do i = 1,3
    do j = 1,3
      tictac(i,j) = " "
    end do
  end do
 return

end

!=================================
! subroutine to check human play  
! ================================ 
logical function chkplay(tictac,move) 
      
  character, dimension(3,3) :: tictac
  integer :: move

  if (move==1 .and. tictac(1,1) == " ")then
      chkplay = .true.
  else if (move==2 .and. tictac(1,2) == " ")then 
      chkplay = .true.
  else if (move==3 .and. tictac(1,3) == " ")then 
      chkplay = .true.
  else if (move==4 .and. tictac(2,1) == " ")then 
      chkplay = .true.
  else if (move==5 .and. tictac(2,2) == " ")then 
      chkplay = .true.
  else if (move==6 .and. tictac(2,3) == " ")then 
      chkplay = .true.
  else if (move==7 .and. tictac(3,1) == " ")then 
      chkplay = .true.
  else if (move==8 .and. tictac(3,2) == " ")then
      chkplay = .true.
  else if (move==9 .and. tictac(3,3) == " ")then
      chkplay = .true.
  else
      chkplay = .false.
  end if 

end

!==============================================
!subroutine to check to see if the game is over      
!==============================================
subroutine chkovr(tictac,over,winner)
      
  character, dimension(3,3) :: tictac
  character:: winner
  logical:: over
  character :: blank, draw
  logical:: same
  logical:: dsame,esame
  integer:: ir, ic
  
  blank = ' ' 
  draw = 'd'
! assume game is over at start
  over = .true.

! check for a winner
! check rows for a winner
  do ir = 1, 3
    if (same(tictac(ir,1),tictac(ir,2),tictac(ir,3))) then
      winner = tictac(ir,1)
      return
    end if
  end do

! no winner by rows, check columns for a winner
  do ic = 1, 3
    if (same(tictac(1,ic),tictac(2,ic),tictac(3,ic))) then
      winner = tictac(1,ic)
      return
    end if
  end do

!c no winner by rows or columns, check diagonals
  dsame = same(tictac(1,1),tictac(2,2),tictac(3,3)) 
  esame = same(tictac(1,3),tictac(2,2),tictac(3,1)) 

  if (dsame) then
    winner = tictac(2,2)
    return
  else if(esame)then
    winner = tictac(2,2)
    return
  end if

! no winner at all. see if game is a draw
! check each row for an empty space
      
  do ir = 1,3
    do ic = 1,3
      if (tictac(ir,ic) == blank) then
        over = .false.
        return
        end if
    end do
  end do 

! no blank found, game is a draw
  winner = draw
  return    

end

!=========================================
! CHEK THE BOD FOR THE SAME SYMBOL           
! =========================================
logical function same(t1,t2,t3)
  
  character:: t1,t2,t3
      
  !check if either X's or O's are the same 
  if (t1 == "x" .and. t2 == "x" .and. t3 == "x")then
    same = .true.
  else if (t1 == "o" .and. t2 == "o" .and. t3 == "o") then
    same = .true.    
  else      
    same = .false.
  end if

end

!=========================================
!subroutine to play for the computer  
!=========================================
subroutine compmove(tictac)
      
  character, dimension(3,3) :: tictac
  integer :: pathsum(8)
  integer, dimension(3,8) :: paths
  integer :: k, x, y, randpos
  integer, dimension(9,2) :: board
      
  !The declearation of the arraies 
  paths = reshape((/1,2,3,4,5,6,7,8,9,1,4,7,2,5,8,3,6,9,1,5,9,3,5,7/) , (/3,8/))
  board = reshape ((/ 1,1,1,2,2,2,3,3,3,1,2,3,1,2,3,1,2,3 /) , (/9,2/))

!  calculate the pathsums
  do i = 1,8
    pathsum(i) = 0
      do j = 1,3
        x = board(paths(j,i),1)
        y = board(paths(j,i),2)
        
        if (tictac(x,y) == " ")then 
            k = 0
        else if (tictac(x,y) == "x")then 
            k = 1
        else if (tictac(x,y) == "o")then 
            k = 4 
        end if 
            pathsum(i) = pathsum(i) + k 
      end do
  end do 

!    offensive code to deal with scenarios where the
!    computer has two in a path
  do i = 1,8
    if (pathsum(i) == 8) then
      do j = 1,3
         x = board(paths(j,i),1)
         y = board(paths(j,i),2)
         !write(*,*) " yolo", pathsum,x,y
         if (tictac(x,y) == " ") then
            tictac(x,y) = "o"
            return
         end if
      
      end do 
    end if
  end do 
  
!    defensive code to deal with scenarios where the
!    opponent has two in a path
  do i = 1,8
   if (pathsum(i) == 2) then
      do j = 1,3
         x = board(paths(j,i),1)
         y = board(paths(j,i),2)
         if (tictac(x,y) == " ") then
            tictac(x,y) = "o"
            return
         end if
      
      end do 
   end if
  end do 
  
! randomaly assigne a computer's spot  
  do 
    randpos = int(rand(0)*9)+1
    x = board(randpos,1)
    y = board(randpos,2)
    if (tictac(x,y) == " ") then
       tictac(x,y) = "o"
       return
    end if
  end do

end

