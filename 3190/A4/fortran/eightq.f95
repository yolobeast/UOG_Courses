!=========================
! Name : Sathi.Ranganathan
! Student # : 0757619
! Date : 05/04/2016
!=========================
! How to run
! 1. On command line call "gfortran eightq.f95"
! 2. Then call "./a.out" 
! 3. QueenF.txt will created
! 4. In the file there will be 92 solutions
!-------------------------------------------------------------------------------------------

program eightqueens
! global decleartions variables
integer :: i ,sum
logical, dimension (1:8) :: checkboolone
logical, dimension (2:16) :: checkbooltwo
logical, dimension (-7:7) :: checkboolthree
integer, dimension (1:8) ::x
sum=0;

  ! initailizing the arraies to have true values first
  do i = 1,8
   checkboolone(i) =.true.
  end do
  do i =2,16
   checkbooltwo(i) = .true.
  end do
  do i = -7,7
   checkboolthree(i) = .true.
  end do
  ! calling the recursive function
  call try(1)
  ! lettign the user know what is happening
  write(*,*) "QueenF.txt has been created with slution to 8 queens"
  
! contains will seprate the global varialble to other vaiables 
contains
!recursive function try from pascal
recursive subroutine try(i)

  integer :: j
  
   do j = 1,8
   !check the boolean arries are the same
    if (checkboolone(j) .and. checkbooltwo(i+j) .and. checkboolthree(i-j) )then
       x(i) = j
       checkboolone(j) = .false.
       checkbooltwo(i+j) = .false.
       checkboolthree(i-j) = .false.
	   !calling the function again
       if (i < 8 )then
          call try(i+1)
       else
          call print
       end if
	   !reseting the boolean function
       checkboolone(j) = .true.
       checkbooltwo(i+j) = .true.
       checkboolthree(i-j) = .true.
	   
      end if
    end do
	
  end subroutine
 !printing function  
subroutine print

 integer :: k,n
 ! gettign the number of time this funtion called
 sum=sum+1;
  !creating the QueenF.txt
  open (unit = 7, file = "QueenF.txt")
  
  write (7,*) " Solution number ",sum
   do k = 1,8
    do n = 1,8
	 !putting the queen at the rigth place
     if(x(k) == n) then
      write(7,"(A)", advance='no')'Q'
     else
      write(7,"(A)", advance='no')'.'
     end if 
    end do
   write (7,*) " "
  end do
  write (7,*) " "
  
end
  
end program 